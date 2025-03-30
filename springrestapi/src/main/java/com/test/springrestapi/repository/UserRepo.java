package com.test.springrestapi.repository;

import com.test.springrestapi.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepo extends CrudRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
    UserEntity getUserById(Long id);
    List<UserEntity> findAll();
}