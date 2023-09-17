package com.example.msapiproduct.Model;

import com.example.msapiproduct.Core.BaseDto;
import lombok.Data;

import java.util.List;

@Data
public class SubCategoryDto extends BaseDto {
    private String name;
    private List<ChildCategoryDto> childCategoryList;
}
