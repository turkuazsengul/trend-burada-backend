package com.example.apiotomation.Repository;

import com.example.apiotomation.Entity.ResultEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestResultRepository extends CrudRepository<ResultEntity, Integer>{
    List<ResultEntity> findAll();
    ResultEntity findByPkId(Integer pkId);
}
