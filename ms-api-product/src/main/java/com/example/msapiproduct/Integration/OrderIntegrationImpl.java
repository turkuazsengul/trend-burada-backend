package com.example.msapiproduct.Integration;

import com.example.msapiproduct.FeignClient.Model.OrderDto;
import com.example.msapiproduct.FeignClient.Repository.OrderClient;
import com.example.msapiproduct.Model.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderIntegrationImpl implements OrderIntegration{

    @Autowired
    private OrderClient orderClient;

    @Override
    public OrderDto addOrder(ProductDto productDto, int user_id) {
        return orderClient.addOrder(productDto,user_id);
    }
}
