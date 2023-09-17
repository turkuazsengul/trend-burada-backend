package com.example.msapiproduct.Controller;

import com.example.msapiproduct.Core.BaseResponse;
import com.example.msapiproduct.Model.ProductCategoryDropDownItem;
import com.example.msapiproduct.Model.ProductCategoryDto;
import com.example.msapiproduct.Service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product/productCategory")
public class ProductCategory {

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/getAll")
    public ResponseEntity<BaseResponse<ProductCategoryDto>> getAllProductCategory(){
        return new ResponseEntity<>(productCategoryService.getAllProductCategory(), HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<BaseResponse<ProductCategoryDto>> getProductCategoryById(@RequestParam int id){
        return new ResponseEntity<>(productCategoryService.getProductCategoryById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<BaseResponse<ProductCategoryDto>> addProductCategory(@RequestBody ProductCategoryDto productCategoryDto){
        return new ResponseEntity<>(productCategoryService.addProductCategory(productCategoryDto), HttpStatus.OK);
    }

    @GetMapping("/getDropdownItem")
    public ResponseEntity<BaseResponse<ProductCategoryDropDownItem>> getProductCategoryDropDownItem(){
        return new ResponseEntity<>(productCategoryService.getProductCategoryDropDownItem(), HttpStatus.OK);
    }

}
