package com.example.msapiproduct.Controller;

import com.example.msapiproduct.Core.BaseResponse;
import com.example.msapiproduct.Model.ProductDto;
import com.example.msapiproduct.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product/info")
public class ProductQuery {

    @Autowired
    private ProductService productService;

    @GetMapping("/getAllProduct")
    public ResponseEntity<BaseResponse<ProductDto>> getAllProduct(){
        return new ResponseEntity<>(productService.gelAllProduct(), HttpStatus.OK);
    }
}
