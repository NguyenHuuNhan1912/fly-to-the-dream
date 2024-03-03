package com.ten.ten.commons;

import com.ten.ten.constants.CodeResponseConstant;
import com.ten.ten.constants.MessageResponseConstant;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult {

    private Integer code;

    private Object data;

    private String message;

    public ResponseResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    public ResponseEntity<ResponseResult> ResponseResult(HttpStatus httpStatus, Integer code, Object data, String message) {
        return ResponseEntity.status(httpStatus)
                .body(new ResponseResult(code, data, message));
    }
}
