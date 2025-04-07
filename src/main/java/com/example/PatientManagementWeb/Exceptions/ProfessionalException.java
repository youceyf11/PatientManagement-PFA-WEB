package com.example.PatientManagementWeb.Exceptions;

import com.example.PatientManagementWeb.Enum.ErrorCode;
import lombok.Getter;

@Getter
public class ProfessionalException extends RuntimeException {
    private final ErrorCode errorCode;

    public ProfessionalException(String message){
        super(message);
        this.errorCode=null;
    }

    public ProfessionalException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ProfessionalException(ErrorCode errorCode, String details) {
        super(errorCode.getMessage() + " : " + details);
        this.errorCode = errorCode;
    }
}