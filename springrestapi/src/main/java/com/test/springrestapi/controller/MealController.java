package com.test.springrestapi.controller;

import com.test.springrestapi.entity.MealEntity;
import com.test.springrestapi.exception.MealAlreadyExistException;
import com.test.springrestapi.exception.MealNotFoundException;
import com.test.springrestapi.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/meals")
public class MealController {

    @Autowired
    private MealService mealService;

    @GetMapping
    public ResponseEntity<Iterable<MealEntity>> getAllMeals() {
        return ResponseEntity.ok(mealService.getAllMeals());
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneMeal(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(mealService.getOneMeal(id));
        } catch (MealNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error, bad request");
        }
    }

    @PostMapping
    public ResponseEntity createMeal(@RequestBody MealEntity meal) {
        try {
            mealService.createMeal(meal);
            return ResponseEntity.ok("Meal " + meal.getName() + " created");
        } catch (MealAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error, bad request");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateMeal(@PathVariable Long id, @RequestBody MealEntity updatedMeal) throws MealNotFoundException {
        return ResponseEntity.ok(mealService.updateMeal(id, updatedMeal));
    }

    // Пишет что удалил уже не существующее блюдо
    @DeleteMapping("/{id}")
    public ResponseEntity deleteMeal(@PathVariable Long id) {
        try {
            return ResponseEntity.ok("Meal " + mealService.deleteMeal(id) + " deleted");
        }  catch (Exception e) {
            return ResponseEntity.badRequest().body("Error, bad request");
        }
    }

    }
