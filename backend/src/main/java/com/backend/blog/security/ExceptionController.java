package com.backend.blog.security;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // 전역 controller 예외처리 작업
public class ExceptionController {
    @ExceptionHandler(Exception.class)
    public JSONObject ServerException(Exception e) {
        JSONObject data = new JSONObject();
        data.put("result", false);
        data.put("message", e.getMessage());
        return data;
    }
}
