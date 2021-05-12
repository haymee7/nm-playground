package com.naturemobility.api.exception;

import com.naturemobility.api.enumeration.ApiResponseCode;
import lombok.Getter;

@Getter
public class IntApiException extends RuntimeException {
  private String code;

  public IntApiException(ApiResponseCode response) {
    super(response.msg());
    this.code = response.code();
  }

  public IntApiException(String code, String msg) {
    super(msg);
    this.code = code;
  }
}
