package com.example.msapiproduct.Controller;

import com.example.msapiproduct.Core.BaseResponse;
import com.example.msapiproduct.Model.CategoryDto;
import com.example.msapiproduct.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product/category")
public class Category {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getAll")
    public ResponseEntity<BaseResponse<CategoryDto>> getAllCategory(){
        return new ResponseEntity<>(categoryService.getAllCategory(), HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<BaseResponse<CategoryDto>> getCategoryById(@RequestParam int id){
        return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<BaseResponse<CategoryDto>> addCategory(@RequestBody CategoryDto categoryDto){
        return new ResponseEntity<>(categoryService.addCategory(categoryDto), HttpStatus.OK);
    }
}
