package com.example.msapiproduct.Controller;

import com.example.msapiproduct.Core.BaseResponse;
import com.example.msapiproduct.Model.ChildCategoryDto;
import com.example.msapiproduct.Service.ChildCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product/childCategory")
public class ChildCategory {
    @Autowired
    private ChildCategoryService childCategoryService;

    @GetMapping("/getAll")
    public ResponseEntity<BaseResponse<ChildCategoryDto>> getAllChildCategory(){
        return new ResponseEntity<>(childCategoryService.getAllCategory(), HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<BaseResponse<ChildCategoryDto>> getChildCategoryById(@RequestParam int id){
        return new ResponseEntity<>(childCategoryService.getCategoryById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<BaseResponse<ChildCategoryDto>> addChildCategory(@RequestBody ChildCategoryDto childCategoryDto){
        return new ResponseEntity<>(childCategoryService.addCategory(childCategoryDto), HttpStatus.OK);
    }
}
