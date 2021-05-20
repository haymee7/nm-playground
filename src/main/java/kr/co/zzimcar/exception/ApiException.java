package kr.co.zzimcar.exception;

import kr.co.zzimcar.enumeration.ResponseCode;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

    private final String code;

    public ApiException(ResponseCode responseCode) {  //ApiException으로 들어오면 ApiExceptionHandler에서 낚아챔
        super(responseCode.getMessage());
        this.code = responseCode.getCode();
    }
}
//  public ApiException(String code, String msg) {
//    super(msg);
//    this.code = code;
//  }
