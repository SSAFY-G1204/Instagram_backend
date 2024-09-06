package com.example.instagram_backend.domain.response;


import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@Builder
public class ApiResponseEntity {
    private boolean success;
    private Object data;

    public static ResponseEntity<ApiResponseEntity> toResponseEntity() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponseEntity.builder()
                        .success(true)
                        .build());
    }

}
