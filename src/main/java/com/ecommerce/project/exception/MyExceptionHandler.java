package com.ecommerce.project.exception;

//エラーレスポンスをカスタムで設定する

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

//@RestControllerAdvice(コントローラークラスで発生した例外をインターセプトする)
@RestControllerAdvice
public class MyExceptionHandler {

    //MethodArgumentNotValidException例外をインターセプトする
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> myMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        //レスポンスを格納するハッシュマップを作成
        Map<String, String> response = new HashMap<>();

        //例外を取得、レスポンス変数に格納する
        e.getBindingResult().getAllErrors().forEach(err -> {
            String fieldName = ((FieldError)err).getField();
            String message = err.getDefaultMessage();

            response.put(fieldName, message);
        });

        //エラーステータス返却のためレスポンスエンティティを返す
        return new ResponseEntity<Map<String, String>>(response, HttpStatus.BAD_REQUEST);
    }
}
