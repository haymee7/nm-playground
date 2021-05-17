package kr.co.zzimcar.dto;

import io.swagger.annotations.*;
import kr.co.zzimcar.enumeration.ResponseCode;
import kr.co.zzimcar.exception.ApiException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@ApiModel(description = "API 통신 결과")
public class ResponseDto<T> {
  @ApiModelProperty(value = "API 통신 성공유무")
  private boolean success;
  @ApiModelProperty(value = "API 통신 CODE")
  private String code;
  @ApiModelProperty(value = "API 통신 메시지")
  private String message;
  @ApiModelProperty(value = "API 데이터")
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
