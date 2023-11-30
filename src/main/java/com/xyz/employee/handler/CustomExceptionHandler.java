package com.xyz.employee.handler;

import com.xyz.employee.bean.CustomExceptionBean;
import com.xyz.employee.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> customException(CustomException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomExceptionBean(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }
}
