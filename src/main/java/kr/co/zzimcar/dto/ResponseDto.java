package kr.co.zzimcar.dto;

import kr.co.zzimcar.exception.ApiException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseDto<T> {
  private boolean success;
  private String code;
  private String message;
  private T data;


  public ResponseDto(boolean success) {
    this.success = success;
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


  public ResponseDto(T data) {
    this.success = true;
    this.data = data;
  }
}
