package com.test.springrestapi.service;

import com.test.springrestapi.entity.UserEntity;
import com.test.springrestapi.enums.Gender;
import org.springframework.stereotype.Service;

@Service
public class CalorieCalculator {
    public double calculateDailyCalorieNorm(UserEntity user) {
        double bmr;
        if (user.getGender() == Gender.MALE) {
            bmr = 88.362 + (13.397 * user.getWeight()) + (4.799 * user.getHeight()) - (5.677 * user.getAge());
        } else {
            bmr = 447.593 + (9.247 * user.getWeight()) + (3.098 * user.getHeight()) - (4.330 * user.getAge());
        }

        // Учет цели пользователя
        switch (user.getGoal()) {
            case WEIGHT_LOSS:
                return bmr * 0.8; // Уменьшаем норму для похудения
            case MAINTENANCE:
                return bmr;
            case WEIGHT_GAIN:
                return bmr * 1.2; // Увеличиваем норму для набора массы
            default:
                throw new IllegalArgumentException("Неизвестная цель");
        }
    }
}
