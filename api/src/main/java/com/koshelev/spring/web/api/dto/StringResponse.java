package com.koshelev.spring.web.api.dto;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Обертка для строки")
public class StringResponse {
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public StringResponse(String value) {
        this.value = value;
    }

    public StringResponse() {
    }
}
