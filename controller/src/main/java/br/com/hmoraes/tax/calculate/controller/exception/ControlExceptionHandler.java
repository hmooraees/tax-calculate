package br.com.hmoraes.tax.calculate.controller.exception;

import br.com.hmoraes.tax.calculate.port.database.CategoryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControlExceptionHandler {

    @ExceptionHandler({ CategoryGateway.CategoryNotFoundException.class })
    public ResponseEntity<Object> handlerException(CategoryGateway.CategoryNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(this.getErrorBody(HttpStatus.NOT_FOUND.toString(), "Categoria nao encontrada"));
    }

    private ErrorResponse getErrorBody(String code, String message) {
        return ErrorResponse.builder()
                .code(code)
                .message(message)
                .build();
    }
}
