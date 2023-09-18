package com.example.msapiuser.Repository;

import com.example.msapiuser.Entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Integer> {
    List<UserEntity> findAll();
    UserEntity findByEmailAndPassword(String username, String password);
    UserEntity findByEmail(String username);
    int findByConfirmCode(int code);
    UserEntity findByPkId(int id);
    List<UserEntity> findByIsEnable(boolean isEnabled);
}
