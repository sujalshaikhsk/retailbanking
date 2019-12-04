package com.hcl.retailbanking.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiExceptionDto {

    private String errorCode;
    private String message;

    public ApiExceptionDto(String errorCode, String message) {
        super();
        this.errorCode = errorCode;
        this.message = message;
    }

    public ApiExceptionDto() {
        super();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
