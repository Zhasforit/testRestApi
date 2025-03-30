package com.test.springrestapi.repository;

import com.test.springrestapi.entity.MealEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MealRepo extends CrudRepository<MealEntity, Long> {
    MealEntity findByName(String name);
    List<MealEntity> findAll();
}
