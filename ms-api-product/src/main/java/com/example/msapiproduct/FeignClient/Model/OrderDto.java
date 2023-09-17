package com.example.msapiproduct.FeignClient.Model;

import com.example.msapiproduct.Core.BaseDto;
import com.example.msapiproduct.Model.ProductDto;
import lombok.Data;

@Data
public class OrderDto extends BaseDto {

    private int userId;
    private String orderDate;
    private boolean isActive;
    private ProductDto product;
}
