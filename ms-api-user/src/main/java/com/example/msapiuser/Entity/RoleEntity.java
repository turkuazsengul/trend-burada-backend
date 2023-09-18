package com.example.msapiuser.Entity;

import com.example.msapiuser.Core.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "ms_role")
public class RoleEntity extends BaseEntity {
    @Column(name = "name")
    private String name;

}
