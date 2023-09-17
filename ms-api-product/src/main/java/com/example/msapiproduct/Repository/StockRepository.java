package com.example.msapiproduct.Repository;

import com.example.msapiproduct.Entity.ProductEntity;
import com.example.msapiproduct.Entity.StockEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends CrudRepository<StockEntity, Integer> {
    List<StockEntity> findAll();
    StockEntity findByPkId(Integer id);
}
