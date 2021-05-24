package kr.co.zzimcar.exception;

import io.swagger.models.auth.In;
import kr.co.zzimcar.dto.ResponseDto;
import kr.co.zzimcar.serviceImpl.BookServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import java.util.List;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
  private final Logger log = LogManager.getLogger(ApiExceptionHandler.class);

  private ResponseEntity<Object> buildResponseEntity(ResponseDto<Void> responseDto) {
    return ResponseEntity.ok(responseDto);
  }

  @Override

  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
                                                                WebRequest request) {
    ResponseDto<Void> responseDto = new ResponseDto<Void>(false);

    String message = new String();
    List<FieldError> FE = ex.getBindingResult().getFieldErrors();
    List<ObjectError> OE = ex.getBindingResult().getGlobalErrors();

    if (!FE.isEmpty()) {
      for (FieldError i : FE) {
        message += "[" + i.getField() + "]" + i.getDefaultMessage() + ". ";
      }
    } else if (!OE.isEmpty()) {
      for (ObjectError i : OE) {
        message += "[" + i.getObjectName() + "]" + i.getDefaultMessage() + ". ";
      }
    } else {
      message = "데이터 형식 오류 오류";
    }

    responseDto.setMessage(message);
    System.out.println("responseDto");
    System.out.println(responseDto.getMessage());

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