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


    public ResponseDto(ApiException ex) {  //여기서 에러를 받음
        this.success = false;                // 에러라는건 성공 실패
        this.code = ex.getCode();            // 코드
        this.message = ex.getMessage();      // 메세지 넘겨줌
    }

}
