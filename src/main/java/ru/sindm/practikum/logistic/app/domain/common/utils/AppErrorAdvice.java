package ru.sindm.practikum.logistic.app.domain.common.utils;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.sindm.practikum.logistic.app.domain.exception.DeliveryPriceCalculateException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AppErrorAdvice {

    @ExceptionHandler(DeliveryPriceCalculateException.class)
    public ResponseEntity<?> handleDeliveryPriceCalculateException(DeliveryPriceCalculateException e) {
        return buildResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return buildResponseEntity(
                e.getBindingResult()
                        .getFieldErrors()
                        .stream().findFirst()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<?> buildResponseEntity(Object error, HttpStatus status) {
        Map<String, Object> body = new HashMap<>() {{
            put("error", error);
        }};
        return new ResponseEntity<>(body, status);
    }
}