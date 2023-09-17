package com.example.msapiproduct.Controller;

import com.example.msapiproduct.Core.BaseResponse;
import com.example.msapiproduct.Model.ProductDto;
import com.example.msapiproduct.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;

@RestController
@RequestMapping("/api/v1/product/management")
public class ProductManagement {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<BaseResponse<ProductDto>> createProduct(@RequestBody ProductDto productDto){
        return new ResponseEntity<>(productService.addProduct(productDto), HttpStatus.OK);
    }

    @PostMapping("/addProduct2Order/{productId}")
    public ResponseEntity<String> addProduct2Order(@RequestBody ProductDto productDto, @PathVariable("productId") int userId){
        return new ResponseEntity<>(productService.addProduct2Order(productDto,userId), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<BaseResponse<ProductDto>> deleteProduct(@RequestParam("productId") int productId){
        return new ResponseEntity<>(productService.deleteProduct(productId), HttpStatus.OK);
    }
}
