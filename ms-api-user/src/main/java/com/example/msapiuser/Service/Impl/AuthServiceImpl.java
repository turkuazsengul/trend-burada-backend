package com.example.msapiuser.Service.Impl;

import com.example.msapiuser.Constants.ExceptionConstants;
import com.example.msapiuser.Converter.UserConverter;
import com.example.msapiuser.Core.Response.BaseResponse;
import com.example.msapiuser.Core.Response.Response;
import com.example.msapiuser.Core.ResponseMapper;
import com.example.msapiuser.Entity.UserEntity;
import com.example.msapiuser.Exception.AccountConfirmExceptions;
import com.example.msapiuser.Exception.DuplicateUserException;
import com.example.msapiuser.Exception.RegisterExceptions;
import com.example.msapiuser.Model.UserDto;
import com.example.msapiuser.Repository.UserRepository;
import com.example.msapiuser.Service.AuthService;
import com.example.msapiuser.Service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AuthServiceImpl implements AuthService {

    private final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailService mailService;

    @Autowired
    private ResponseMapper responseMapper;

    @Override
    public UserDto login(String username) {
        return userConverter.convertToDto(userRepository.findByEmail(username));
    }

    @Override
    public BaseResponse register(UserDto userDto) {
        BaseResponse baseResponse;
        try {
            int randomConfirmCode = randomConfirmCode();

            checkUserFound(userDto);
            userDto.setConfirmCode(randomConfirmCode);
            userDto.setEnable(false);
            userDto.setConfirmCodeCreatedTime(new Date());
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

            userRepository.save(userConverter.convertToEntity(userDto));
            mailService.sendRegisterConfirmMail2Queues(userDto.getEmail(), randomConfirmCode);

            baseResponse = responseMapper.response2Map(userDto);
        } catch (RegisterExceptions e) {
            baseResponse = responseMapper.exceptionalResponse2Map(e);
        }

        return baseResponse;
    }

    @Override
    public BaseResponse confirm(int userId, int confirmCode) {
        BaseResponse baseResponse;
        try {
            UserEntity userEntity = userRepository.findByPkId(userId);
            UserDto userDto = userConverter.convertToDto(userEntity);
            checkMailConfirmCode(confirmCode, userDto);
            userRepository.save(userConverter.convertToEntity(userDto));

            baseResponse = responseMapper.response2Map(userDto);
        } catch (RegisterExceptions re) {
            baseResponse = responseMapper.exceptionalResponse2Map(re);
            logger.error("Confirm Proses Fail: " + re.getMessage());
        }
        return baseResponse;
    }

    @Override
    public Response<UserDto> forgotPassword(int userId, int confirmCode) {
        /**
         * Kullanıcı login ekranındaki link ile bir mail tetikler. tetiklemeden önce buradan
         * DB de yer alan tabloda yeni bir token oluşur. Token süresi ve token burda yaratılacak.
         * süresi dolan token ın kontrolü doğrulama rest ucunda kontrol edilecek.
         */
        return null;
    }

    @Override
    public BaseResponse createConfirm(int userId) {
        BaseResponse baseResponse;
        int randomConfirmCode = randomConfirmCode();
        try {
            UserDto userDto = userConverter.convertToDto(userRepository.findByPkId(userId));

            if(userDto !=null){
                userDto.setConfirmCode(randomConfirmCode);
                userDto.setConfirmCodeCreatedTime(new Date());

                userRepository.save(userConverter.convertToEntity(userDto));
                mailService.sendRegisterConfirmMail2Queues(userDto.getEmail(), randomConfirmCode);
                baseResponse = responseMapper.response2Map(userDto);
            }else{
                baseResponse = responseMapper.exceptionalResponse2Map(ExceptionConstants.CREATE_CONFIRM_PROCESS_FAIL,ExceptionConstants.NO_FOUND_USER_MESSAGE);
            }
        } catch (Exception e) {
            baseResponse = responseMapper.exceptionalResponse2Map(ExceptionConstants.CREATE_CONFIRM_PROCESS_FAIL,e.getMessage());
        }
        return baseResponse;
    }

    private int randomConfirmCode() {
        return (int) ((Math.random() * (999999 - 100000)) + 100000);
    }

    private void checkMailConfirmCode(int confirmCode, UserDto userDto) {
        int providedConfirmCode = userDto.getConfirmCode();

        if (confirmCode != providedConfirmCode) {
            throw new AccountConfirmExceptions("Register Confirm Fail! ",ExceptionConstants.CONFIRM_PROCESS_FAIL_MESSAGE);
        } else {
            userDto.setEnable(true);
        }
    }

    private void checkUserFound(UserDto userDto) throws RegisterExceptions {
        UserEntity userEntity = userRepository.findByEmail(userDto.getEmail());
        if (userEntity != null) {
            if (userEntity.isEnable()) {
                logger.error("Active User Register Request Fail. User id: " + userEntity.getPkId());
                throw new DuplicateUserException(ExceptionConstants.REGISTER_PROCESS_FAIL, ExceptionConstants.DUPLICATE_USER_FAIL_MESSAGE);
            } else {
                userDto.setPkId(userEntity.getPkId());
            }
        }
    }
}
