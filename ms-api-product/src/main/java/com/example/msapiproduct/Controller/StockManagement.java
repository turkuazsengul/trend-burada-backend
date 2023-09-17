package com.example.msapiproduct.Controller;

import com.example.msapiproduct.Core.BaseResponse;
import com.example.msapiproduct.Model.ProductCategoryDto;
import com.example.msapiproduct.Model.StockDto;
import com.example.msapiproduct.Service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product/stock")
public class StockManagement {

    @Autowired
    private StockService stockService;

    @GetMapping("/getAll")
    public ResponseEntity<BaseResponse<StockDto>> getAllStockStatus(){
        return new ResponseEntity<>(stockService.getAllStockStatus(), HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<BaseResponse<StockDto>> getStockStatusById(@RequestParam int id){
        return new ResponseEntity<>(stockService.getStockStatusById(id), HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<BaseResponse<StockDto>> editStockStatus(@RequestBody StockDto stockDto){
        return new ResponseEntity<>(stockService.addStockStatus(stockDto), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<BaseResponse<StockDto>> addStockStatus(@RequestBody StockDto stockDto){
        return new ResponseEntity<>(stockService.addStockStatus(stockDto), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<BaseResponse<StockDto>> deleteStockStatus(@RequestParam int id){
        return new ResponseEntity<>(stockService.deleteStockStatus(id), HttpStatus.OK);
    }
}
