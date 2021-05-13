package kr.co.zzimcar.dto;

import kr.co.zzimcar.enumeration.ResponseCode;
import kr.co.zzimcar.exception.ApiException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ResponseDto<T> {
  private boolean success;
  private String code;
  private String message;
  private T data;


  public ResponseDto(boolean success) {
    this.success = success;
  }

  public ResponseDto(ResponseCode code) {
    this.success = false;
    this.code = code.getCode();
    this.message = code.getMessage();
  }

  public ResponseDto(String code, String message) {
    this.success = false;
    this.code = code;
    this.message = message;
  }

  public ResponseDto(String message) {
    this.success = false;
    this.message = message;
  }

  public ResponseDto(ApiException ex) {
    this.success = false;
    this.code = ex.getCode();
    this.message = ex.getMessage();
  }

}
