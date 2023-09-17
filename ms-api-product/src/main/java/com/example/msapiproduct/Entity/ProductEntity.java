package com.example.msapiproduct.Entity;

import com.example.msapiproduct.Core.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ms_product")
public class ProductEntity extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private float price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_status_id")
    private StockEntity inventoryStatus;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_category_id")
    private SubCategoryEntity subCategory;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "rating")
    private int rating;

    @Column(name = "image")
    private String image;


}
