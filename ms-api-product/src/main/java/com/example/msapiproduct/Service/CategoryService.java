package com.example.msapiproduct.Service;

import com.example.msapiproduct.Core.BaseResponse;
import com.example.msapiproduct.Model.CategoryDto;
import com.example.msapiproduct.Model.ProductCategoryDto;
import com.example.msapiproduct.Model.SubCategoryDto;

public interface CategoryService {
    BaseResponse<CategoryDto> getAllCategory();
    BaseResponse<CategoryDto> getCategoryById(Integer pkId);
    BaseResponse<CategoryDto> addCategory(CategoryDto categoryDto);
}
