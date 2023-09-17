package com.example.msapiproduct.Converter;

import com.example.msapiproduct.Core.AbstractBaseConverter;
import com.example.msapiproduct.Entity.ProductEntity;
import com.example.msapiproduct.Model.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter extends AbstractBaseConverter<ProductDto, ProductEntity> {
}
