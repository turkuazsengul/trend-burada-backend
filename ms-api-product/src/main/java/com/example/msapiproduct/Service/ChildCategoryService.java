package com.example.msapiproduct.Service;

import com.example.msapiproduct.Core.BaseResponse;
import com.example.msapiproduct.Model.ChildCategoryDto;

public interface ChildCategoryService {
    BaseResponse<ChildCategoryDto> getAllCategory();
    BaseResponse<ChildCategoryDto> getCategoryById(Integer pkId);
    BaseResponse<ChildCategoryDto> addCategory(ChildCategoryDto childCategoryDto);
}
