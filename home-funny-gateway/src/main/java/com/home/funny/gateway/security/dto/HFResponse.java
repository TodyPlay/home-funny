package com.home.funny.gateway.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HFResponse<T> {
    private int code;
    private String message;
    private T data;

    public static <T> HFResponse<T> ok(T data, String message) {
        return new HFResponse<>(200, message, data);
    }

    public static <T> HFResponse<T> ok(T data) {
        return ok(data, "");
    }

    public static <T> HFResponse<T> unAuthorization(String message) {
        return new HFResponse<>(401, message, null);
    }
}
