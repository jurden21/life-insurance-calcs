package ru.jurden.calcs.controllers;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.jurden.calcs.entities.ErrorResponse;
import ru.jurden.calcs.enums.ErrorCode;

@Slf4j
@RestControllerAdvice
@NoArgsConstructor
public class ExceptionHandlerController {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableExceptions() {
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INCORRECT_BODY);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        ErrorResponse errorResponse = ErrorResponse.of(ex.getBindingResult());
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
