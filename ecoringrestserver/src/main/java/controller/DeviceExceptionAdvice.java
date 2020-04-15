package controller;

import error.DeviceNotFoundException;
import error.DuplicateAddressException;
import error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("controller")
public class DeviceExceptionAdvice {

  @ExceptionHandler(DeviceNotFoundException.class)
  public ResponseEntity<ErrorResponse> deviceNotFound() {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(new ErrorResponse("디바이스를 찾을 수 없습니다."));
  }

  @ExceptionHandler(DuplicateAddressException.class)
  public ResponseEntity<ErrorResponse> duplicateDevice() {
    return ResponseEntity
        .status(HttpStatus.CONFLICT)
        .body(new ErrorResponse("이미 디바이스 정보가 존재합니다."));
  }


}
