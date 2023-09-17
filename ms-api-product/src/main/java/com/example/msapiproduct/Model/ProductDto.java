package com.example.msapiproduct.Model;

import com.example.msapiproduct.Core.BaseDto;
import lombok.Data;

@Data
public class ProductDto extends BaseDto {
    private String name;
    private float price;
    private StockDto inventoryStatus;
    private String description;
    private CategoryDto category;
    private SubCategoryDto subCategory;
    private int quantity;
    private int rating;
    private String image;

}
