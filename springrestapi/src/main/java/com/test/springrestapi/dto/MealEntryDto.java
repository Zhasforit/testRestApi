package com.test.springrestapi.dto;

import com.test.springrestapi.entity.MealEntryEntity;
import com.test.springrestapi.enums.MealType;

import java.time.LocalDate;
import java.util.List;

public class MealEntryDto {
    private Long id;
    private LocalDate date;
    private MealType mealType;
    private List<MealDto> meals;

    public MealEntryDto(MealEntryEntity entity) {
        this.id = entity.getId();
        this.date = entity.getDate();
        this.meals = entity.getMeals().stream()
                .map(MealDto::new)
                .toList();
    }
}
