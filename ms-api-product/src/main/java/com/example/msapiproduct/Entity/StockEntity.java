package com.example.msapiproduct.Entity;

import com.example.msapiproduct.Core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "stock_status")
public class StockEntity extends BaseEntity {
    @Column(name = "stock_status_name")
    private String stockStatusName;
}
