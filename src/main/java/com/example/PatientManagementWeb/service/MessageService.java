package com.example.PatientManagementWeb.service;

import com.example.PatientManagementWeb.Enum.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageSource messageSource;

    public String getMessage(String key) {
        return messageSource.getMessage(key,null, LocaleContextHolder.getLocale());
    }

    public String getErrorMessage(ErrorCode errorCode) {
        String key="error."+errorCode.name().toLowerCase().replace("_","-") ;
        return messageSource.getMessage(key,null, LocaleContextHolder.getLocale());
    }
}
