package com.phonebook.dto;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    private UUID userId;
    private String operationType;
    private String operationStatus;

    public static UserResponse success(UUID userId, String operationType) {
        return UserResponse.builder().userId(userId).operationType(operationType).operationStatus("success").build();
    }

    public static UserResponse failure(UUID userId, String operationType) {
        return UserResponse.builder().userId(userId).operationType(operationType).operationStatus("failure").build();
    }
}
