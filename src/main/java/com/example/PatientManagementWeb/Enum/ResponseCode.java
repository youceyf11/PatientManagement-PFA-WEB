package com.example.PatientManagementWeb.Enum;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseCode {
    // Codes de succès
    COMPLETED("0000", 200, "Operation completed successfully"),
    CREATED("1000", 201, "Resource created successfully"),
    UPDATED("1001", 200, "Resource updated successfully"),
    DELETED("1002", 200, "Resource deleted successfully"),

    // Codes d'erreur
    BAD_REQUEST("4000", 400, "Invalid request"),
    NOT_FOUND("4001",404, "Resource not found"),
    UNAUTHORIZED("4010", 401, "Unauthorized"),
    FORBIDDEN("4030", 403, "Access forbidden"),
    CONFLICT("4090", 409, "Data conflict"),

    // Erreurs serveur
    SERVER_ERROR("5000",500, "Internal server error"),
    SERVICE_UNAVAILABLE("5030", 503, "Service unavailable");

    private final String code;
    private final int statusCode;
    private final String defaultMessage;

    ResponseCode(String code, int statusCode, String defaultMessage) {
        this.code = code;
        this.statusCode = statusCode;
        this.defaultMessage = defaultMessage;
    }


    public HttpStatus getHttpStatus() {
        return HttpStatus.valueOf(statusCode);
    }
}