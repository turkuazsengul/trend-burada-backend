package com.example.msapiproduct.Model;

import com.example.msapiproduct.Core.BaseDto;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDto extends BaseDto {
    private String name;
    private int sortOrder;
    private List<SubCategoryDto> subCategoryList;
}
