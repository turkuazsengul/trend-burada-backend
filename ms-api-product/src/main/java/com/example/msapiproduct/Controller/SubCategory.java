package com.example.msapiproduct.Controller;

import com.example.msapiproduct.Core.BaseResponse;
import com.example.msapiproduct.Model.SubCategoryDto;
import com.example.msapiproduct.Service.CategoryService;
import com.example.msapiproduct.Service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product/subCategory")
public class SubCategory {

    @Autowired
    private SubCategoryService subCategoryService;

    @GetMapping("getAll")
    public ResponseEntity<BaseResponse<SubCategoryDto>> getAllSubCategory(){
        return new ResponseEntity<>(subCategoryService.getAllSubCategory(), HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<BaseResponse<SubCategoryDto>> getSubCategoryById(@RequestParam int id){
        return new ResponseEntity<>(subCategoryService.getSubCategoryById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<BaseResponse<SubCategoryDto>> addSubCategory(@RequestBody SubCategoryDto subCategoryDto){
        return new ResponseEntity<>(subCategoryService.addSubCategory(subCategoryDto), HttpStatus.OK);
    }

}
