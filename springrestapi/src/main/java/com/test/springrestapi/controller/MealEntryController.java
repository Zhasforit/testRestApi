package com.test.springrestapi.controller;

import com.test.springrestapi.entity.MealEntryEntity;
import com.test.springrestapi.enums.MealType;
import com.test.springrestapi.exception.UserNotFoundException;
import com.test.springrestapi.service.MealEntryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/entries")
public class MealEntryController {
    private final MealEntryService mealEntryService;

    public MealEntryController(MealEntryService mealEntryService) {
        this.mealEntryService = mealEntryService;
    }

    @PostMapping
    public ResponseEntity<MealEntryEntity> createEntry(
            @RequestParam Long userId,
            @RequestParam Set<Long> mealIds,
            @RequestParam MealType mealType) throws UserNotFoundException {
        return ResponseEntity.ok(mealEntryService.createMealEntry(userId, mealIds, mealType));
    }
}
