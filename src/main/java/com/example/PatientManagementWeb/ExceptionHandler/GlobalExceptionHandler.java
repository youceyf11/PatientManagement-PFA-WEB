package com.example.PatientManagementWeb.ExceptionHandler;

import com.example.PatientManagementWeb.Exceptions.ProfessionalException;
import com.example.PatientManagementWeb.Exceptions.TechnicalException;
import com.example.PatientManagementWeb.Service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageService messageService;

    @ExceptionHandler(ProfessionalException.class)
    public ResponseEntity<String> handleProfessionalException(ProfessionalException ex) {
        String message = ex.getErrorCode() != null ?
                messageService.getErrorMessage(ex.getErrorCode()) :
                ex.getMessage();

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    //@ExceptionHandler(TechnicalException.class)
    public ResponseEntity<String> handleTechnicalException(TechnicalException ex) {
        String message = ex.getErrorCode() != null ?
                messageService.getErrorMessage(ex.getErrorCode()) :
                ex.getMessage();

        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>("Erreur de validation dans les données fournies",
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return new ResponseEntity<>("Le corps de la requête est manquant ou mal formaté",
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        return new ResponseEntity<>("Méthode HTTP non supportée",
                HttpStatus.METHOD_NOT_ALLOWED);
    }

    //@ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}