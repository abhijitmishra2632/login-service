package com.cosmos.error;

import com.cosmos.model.JWTResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(PasswordError.class)
    public ResponseEntity<JWTResponse> passwordNotMatching(PasswordError exception, WebRequest request){
        JWTResponse jWTResponse = new JWTResponse(HttpStatus.NOT_FOUND,"",exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(jWTResponse);
    }
}
