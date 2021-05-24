package kr.co.zzimcar.exception;

import kr.co.zzimcar.dto.ResponseDto;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  private ResponseEntity<Object> buildResponseEntity(ResponseDto<Void> responseDto) {
    return ResponseEntity.ok(responseDto);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
                                                                WebRequest request) {
    ResponseDto<Void> responseDto = new ResponseDto<Void>(false);

//    StringBuilder builder = new StringBuilder();
//
//    if (!ex.getBindingResult().getFieldErrors().isEmpty()) {
//      for (int i = 0; i < ex.getBindingResult().getFieldErrors().size(); i++) {
//        String message = "[" + ex.getBindingResult().getFieldErrors().get(i).getField() + "]" + ex.getBindingResult().getFieldErrors().get(i).getDefaultMessage();
//        builder.append(message + ". ");
//      }
//    } else if (!ex.getBindingResult().getGlobalErrors().isEmpty()) {
//      String message = "[" + ex.getBindingResult().getGlobalErrors().get(0).getObjectName() + "]" + ex.getBindingResult().getGlobalErrors().get(0).getDefaultMessage();
//      builder.append(message + ". ");
//    } else {
//      String message = "데이터 형식 오류";
//      builder.append(message);
//    }
//
//    responseDto.setMessage(builder.toString());

    String message = "";
    if(!ex.getBindingResult().getFieldErrors().isEmpty()) {
      for(int i = 0; i < ex.getBindingResult().getFieldErrors().size(); i++) {
        message += ex.getBindingResult().getFieldErrors().get(i).getDefaultMessage() + ". ";
      }
    } else if(!ex.getBindingResult().getGlobalErrors().isEmpty()) {
      message += ex.getBindingResult().getGlobalErrors().get(0).getDefaultMessage() + ". ";
    } else {
      message += "데이터 형식 오류";
    }
    responseDto.setMessage(message);

//    String message = "";
//    String message1 = !ex.getBindingResult().getFieldErrors().isEmpty() ?
//      "[" + ex.getBindingResult().getFieldErrors().get(0).getField() + "]" + ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage() :
//      !ex.getBindingResult().getGlobalErrors().isEmpty() ?
//        "[" + ex.getBindingResult().getGlobalErrors().get(0).getObjectName() + "]" + ex.getBindingResult().getGlobalErrors().get(0).getDefaultMessage()
//        : "데이터 형식 오류";
//
//    responseDto.setMessage(message+message1);


    return buildResponseEntity(responseDto);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    String error = "Malformed JSON request";
    return buildResponseEntity(new ResponseDto<>("요청 JSON 포맷 오류"));
  }

  @ExceptionHandler(ApiException.class)
  protected ResponseEntity<Object> handleIntApiException(ApiException ex) {
    return buildResponseEntity(new ResponseDto(ex));
  }
}