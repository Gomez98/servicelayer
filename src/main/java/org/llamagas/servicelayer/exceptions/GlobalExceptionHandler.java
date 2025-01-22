package org.llamagas.servicelayer.exceptions;

import org.llamagas.servicelayer.constants.ResponsesCodes;
import org.llamagas.servicelayer.model.response.GeneralResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja errores de validación cuando los datos no cumplen los requisitos.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GeneralResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        GeneralResponse response = new GeneralResponse();
        response.setCode(ResponsesCodes.PARAMETER_FAILED.getCode());
        response.setMessage(ResponsesCodes.PARAMETER_FAILED.getDescription());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
