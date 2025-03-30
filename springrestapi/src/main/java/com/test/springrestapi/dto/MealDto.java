package com.test.springrestapi.dto;

import com.test.springrestapi.entity.MealEntity;

public class MealDto {
    private String name;
    private double caloriesPerServing;
    private double proteins;
    private double fats;
    private double carbs;

    public MealDto(MealEntity meal) {
        this.name = meal.getName();
        this.caloriesPerServing = meal.getCaloriesPerServing();
        this.proteins = meal.getProteins();
        this.fats = meal.getFats();
        this.carbs = meal.getCarbs();
    }

    public String getName() {
        return name;
    }
}
