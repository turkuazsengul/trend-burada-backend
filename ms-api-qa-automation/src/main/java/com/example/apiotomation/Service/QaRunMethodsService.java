package com.example.apiotomation.Service;

import com.example.apiotomation.Modal.RequestModal;
import com.example.apiotomation.Modal.TestResultModal;

public interface QaRunMethodsService {
    void runMethodTypeGet(RequestModal requestModal, TestResultModal testResultModel);
    void runMethodTypePost(RequestModal requestModal, TestResultModal testResultModel);
}
