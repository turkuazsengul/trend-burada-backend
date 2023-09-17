package com.example.msapiproduct.Repository;

import com.example.msapiproduct.Entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
    List<ProductEntity> findAll();
    ProductEntity findByPkId(Integer id);

}
