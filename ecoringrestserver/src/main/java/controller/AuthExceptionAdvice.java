package controller;

import error.ErrorResponse;
import error.MemberNotFoundException;
import error.WrongPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("controller")
public class AuthExceptionAdvice {

  @ExceptionHandler(MemberNotFoundException.class)
  public ResponseEntity<ErrorResponse> memberNotFound() {
    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(new ErrorResponse("회원을 찾을 수 없습니다."));
  }

  @ExceptionHandler(WrongPasswordException.class)
  public ResponseEntity<ErrorResponse> wrongPassword() {
    return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(new ErrorResponse("잘못된 비밀번호 입니다."));
  }

}
