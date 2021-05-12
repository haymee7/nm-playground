package com.naturemobility.api.exception;

import com.naturemobility.api.enumeration.ApiResponseCode;
import com.naturemobility.api.util.CommonUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @deprecated 성공/실패 리턴 포맷을 ApiResultDto로 맞추기 위해 IntApiCustomException 대신 IntApiException 을 사용.
 */
@Deprecated
@Getter
@Setter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IntApiCustomException extends RuntimeException {
  private String errCode;

  public IntApiCustomException(ApiResponseCode error) {
    super(error.msg());
    this.errCode = error.code();
//    sendError(this, error.code(), error.msg());
  }

  public IntApiCustomException(String code, String msg) {
    super(msg);
    this.errCode = code;
//    sendError(this, code, msg);
  }

  private void sendError(IntApiCustomException e, String code, String msg) {
    System.out.println(e.getStackTrace()[0].getFileName());

    if (e.getStackTrace()[0].getFileName().indexOf("CommonUtil") < 0
    && e.getStackTrace()[0].getFileName().indexOf("Biztalk") < 0 ) { // IntApiCustomException 무한루프 막기위한 조치임
      String where = new StringBuilder(e.getStackTrace()[0].getFileName())
        .append("\n함수명:")
        .append(e.getStackTrace()[0].getMethodName())
        .toString();

      String sendMsg = new StringBuilder("[ErrorCode]")
        .append(code)
        .append("\n")
        .append("[Msg]\n")
        .append(msg)
        .toString();

      CommonUtil.sendError(where, sendMsg);
    }
  }
}
