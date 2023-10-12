package com.example.apiotomation.Service;

import com.example.apiotomation.Converter.TestResultConverter;
import com.example.apiotomation.Enum.TestProcessCodeEnum;
import com.example.apiotomation.Enum.TestProcessMessageEnum;
import com.example.apiotomation.Entity.ResultEntity;
import com.example.apiotomation.Core.BaseResponse;
import com.example.apiotomation.Modal.RequestModal;
import com.example.apiotomation.Dto.ResultDto;
import com.example.apiotomation.Modal.TestResultModal;
import com.example.apiotomation.Repository.TestResultRepository;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class QaAutomationServiceImpl implements QaAutomationService {
    Logger logger = LoggerFactory.getLogger(QaAutomationServiceImpl.class);

    @Autowired
    TestResultRepository testResultRepository;

    @Autowired
    TestResultConverter testResultConverter;

    @Autowired
    QaRunMethodsService qaRunMethodsService;

    @Override
    public BaseResponse<ResultDto> getAllProcess() {
        BaseResponse<ResultDto> baseResponse = new BaseResponse<>();
        try {
            List<ResultEntity> testResultEntity = testResultRepository.findAll();

            baseResponse.setReturnCode(TestProcessCodeEnum.DEFAULT_SUCCESS_CODE.getCode());
            baseResponse.setReturnMessage(TestProcessMessageEnum.SUCCESS__MESSAGE.getMessage());
            baseResponse.setTestResults(testResultConverter.convertToDtoList(testResultEntity));
        } catch (Exception e) {
            logger.error("Error: " + e.getMessage());
            baseResponse.setReturnCode(TestProcessCodeEnum.DEFAULT_ERROR_CODE.getCode());
            baseResponse.setReturnMessage(TestProcessMessageEnum.ERROR__MESSAGE.getMessage());
        }
        return baseResponse;
    }

    @Override
    public BaseResponse<ResultDto> getProcessById(int id) {
        BaseResponse<ResultDto> baseResponse = new BaseResponse<>();
        try{
            ResultEntity resultEntity = testResultRepository.findByPkId(id);
            ResultDto resultDto = testResultConverter.convertToDto(resultEntity);

            baseResponse.setReturnCode(TestProcessCodeEnum.DEFAULT_SUCCESS_CODE.getCode());
            baseResponse.setReturnMessage(TestProcessMessageEnum.SUCCESS__MESSAGE.getMessage());
            baseResponse.addListToTestResults(resultDto);
        }catch (Exception e){
            logger.error("Error: " + e.getMessage());
            baseResponse.setReturnCode(TestProcessCodeEnum.DEFAULT_ERROR_CODE.getCode());
            baseResponse.setReturnMessage(TestProcessMessageEnum.ERROR__MESSAGE.getMessage());
        }
        return baseResponse;
    }


    @Override
    public BaseResponse<TestResultModal> startProcess(RequestModal requestModal) {
        TestResultModal testResultModel = new TestResultModal();
        BaseResponse<TestResultModal> baseResponse = new BaseResponse<>();

        String apiUrl = requestModal.getApiUrl();
        String apiMethodType = requestModal.getApiMethodType();

        testResultModel.setApiUrl(apiUrl);
        testResultModel.setMethodType(apiMethodType.toUpperCase());

        baseResponse.setReturnCode(TestProcessCodeEnum.DEFAULT_SUCCESS_CODE.getCode());
        baseResponse.setReturnMessage(TestProcessMessageEnum.SUCCESS_AUTOMATION_TEST_MESSAGE.getMessage());

        if (!StringUtils.isEmpty(apiUrl) && !StringUtils.isEmpty(apiMethodType)) {
            if (apiMethodType.equals(TestProcessMessageEnum.REQUEST_METHOD_TYPE_GET.getMessage())) {

                qaRunMethodsService.runMethodTypeGet(requestModal, testResultModel);

            } else if (apiMethodType.equals(TestProcessMessageEnum.REQUEST_METHOD_TYPE_POST.getMessage())) {

            }
        } else {
            baseResponse.setReturnCode(TestProcessCodeEnum.ERROR_NO_API_OR_METHOD_TYPE.getCode());
            baseResponse.setReturnMessage(TestProcessMessageEnum.ERROR_NO_METHOD_TYPE_OR_URL.getMessage());
        }

        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        testResultModel.setTestRunTime(localDate.format(formatter));

        baseResponse.addListToTestResults(testResultModel);
        saveResultToDb(testResultModel);
        return baseResponse;
    }

    private void saveResultToDb(TestResultModal testResultModel) {
        ResultEntity resultEntity;
        ResultDto resultDto = new ResultDto();

        resultDto.setServiceUrl(testResultModel.getApiUrl());
        resultDto.setResultStatus(testResultModel.getTestResultStatus());
        resultDto.setResultStatusCode(testResultModel.getTestResultStatusCode());
        resultDto.setProvidedJsonResponseToken(testResultModel.getTestProvidedResponseJson());
        resultDto.setExpectedJsonResponseToken(testResultModel.getTestExpectedResponseJson());
        resultDto.setRequestMethodType(testResultModel.getMethodType());
        resultDto.setRunTime(testResultModel.getTestRunTime());
        resultDto.setTestResultStatusMessage(testResultModel.getTestResultStatusMessage());

        try{
            resultEntity = testResultRepository.save(testResultConverter.convertToEntity(resultDto));
            logger.info("Created DB Object: " + resultEntity);
        }catch (Exception e){
            logger.error("Error for db process: " + e.getMessage());
        }
    }
}
