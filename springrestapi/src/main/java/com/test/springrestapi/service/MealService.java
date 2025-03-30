package com.test.springrestapi.service;

import com.test.springrestapi.dto.MealDto;
import com.test.springrestapi.entity.MealEntity;
import com.test.springrestapi.exception.MealAlreadyExistException;
import com.test.springrestapi.exception.MealNotFoundException;
import com.test.springrestapi.repository.MealRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealService {

    @Autowired
    private MealRepo mealRepo;

    public MealEntity createMeal(MealEntity meal) throws MealAlreadyExistException {
        if (mealRepo.findByName(meal.getName()) != null) {
            throw new MealAlreadyExistException("This meal is already exist");
        }
        return mealRepo.save(meal);
    }

    public List<MealEntity> getAllMeals() {
        return mealRepo.findAll();
    }

    public MealEntity getOneMeal(Long id) throws MealNotFoundException {
        MealEntity meal = mealRepo.findById(id)
            .orElseThrow(() -> new MealNotFoundException("Meal " + id + " not found"));
        return meal;
    }

    public MealEntity updateMeal(Long id, MealEntity updatedMeal) throws MealNotFoundException {
        MealEntity existingMeal = mealRepo.findById(id)
                .orElseThrow(() -> new MealNotFoundException("User " + id + " not found"));

        if (updatedMeal.getName() != null) {
            existingMeal.setName(updatedMeal.getName());
        }
        if (updatedMeal.getCaloriesPerServing() > 0) {
            existingMeal.setCaloriesPerServing(updatedMeal.getCaloriesPerServing());
        }
        if (updatedMeal.getProteins() > 0) {
            existingMeal.setProteins(updatedMeal.getProteins());
        }
        if (updatedMeal.getCarbs() > 0) {
            existingMeal.setCarbs(updatedMeal.getCarbs());
        }
        if (updatedMeal.getFats() > 0) {
            existingMeal.setFats(updatedMeal.getFats());
        }

        return mealRepo.save(existingMeal);
    }

    public Long deleteMeal(Long id) throws MealNotFoundException {
        mealRepo.deleteById(id);
        return id;
    }
}
