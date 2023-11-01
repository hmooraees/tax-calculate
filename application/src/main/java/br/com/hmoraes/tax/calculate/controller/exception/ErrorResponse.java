package br.com.hmoraes.tax.calculate.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponse {

    private String code;

    private String message;
}
