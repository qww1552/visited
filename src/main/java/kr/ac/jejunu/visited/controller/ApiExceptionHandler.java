package kr.ac.jejunu.visited.controller;

import kr.ac.jejunu.visited.model.dto.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.util.InputMismatchException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity handleNoSuchElementException(Exception e) {
        return getErrorResponse(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InputMismatchException.class)
    public ResponseEntity handleInputMismatchException(Exception e) {
        return getErrorResponse(e, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleTest(Exception e) {
        return getErrorResponse(e, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity getErrorResponse(Exception e, HttpStatus httpStatus) {
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
                .error(e.getClass().getSimpleName())
                .status(httpStatus.value())
                .message(e.getMessage())
                .build();
        return new ResponseEntity(apiErrorResponse, httpStatus);
    }
}
