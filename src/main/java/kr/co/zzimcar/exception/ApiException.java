package kr.co.zzimcar.exception;

import kr.co.zzimcar.enumeration.ResponseCode;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

  private final String code;

  public ApiException(String code, String msg) {
    super(msg);
    this.code = code;
  }

  public ApiException(ResponseCode responseCode) {
    super(responseCode.getMessage());
    this.code = responseCode.getCode();
  }
}
