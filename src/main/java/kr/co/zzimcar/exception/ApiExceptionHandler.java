package kr.co.zzimcar.exception;

import kr.co.zzimcar.dto.ResponseDto;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.charset.StandardCharsets;
import java.util.List;

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
///////////  생성  /////////
    String message = "";
    List<FieldError> BF = ex.getBindingResult().getFieldErrors();
    List<ObjectError> BG = ex.getBindingResult().getGlobalErrors();

    if(!BF.isEmpty()) {
      for(FieldError i : BF) {
        message += i.getDefaultMessage()+". ";
      }
    } else if(!BG.isEmpty()) {
      message = BG.get(0).getDefaultMessage() + ". ";
    } else {
      message = "데이터 형식 오류";
    }
    responseDto.setMessage(message);
///////////  기존  /////////
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