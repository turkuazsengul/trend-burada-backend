package com.example.msapiproduct.Converter;

import com.example.msapiproduct.Core.AbstractBaseConverter;
import com.example.msapiproduct.Entity.ProductCategoryEntity;
import com.example.msapiproduct.Model.ProductCategoryDto;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryConverter extends AbstractBaseConverter<ProductCategoryDto, ProductCategoryEntity> {
}
