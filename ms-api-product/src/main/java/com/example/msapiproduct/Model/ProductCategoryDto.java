package com.example.msapiproduct.Model;

import com.example.msapiproduct.Core.BaseDto;
import lombok.Data;

@Data
public class ProductCategoryDto extends BaseDto {
    private CategoryDto category;
    private SubCategoryDto subCategory;
}
