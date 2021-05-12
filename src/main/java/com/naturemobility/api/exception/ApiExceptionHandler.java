package com.naturemobility.api.exception;

import com.naturemobility.api.dto.ApiResultDto;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    private ResponseEntity<ApiResultDto> buildResponseEntity(ApiResultDto resultDto) {
        return ResponseEntity.ok(resultDto);
    }

    /**
     * 요청 객체의 @Valid 검사 실패 시 동작되는 예외처리
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);

        String message = !ex.getBindingResult().getFieldErrors().isEmpty() ?
          "[" + ex.getBindingResult().getFieldErrors().get(0).getField() + "]" + ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage() :
          !ex.getBindingResult().getGlobalErrors().isEmpty() ?
            "[" + ex.getBindingResult().getGlobalErrors().get(0).getObjectName() + "]" + ex.getBindingResult().getGlobalErrors().get(0).getDefaultMessage() : "데이터 형식 오류";

        apiError.setMessage(message);
        apiError.addValidationErrors(ex.getBindingResult().getFieldErrors());
        apiError.addValidationError(ex.getBindingResult().getGlobalErrors());
        return buildResponseEntity(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(String.format("%s URL 에서 %s 요청을 지원하지 않습니다.", ex.getRequestURL(), ex.getHttpMethod()));
        apiError.setDebugMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Malformed JSON request";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage("");
        apiError.setDebugMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    // ResponseEntityExceptionHandler 에 없는 예외처리 커스터마이징 --

    /**
     * @deprecated 성공/실패 리턴 포맷을 ApiResultDto로 맞추기 위해 IntApiCustomException 대신 IntApiException 을 사용.
     * @param ex
     * @return
     */
    @ExceptionHandler(IntApiCustomException.class)
    @Deprecated
    protected ResponseEntity<?> handleIntApiCustom(IntApiCustomException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), ex.getErrCode());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(IntApiException.class)
    protected ResponseEntity<ApiResultDto> handleIntApiException(IntApiException ex) {
        ApiResultDto resultDto = new ApiResultDto(false);
        resultDto.setCode(ex.getCode());
        resultDto.setMessage(ex.getMessage());
        return buildResponseEntity(resultDto);
    }

    @ExceptionHandler(AuthenticationEntryPointException.class)
    protected ResponseEntity<?> handleAuthenticationEntryPoint(AuthenticationEntryPointException ex) {
        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, ex.getMessage(), ex);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<?> handleConstraintViolation(ConstraintViolationException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        return buildResponseEntity(apiError);
    }

    // 알수없는 오류 핸들링
    @ExceptionHandler(UnknownException.class)
    protected ResponseEntity<?> handleUnknown(UnknownException ex) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<Object> handleAccessDenied(AccessDeniedException ex) {
        ApiError apiError = new ApiError(HttpStatus.FORBIDDEN, "보유한 권한으로 접근할 수 없는 리소스입니다.", ex);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    protected ResponseEntity<Object> handleDuplicateKey(DuplicateKeyException ex) {
        ApiError apiError = new ApiError(HttpStatus.FORBIDDEN, ex.getMessage(), ex);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException ex) {
        ApiError apiError = new ApiError(HttpStatus.FORBIDDEN, ex.getMessage(), ex);
        return buildResponseEntity(apiError);
    }
}