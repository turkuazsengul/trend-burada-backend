package com.example.msapiproduct.Repository;

import com.example.msapiproduct.Entity.CategoryEntity;
import com.example.msapiproduct.Entity.ProductCategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryRepository extends CrudRepository<ProductCategoryEntity,Integer> {
    List<ProductCategoryEntity> findAll();
    ProductCategoryEntity findByPkId(Integer pkId);
}
