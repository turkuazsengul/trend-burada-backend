package com.example.msapiproduct.Service;

import com.example.msapiproduct.Core.BaseResponse;
import com.example.msapiproduct.Model.ProductDto;

public interface ProductService {
    BaseResponse<ProductDto> addProduct(ProductDto productDto);
    BaseResponse<ProductDto> gelAllProduct();
    BaseResponse<ProductDto> deleteProduct(int productId);
    String addProduct2Order(ProductDto productDto, int userId);
}
