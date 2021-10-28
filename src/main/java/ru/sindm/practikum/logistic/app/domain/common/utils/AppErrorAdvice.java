package ru.sindm.practikum.logistic.app.domain.common.utils;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.sindm.practikum.logistic.app.domain.common.model.Response;
import ru.sindm.practikum.logistic.app.domain.exception.DeliveryPriceCalculateException;

@RestControllerAdvice
public class AppErrorAdvice {

    @ExceptionHandler(DeliveryPriceCalculateException.class)
    public Response<?> handleDeliveryPriceCalculateException(DeliveryPriceCalculateException e) {
        return buildResponseEntity(500, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return buildResponseEntity(400,
                e.getBindingResult()
                        .getFieldErrors()
                        .stream().findFirst()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage).get());
    }

    private Response<?> buildResponseEntity(Integer code, String error) {
        return new Response<>(code, error);
    }
}