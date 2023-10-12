package com.example.apiotomation.Service;

import com.example.apiotomation.Enum.TestProcessMessageEnum;
import com.example.apiotomation.Modal.RequestModal;
import com.example.apiotomation.Modal.TestResultModal;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

@Component
public class QaRunMethodsServiceImpl implements QaRunMethodsService {

    Logger logger = LoggerFactory.getLogger(QaAutomationServiceImpl.class);

    public void runMethodTypeGet(RequestModal requestModal, TestResultModal testResultModel) {
        RestTemplate restTemplate = new RestTemplate();

        String apiUrl = requestModal.getApiUrl();
        String expectedResultJsonToken = requestModal.getExpectedResultJsonToken();

        try {
            logger.info("restTemplate request info: uri=" + apiUrl + "  ===>  request method type: " + TestProcessMessageEnum.REQUEST_METHOD_TYPE_GET.getMessage());
            ResponseEntity<String> resultRestTemplate = restTemplate.getForEntity(apiUrl, String.class);

            int statusCode = resultRestTemplate.getStatusCodeValue();
            String responseBody = resultRestTemplate.getBody();
            logger.info("Service Response: " + responseBody);

            if (!StringUtils.isEmpty(expectedResultJsonToken) && !StringUtils.isEmpty(responseBody)) {
                boolean isSameToken = checkResponseJsonToken(responseBody, expectedResultJsonToken, testResultModel);

                if (isSameToken) {
                    testResultModel.setTestResultStatus(TestProcessMessageEnum.SUCCESS_RUN_TEST_RESULT.getMessage());
                    testResultModel.setTestResultStatusMessage(TestProcessMessageEnum.SUCCESS_CHECK_DATA_LIST.getMessage());
                } else {
                    testResultModel.setTestResultStatus(TestProcessMessageEnum.ERROR_RUN_TEST_RESULT.getMessage());
                    testResultModel.setTestResultStatusMessage(TestProcessMessageEnum.ERROR_CHECK_DATA_LIST.getMessage());
                }
            } else {
                testResultModel.setTestResultStatus(TestProcessMessageEnum.SUCCESS_RUN_TEST_RESULT.getMessage());
                testResultModel.setTestResultStatusMessage(TestProcessMessageEnum.SUCCESS_WITH_NO_CHECK_DATA_LIST.getMessage());
            }

            testResultModel.setTestResultStatusCode(statusCode);
        } catch (HttpClientErrorException e) {
            logger.error("restTemplate run error: " + e);
            testResultModel.setTestResultStatusCode(e.getRawStatusCode());
            testResultModel.setTestResultStatus(TestProcessMessageEnum.ERROR_RUN_TEST_RESULT.getMessage());
            testResultModel.setTestResultStatusMessage(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            testResultModel.setTestResultStatusCode(500);
            testResultModel.setTestResultStatus(TestProcessMessageEnum.ERROR_RUN_TEST_RESULT.getMessage());
        }

    }

    @Override
    public void runMethodTypePost(RequestModal requestModal, TestResultModal testResultModel) {
        RestTemplate restTemplate = new RestTemplate();

        String apiUrl = requestModal.getApiUrl();
        String expectedResultJsonToken = requestModal.getExpectedResultJsonToken();

        try {
            logger.info("restTemplate request info: uri=" + apiUrl + "  ===>  request method type: " + TestProcessMessageEnum.REQUEST_METHOD_TYPE_GET.getMessage());
            ResponseEntity<String> resultRestTemplate = restTemplate.getForEntity(apiUrl, String.class);

            int statusCode = resultRestTemplate.getStatusCodeValue();
            String responseBody = resultRestTemplate.getBody();
            logger.info("Service Response: " + responseBody);

            if (!StringUtils.isEmpty(expectedResultJsonToken) && !StringUtils.isEmpty(responseBody)) {
                boolean isSameToken = checkResponseJsonToken(responseBody, expectedResultJsonToken, testResultModel);

                if (isSameToken) {
                    testResultModel.setTestResultStatus(TestProcessMessageEnum.SUCCESS_RUN_TEST_RESULT.getMessage());
                    testResultModel.setTestResultStatusMessage(TestProcessMessageEnum.SUCCESS_CHECK_DATA_LIST.getMessage());
                } else {
                    testResultModel.setTestResultStatus(TestProcessMessageEnum.ERROR_RUN_TEST_RESULT.getMessage());
                    testResultModel.setTestResultStatusMessage(TestProcessMessageEnum.ERROR_CHECK_DATA_LIST.getMessage());
                }
            } else {
                testResultModel.setTestResultStatus(TestProcessMessageEnum.SUCCESS_RUN_TEST_RESULT.getMessage());
                testResultModel.setTestResultStatusMessage(TestProcessMessageEnum.SUCCESS_WITH_NO_CHECK_DATA_LIST.getMessage());
            }

            testResultModel.setTestResultStatusCode(statusCode);
        } catch (HttpClientErrorException e) {
            logger.error("restTemplate run error: " + e);
            testResultModel.setTestResultStatusCode(e.getRawStatusCode());
            testResultModel.setTestResultStatus(TestProcessMessageEnum.ERROR_RUN_TEST_RESULT.getMessage());
            testResultModel.setTestResultStatusMessage(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            testResultModel.setTestResultStatusCode(500);
            testResultModel.setTestResultStatus(TestProcessMessageEnum.ERROR_RUN_TEST_RESULT.getMessage());
        }

    }

    public boolean checkResponseJsonToken(String responseJsonBody, String expectedJsonResponse, TestResultModal testResultModel) {
        /*
         * Client beklenen json örneğini tüm boşlukları silerek encode işlemi yapıcak. Aynı işlemi servis response
         * yaparak tokenların karşılaştırılması yapılıyor.
         */
        responseJsonBody = responseJsonBody.replaceAll(" ", "");
        String expectedJsonBody = expectedJsonResponse.replaceAll("\n", "");
        expectedJsonBody = expectedJsonBody.replaceAll(" ", "");

        String encodedResponseBody = Base64.getEncoder().encodeToString(responseJsonBody.getBytes());
        String encodedExpectedJson = Base64.getEncoder().encodeToString(expectedJsonBody.getBytes());

        logger.info(encodedResponseBody);
        logger.info(encodedExpectedJson);
        testResultModel.setTestProvidedResponseJson(encodedResponseBody);
        testResultModel.setTestExpectedResponseJson(encodedExpectedJson);

        return encodedResponseBody.equals(encodedExpectedJson);
    }
}
