package com.example.msapiproduct.Entity;

import com.example.msapiproduct.Core.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "sub_category")
public class SubCategoryEntity extends BaseEntity {
    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(name = "category_tree",
            joinColumns = @JoinColumn(name = "sub_category_id", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "child_category_id", referencedColumnName = "id", nullable = false))
    private List<ChildCategoryEntity> childCategoryList;
}
