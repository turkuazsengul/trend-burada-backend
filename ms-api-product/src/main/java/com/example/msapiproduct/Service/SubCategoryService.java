package com.example.msapiproduct.Service;

import com.example.msapiproduct.Core.BaseResponse;
import com.example.msapiproduct.Model.SubCategoryDto;

public interface SubCategoryService {
    BaseResponse<SubCategoryDto> getAllSubCategory();
    BaseResponse<SubCategoryDto> getSubCategoryById(Integer pkId);
    BaseResponse<SubCategoryDto> addSubCategory(SubCategoryDto subCategoryDto);
}
