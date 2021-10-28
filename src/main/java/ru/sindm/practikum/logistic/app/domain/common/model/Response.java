package ru.sindm.practikum.logistic.app.domain.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response<T> {
    @JsonProperty(value = "code", required = true)
    public Integer code;

    @JsonProperty(value = "error", required = true)
    public String error = null;

    @JsonProperty(value = "body", required = true)
    public T body = null;

    public Response(Integer code, String error) {
        this.code = code;
        this.error = error;
    }

    public Response(Integer code, T body) {
        this.code = code;
        this.body = body;
    }

}
