package com.example.apiotomation.Service;

import com.example.apiotomation.Core.BaseResponse;
import com.example.apiotomation.Modal.RequestModal;
import com.example.apiotomation.Dto.ResultDto;
import com.example.apiotomation.Modal.TestResultModal;

public interface QaAutomationService {
    BaseResponse<TestResultModal> startProcess(RequestModal requestModal);
    BaseResponse<ResultDto> getAllProcess();
    BaseResponse<ResultDto> getProcessById(int id);
}
