package com.test.springrestapi.service;

import com.test.springrestapi.entity.UserEntity;
import com.test.springrestapi.enums.Gender;
import com.test.springrestapi.enums.Goal;
import com.test.springrestapi.exception.UserAlreadyExistException;
import com.test.springrestapi.exception.UserNotFoundException;
import com.test.springrestapi.repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CalorieCalculator calorieCalculator;

    public UserEntity createUser(UserEntity user) throws UserAlreadyExistException {
        if (userRepo.findByEmail(user.getEmail()) != null) {
            throw new UserAlreadyExistException("User with this email is already exist");
        }
        // Валидация других полей
        if (user.getAge() <= 0) {
            throw new IllegalArgumentException("Age must be positive");
        }
        if (user.getWeight() <= 0) {
            throw new IllegalArgumentException("Weight must be positive");
        }
        if (user.getHeight() <= 0) {
            throw new IllegalArgumentException("Height must be positive");
        }
        // Установка дефолтных значений для enum
        if (user.getGender() == null) {
            user.setGender(Gender.NOGENDER);
        }
        if (user.getGoal() == null) {
            user.setGoal(Goal.MAINTENANCE);
        }

        user.setDailyCalorieNorm(calorieCalculator.calculateDailyCalorieNorm(user));

        return userRepo.save(user);
    }

    public List<UserEntity> getAllUsers() {
        return userRepo.findAll();
    }

    public UserEntity getOneUser(Long id) throws UserNotFoundException {
        UserEntity user = userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User " + id + " not found"));
        return user;
    }

    public UserEntity updateUser(Long id, UserEntity updatedUser) throws UserNotFoundException {
        UserEntity existingUser = userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User " + id + " not found"));

        if (updatedUser.getName() != null) {
            existingUser.setName(updatedUser.getName());
        }
        if (updatedUser.getAge() > 0) {
            existingUser.setAge(updatedUser.getAge());
        }
        if (updatedUser.getWeight() > 0) {
            existingUser.setWeight(updatedUser.getWeight());
        }
        if (updatedUser.getHeight() > 0) {
            existingUser.setHeight(updatedUser.getHeight());
        }
        if (updatedUser.getGoal() != null) {
            existingUser.setGoal(updatedUser.getGoal());
        }

        existingUser.setDailyCalorieNorm(calorieCalculator.calculateDailyCalorieNorm(existingUser));

        return userRepo.save(existingUser);
    }

    public Long deleteUser(Long id) throws UserNotFoundException {
        userRepo.deleteById(id);
        return id;
    }

}
