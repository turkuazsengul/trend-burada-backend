package com.example.msapiproduct.Repository;

import com.example.msapiproduct.Entity.CategoryEntity;
import com.example.msapiproduct.Entity.SubCategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoryRepository extends CrudRepository<SubCategoryEntity,Integer> {
    List<SubCategoryEntity> findAll();
    SubCategoryEntity findByPkId(Integer pkId);
}
