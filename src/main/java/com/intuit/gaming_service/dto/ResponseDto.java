package com.intuit.gaming_service.dto;

public class ResponseDto {

  private Object data;

  private String message = "";

  private boolean isSuccess;

  public ResponseDto(Object data, String message, boolean isSuccess) {
    this.data = data;
    this.message = message;
    this.isSuccess = isSuccess;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public boolean isSuccess() {
    return isSuccess;
  }

  public void setSuccess(boolean success) {
    isSuccess = success;
  }
}
