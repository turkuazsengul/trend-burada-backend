package com.example.msapiproduct.Entity;

import com.example.msapiproduct.Core.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "category")
public class CategoryEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "sort_order")
    private int sortOrder;

    @ManyToMany
    @JoinTable(name = "category_tree",
            joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "sub_category_id", referencedColumnName = "id", nullable = false))
    private List<SubCategoryEntity> subCategoryList;
}
