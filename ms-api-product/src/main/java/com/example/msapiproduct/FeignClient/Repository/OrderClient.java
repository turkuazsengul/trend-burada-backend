package com.example.msapiproduct.FeignClient.Repository;

import com.example.msapiproduct.FeignClient.Model.OrderDto;
import com.example.msapiproduct.Model.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(url = "http://apigateway:8080",name = "order-api")
public interface OrderClient {

    @PostMapping("/api/v1/order/management/addOrder/{user_id}")
    OrderDto addOrder(ProductDto productDto, @PathVariable int user_id);
}
