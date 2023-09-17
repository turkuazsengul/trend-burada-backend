package com.example.msapiproduct.Integration;

import com.example.msapiproduct.FeignClient.Model.OrderDto;
import com.example.msapiproduct.Model.ProductDto;

public interface OrderIntegration {
    OrderDto addOrder(ProductDto productDto, int user_id);
}
