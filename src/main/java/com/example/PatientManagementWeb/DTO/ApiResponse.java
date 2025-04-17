package com.example.PatientManagementWeb.DTO;

import com.example.PatientManagementWeb.Enum.ResponseCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    @JsonIgnore
    private HttpStatus status; // Ce champ ne sera pas inclus dans la réponse JSON
    private int responseCode;
    private String responseDescription;
    private Long responseTimestamp;

    private static MessageSource messageSource;



    public static <T> ApiResponse<T> of(ResponseCode responseCode, T data) {
        return ApiResponse.<T>builder()
                .status(responseCode.getHttpStatus())
                .responseCode(responseCode.getStatusCode())
                .responseDescription(responseCode.getDefaultMessage())
                .responseTimestamp(System.currentTimeMillis())
                .build();
    }

    public static <T> ApiResponse<T> of(ResponseCode responseCode, T data, String customMessage) {
        return ApiResponse.<T>builder()
                .status(responseCode.getHttpStatus())
                .responseCode(responseCode.getStatusCode())
                .responseDescription(customMessage)
                .responseTimestamp(System.currentTimeMillis())
                .build();
    }

    // Méthodes de compatibilité avec l'ancien code
    public static <T> ApiResponse<T> success(T data) {
        return of(ResponseCode.COMPLETED, data);
    }

    public static <T> ApiResponse<T> success(T data, String message, HttpStatus status) {
        ResponseCode code = ResponseCode.COMPLETED;
        return ApiResponse.<T>builder()
                .status(status)
                .responseCode(status.value())
                .responseDescription(message)
                .responseTimestamp(System.currentTimeMillis())
                .build();
    }

    public static <T> ApiResponse<T> error(HttpStatus status, String message) {
        ResponseCode code = status.is5xxServerError() ? ResponseCode.SERVER_ERROR : ResponseCode.BAD_REQUEST;
        return ApiResponse.<T>builder()
                .status(status)
                .responseCode(status.value())
                .responseDescription(message)
                .responseTimestamp(System.currentTimeMillis())
                .build();
    }

    // Méthode utilitaire pour convertir timestamp unix en LocalDateTime si nécessaire
    public LocalDateTime getLocalDateTime() {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(responseTimestamp), ZoneId.systemDefault());
    }



    public int getStatus() {
        return this.status.value();
    }
}