package com.example.PatientManagementWeb.exceptionHandler;

import com.example.PatientManagementWeb.DTO.ApiResponse;
import com.example.PatientManagementWeb.Enum.ResponseCode;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class I18nMessageUtil {

    private final MessageSource messageSource;

    public I18nMessageUtil(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String key, Object[] args, String defaultMessage) {
        return messageSource.getMessage(key, args, defaultMessage, LocaleContextHolder.getLocale());
    }

    public <T> ApiResponse<T> createResponse(ResponseCode code, T data) {
        String message = getMessage("response." + code.name().toLowerCase(), null, code.getDefaultMessage());
        return ApiResponse.<T>builder()
                .status(code.getHttpStatus())
                .responseCode(code.getStatusCode())
                .responseDescription(message)
                .responseTimestamp(System.currentTimeMillis())
                .build();
    }
}