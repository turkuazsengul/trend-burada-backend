package com.example.msapiuser.ScheduledOperations;

import com.example.msapiuser.Entity.UserEntity;
import com.example.msapiuser.Repository.UserRepository;
import com.example.msapiuser.Service.Impl.AuthServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class RegisterScheduledOperations {
//    private final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Scheduled(cron = "2 * * * * ", zone = "Europe/Istanbul")
//    public void registerDisableUserCheck() {
//        try {
//            List<UserEntity> userEntityList = userRepository.findByIsEnable(false);
//
//            for (UserEntity user : userEntityList) {
//                long timeDifference = new Date().getTime() - user.getCreatedTime().getTime();
//                int secondDifference = (int) (timeDifference / (1000 * 60)) % 60;
//                if (secondDifference > 2) {
//                    userRepository.delete(user);
//                }
//            }
//
//        } catch (Exception e) {
//            logger.error("Register Disable User Check Precess Error: " + e);
//        }
//    }
}
