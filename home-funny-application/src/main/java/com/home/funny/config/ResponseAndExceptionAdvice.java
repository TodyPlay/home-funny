package com.home.funny.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
@Slf4j
public class ResponseAndExceptionAdvice implements ResponseBodyAdvice<Object> {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    private ResponseEntity<ExceptionResponse> handler(Exception ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(500).body(new ExceptionResponse(ex));
    }

    @Override
    public boolean supports(@NotNull MethodParameter returnType, @NotNull Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  @NotNull MethodParameter returnType,
                                  @NotNull MediaType selectedContentType,
                                  @NotNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  @NotNull ServerHttpRequest request,
                                  @NotNull ServerHttpResponse response) {
        return body;
    }

    @Data
    public static class ExceptionResponse {
        private String message;
        private String type;

        public ExceptionResponse() {
        }

        public ExceptionResponse(Exception ex) {
            message = ex.getMessage();
            type = ex.getClass().toString();
        }
    }
}
