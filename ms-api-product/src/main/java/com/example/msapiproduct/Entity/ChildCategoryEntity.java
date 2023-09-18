package com.example.msapiproduct.Entity;

import com.example.msapiproduct.Core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "child_category")
public class ChildCategoryEntity extends BaseEntity {
    @Column(name = "name")
    private String name;
}
