package com.home.funny.api;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class RestControllerExceptionAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    private ResponseEntity<ExceptionResponse> handler(Exception ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(500).body(new ExceptionResponse(ex));
    }

    @Data
    public static class ExceptionResponse {
        private String message;
        private String type;

        public ExceptionResponse() {
        }

        public ExceptionResponse(Exception ex) {
            if (ex instanceof DataIntegrityViolationException dataIntegrityViolationException) {
                message = "该数据被其他地方引用，无法删除";
            } else {
                message = ex.getMessage();
            }
            type = ex.getClass().toString();
        }
    }
}
