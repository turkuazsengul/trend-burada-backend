package com.example.msapiproduct.Repository;

import com.example.msapiproduct.Entity.CategoryEntity;
import com.example.msapiproduct.Entity.ChildCategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChildCategoryRepository extends CrudRepository<ChildCategoryEntity,Integer> {
    List<ChildCategoryEntity> findAll();
    ChildCategoryEntity findByPkId(Integer pkId);
}
