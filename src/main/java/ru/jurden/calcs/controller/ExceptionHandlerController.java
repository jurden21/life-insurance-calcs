package ru.jurden.calcs.controller;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.jurden.calcs.controller.dto.ResultResponse;
import ru.jurden.calcs.enums.ErrorCode;

@Slf4j
@RestControllerAdvice
@NoArgsConstructor
public class ExceptionHandlerController {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResultResponse> handleHttpMessageNotReadableExceptions() {
        ResultResponse resultResponse = ResultResponse.of(ErrorCode.INCORRECT_BODY);
        return ResponseEntity.badRequest().body(resultResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        ResultResponse resultResponse = ResultResponse.of(ex.getBindingResult());
        return ResponseEntity.badRequest().body(resultResponse);
    }
}
