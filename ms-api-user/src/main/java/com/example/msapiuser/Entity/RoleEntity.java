package com.example.msapiuser.Entity;

import com.example.msapiuser.Core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "ms_role")
public class RoleEntity extends BaseEntity {
    @Column(name = "name")
    private String name;

}
