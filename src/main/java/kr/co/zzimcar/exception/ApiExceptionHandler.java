package kr.co.zzimcar.exception;

import jdk.nashorn.internal.runtime.regexp.joni.exception.SyntaxException;
import kr.co.zzimcar.dto.ResponseDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.mapping.PropertyReferenceException;
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

import java.sql.SQLSyntaxErrorException;
import java.util.List;

import static kr.co.zzimcar.enumeration.ResponseCode.PROPERTY_REFERENCE_EXCEPTION;
import static kr.co.zzimcar.enumeration.ResponseCode.SQL_SYNTAX_EXCEPTION;


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

  @ExceptionHandler(SQLSyntaxErrorException.class)
  protected ResponseEntity<Object> handleSQLSyntaxErrorException(SQLSyntaxErrorException ex){
    return buildResponseEntity(new ResponseDto(SQL_SYNTAX_EXCEPTION));
  }

  @ExceptionHandler(PropertyReferenceException.class)
  protected ResponseEntity<Object> handlePropertyReferenceException(PropertyReferenceException ex){
    return buildResponseEntity(new ResponseDto<>(PROPERTY_REFERENCE_EXCEPTION));
  }
}