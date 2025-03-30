package com.test.springrestapi.service;

import com.test.springrestapi.entity.MealEntity;
import com.test.springrestapi.entity.MealEntryEntity;
import com.test.springrestapi.entity.UserEntity;
import com.test.springrestapi.enums.MealType;
import com.test.springrestapi.exception.MealNotFoundException;
import com.test.springrestapi.exception.UserNotFoundException;
import com.test.springrestapi.repository.MealEntryRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MealEntryService {
    private final MealEntryRepo mealEntryRepo;
    private final UserService userService;
    private final MealService mealService;

    public MealEntryService(MealEntryRepo mealEntryRepo,
                            UserService userService,
                            MealService mealService
    ) {
        this.mealEntryRepo = mealEntryRepo;
        this.userService = userService;
        this.mealService = mealService;
    }

    @Transactional
    public MealEntryEntity createMealEntry(Long userId, Set<Long> mealIds, MealType mealType) throws UserNotFoundException {
        UserEntity user = userService.getOneUser(userId);
        Set<MealEntity> meals = mealIds.stream()
                .map(id -> {
                    try {
                        return mealService.getOneMeal(id);
                    } catch (MealNotFoundException e) {
                        throw new RuntimeException(e); // Обернуть в unchecked исключение
                    }
                })
                .collect(Collectors.toSet());

        MealEntryEntity entry = new MealEntryEntity();
        entry.setUser(user);
        entry.setMeals(meals);
        entry.setMealType(mealType);
        entry.setDate(LocalDate.now());

        return mealEntryRepo.save(entry);
    }

}
