package com.asa.user.demo.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorField {
    private String fieldName;
    private String errorMessage;
    private String errorCode;
}
