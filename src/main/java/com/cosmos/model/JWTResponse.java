package com.cosmos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JWTResponse {
    private HttpStatus status;
    private String jwtToken;
    private String errorMessage;
}
