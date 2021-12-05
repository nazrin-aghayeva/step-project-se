package com.phonebook.service;

import com.phonebook.entity.UserEntity;
import com.phonebook.dto.UserRequest;
import com.phonebook.dto.UserResponse;
import com.phonebook.repository.UserRepo;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public UserResponse save(UserRequest request) {
        var userEntity = UserEntity.builder().name(request.getName()).phone(request.getPhone()).build();
        try {
            var saved = userRepo.save(userEntity);
            return UserResponse.success(saved.getUserId(), "add");
        } catch (Exception ex) {
            return UserResponse.failure(null, "add");
        }
    }

    public UserResponse edit(UUID userId, UserRequest request) {
        try {
            var userEntity = userRepo.findById(userId).orElseThrow();
            userEntity.setName(request.getName());
            userEntity.setPhone(request.getPhone());
            var saved = userRepo.save(userEntity);
            return UserResponse.success(saved.getUserId(), "edit");
        } catch (Exception ex) {
            return UserResponse.failure(userId, "edit");
        }
    }

    public UserResponse delete(UUID userId) {
        try {
            userRepo.deleteById(userId);
            return UserResponse.success(userId, "delete");
        } catch (Exception ex) {
            return UserResponse.failure(userId, "edit");
        }
    }

    public List<UserEntity> getAllUsers() {
        return userRepo.findAll();
    }
}
