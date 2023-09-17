package com.example.msapiproduct.Service;

import com.example.msapiproduct.Core.BaseResponse;
import com.example.msapiproduct.Model.ProductCategoryDropDownItem;
import com.example.msapiproduct.Model.ProductCategoryDto;

public interface ProductCategoryService {
    BaseResponse<ProductCategoryDto> getAllProductCategory();
    BaseResponse<ProductCategoryDto> getProductCategoryById(Integer pkId);
    BaseResponse<ProductCategoryDto> addProductCategory(ProductCategoryDto productCategoryDto);
    BaseResponse<ProductCategoryDropDownItem> getProductCategoryDropDownItem();

}
