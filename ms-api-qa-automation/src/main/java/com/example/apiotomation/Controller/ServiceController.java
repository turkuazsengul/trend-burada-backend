package com.example.apiotomation.Controller;

import com.example.apiotomation.Dto.ResultDto;
import com.example.apiotomation.Service.QaAutomationService;
import com.example.apiotomation.Core.BaseResponse;
import com.example.apiotomation.Modal.RequestModal;
import com.example.apiotomation.Modal.TestResultModal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/otomasyon")
public class ServiceController {

    @Autowired
    private QaAutomationService qaAutomationService;

    @GetMapping("/getAllResults")
    public ResponseEntity<BaseResponse<ResultDto>> getResultAll() {
        return new ResponseEntity<>(qaAutomationService.getAllProcess(), HttpStatus.OK);
    }

    @GetMapping("/getResult")
    public ResponseEntity<BaseResponse<ResultDto>> getResultById(@RequestParam int id) {
        return new ResponseEntity<>(qaAutomationService.getProcessById(id), HttpStatus.OK);
    }

    @PostMapping("/start")
    public ResponseEntity<BaseResponse<TestResultModal>> startProcess(@RequestBody RequestModal requestModal){

        return new ResponseEntity<>(qaAutomationService.startProcess(requestModal), HttpStatus.OK);
    }
}
