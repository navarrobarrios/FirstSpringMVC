package com.example.anavarropc.responses_entities;

import com.example.anavarropc.beans.ErrorCodesAplication;
import com.example.anavarropc.utils.ErrorUtils;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse {
    //region Variables
    @JsonProperty("error_code")
    private int errorCode;
    @JsonProperty("message")
    private String message;
    //endregion

    //region Constructor's

    public ErrorResponse(ErrorCodesAplication errorCodesAplication) {
        this.errorCode = errorCodesAplication.getErrorCode();
        this.message = errorCodesAplication.getMessage();
    }
    //endregion

    //region Setters && Getters

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
        this.setMessage(ErrorUtils.getErrorMessage(errorCode));
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    //endregion
}
