package com.example.msapiproduct.Entity;

import com.example.msapiproduct.Core.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "stock_status")
public class StockEntity extends BaseEntity {
    @Column(name = "stock_status_name")
    private String stockStatusName;
}
