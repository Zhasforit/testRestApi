package com.test.springrestapi.repository;

import com.test.springrestapi.entity.MealEntity;
import com.test.springrestapi.entity.MealEntryEntity;
import com.test.springrestapi.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface MealEntryRepo extends CrudRepository<MealEntryEntity, Long> {
    List<MealEntryEntity> findByUserAndDate(UserEntity user, LocalDate date);
    List<MealEntryEntity> findByUserAndDateBetween(UserEntity user, LocalDate start, LocalDate end);

}
