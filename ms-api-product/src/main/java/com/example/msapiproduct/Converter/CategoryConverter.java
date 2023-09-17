package com.example.msapiproduct.Converter;

import com.example.msapiproduct.Core.AbstractBaseConverter;
import com.example.msapiproduct.Entity.CategoryEntity;
import com.example.msapiproduct.Model.CategoryDto;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter extends AbstractBaseConverter<CategoryDto, CategoryEntity> {
}
