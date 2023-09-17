package com.example.msapiproduct.Entity;

import com.example.msapiproduct.Core.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "child_category")
public class ChildCategoryEntity extends BaseEntity {
    @Column(name = "name")
    private String name;
}
