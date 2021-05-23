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
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


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


        StringBuilder builder = new StringBuilder();

        if (!ex.getBindingResult().getFieldErrors().isEmpty()) {
            for (int i = 0; i < ex.getBindingResult().getFieldErrors().size(); i++) {
                String message = "[" + ex.getBindingResult().getFieldErrors().get(i).getField() + "]" + ex.getBindingResult().getFieldErrors().get(i).getDefaultMessage();
                builder.append(message + ". ");
            }
        } else if (!ex.getBindingResult().getGlobalErrors().isEmpty()) {
            String message = "[" + ex.getBindingResult().getGlobalErrors().get(0).getObjectName() + "]" + ex.getBindingResult().getGlobalErrors().get(0).getDefaultMessage();
            builder.append(message + ". ");
        } else {
            String message = "데이터 형식 오류";
            builder.append(message);
        }


        responseDto.setMessage(builder.toString());
        System.out.println("responseDto");
        System.out.println(responseDto.getMessage());

        return buildResponseEntity(responseDto);

    }
//            for (int i = 0; i < Integer.MAX_VALUE; i++) {
//                String message = !ex.getBindingResult().getFieldErrors().isEmpty() ?
//                  "[" + ex.getBindingResult().getFieldErrors().get(i).getField() + "]" + ex.getBindingResult().getFieldErrors().get(i).getDefaultMessage() :
//                  !ex.getBindingResult().getGlobalErrors().isEmpty() ?
//                    "[" + ex.getBindingResult().getGlobalErrors().get(0).getObjectName() + "]" + ex.getBindingResult().getGlobalErrors().get(0).getDefaultMessage()
//                    : "데이터 형식 오류";
//                builder.append(message + ", ");


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Malformed JSON request";
        return buildResponseEntity(new ResponseDto<>("요청 JSON 포맷 오류"));
    }  //json형태가 아닐떄 나옴 ..  "post": "string", "title": "string", ,로 끝나면 json형태가 아니니까 오류 발생

    @ExceptionHandler(ApiException.class)  //ApiException으로 들어오느 에러 제어
    protected ResponseEntity<Object> handleIntApiException(ApiException ex) {
        return buildResponseEntity(new ResponseDto(ex));  // buildResponseEntity는
        // 새로운 ResponseDto를 만들고 ex를 담아서 보냄
    }
}