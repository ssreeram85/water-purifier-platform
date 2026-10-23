package com.waterpurifier.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;

@ControllerAdvice
public class GlobalException {


    @ExceptionHandler(DuplicateResourceException.class)
    ProblemDetail handleDuplicate(DuplicateResourceException duplicateResourceException){
        return problem(HttpStatus.CONFLICT,"Duplicate resource",duplicateResourceException.getMessage());
    }

    private ProblemDetail problem(HttpStatus status, String title, String message) {
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(status, message);
        detail.setTitle(title);
        detail.setType(URI.create("https://waterpurifier.example.com/problems/" + status.value()));
        return detail;
    }
}
