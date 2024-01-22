package com.example.msapiuser.Repository;

import com.example.msapiuser.Entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Integer> {
    List<UserEntity> findAll();
    UserEntity findByEmail(String username);
    UserEntity findByPkId(int id);
    List<UserEntity> findByIsEnable(boolean isEnabled);
}
