package com.example.demo.exception;

import com.example.demo.enumeration.ErrorCode;
import com.example.demo.model.ErrorDto;
import com.example.demo.model.FieldErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import static com.example.demo.enumeration.ErrorCode.*;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

/*    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
                                                                         @NonNull HttpHeaders headers,
                                                                         @NonNull HttpStatusCode status,
                                                                         @NonNull WebRequest request) {
        String[] supportedMethods = e.getSupportedMethods();
        StringBuilder sb = new StringBuilder();
        sb.append("해당 URI에서 사용가능한 메서드 목록 : ");
        for (String supportedMethod : Objects.requireNonNull(supportedMethods)) {
            sb.append(supportedMethod);
            sb.append(" ");
        }

        final FieldErrorDto fieldErrorDto = FieldErrorDto.builder()
                .field(sb.toString())
                .reason("현재 요청 HTTP메서드 : " + e.getMethod())
                .build();

        final ErrorDto response = ErrorDto.builder()
                .code(METHOD_NOT_ALLOWED.getCode())
                .message(sb.toString())
                .errors(List.of(fieldErrorDto))
                .build();

        log.error("[Exception] : {}", e.getMessage(), e);
        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            @NonNull HttpMediaTypeNotSupportedException e, @NonNull HttpHeaders headers, @NonNull HttpStatusCode status, @NonNull WebRequest request) {

        final ErrorDto response = ErrorDto.builder()
                .code(UNSUPPORTED_MEDIA_TYPE.getCode())
                .build();

        log.error("[Exception] : {}", e.getMessage(), e);
        return new ResponseEntity<>(response, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }


    // @Valid, @Validated 에서 binding error 발생 시 (@RequestBody)
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e, @NonNull HttpHeaders headers, @NonNull HttpStatusCode status, @NonNull WebRequest request) {

        final List<FieldErrorDto> filedError = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> {
                    return FieldErrorDto.builder()
                            .field(error.getField())
                            .value(error.getRejectedValue() == null ? "" : error.getRejectedValue().toString())
                            .reason(error.getDefaultMessage())
                            .build();
                })
                .toList();

        final ErrorDto response = ErrorDto.builder()
                .code(INVALID_INPUT_VALUE.getCode())
                .errors(filedError)
                .build();

        log.error("[Exception] : {}", e.getMessage(), e);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

    @Override
    public ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException e, @NonNull HttpHeaders headers, @NonNull HttpStatusCode status, @NonNull WebRequest request) {

        final FieldErrorDto fieldErrorDto = FieldErrorDto.builder()
                .field(e.getRequestURL())
                .reason("존재하지 않는 URL입니다.")
                .build();

        final ErrorDto response = ErrorDto.builder()
                .code(NOT_FOUND.getCode())
                .errors(List.of(fieldErrorDto))
                .build();

        log.error("[Exception] : {}", e.getMessage(), e);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException e, @NonNull HttpHeaders headers, @NonNull HttpStatusCode status, @NonNull WebRequest request) {

        final ErrorDto response;

        if (e.getCause() instanceof MismatchedInputException mismatchedInputException) {
            final FieldErrorDto fieldErrorDto = FieldErrorDto.builder()
                    .field(mismatchedInputException.getPath().get(0).getFieldName() + "필드의 값이 잘못되었습니다.")
                    .reason("입력 포맷을 확인해 보십시오")
                    .build();

            response = ErrorDto.builder()
                    .code(INVALID_INPUT_VALUE.getCode())
                    .errors(List.of(fieldErrorDto))
                    .build();
        } else {
            final FieldErrorDto fieldErrorDto = FieldErrorDto.builder()
                    .reason("입력 포맷을 확인해 보십시오")
                    .build();
            response = ErrorDto
                    .builder()
                    .code(INVALID_INPUT_VALUE.getCode())
                    .errors(List.of(fieldErrorDto))
                    .build();
        }

        log.error("[Exception] : {}", e.getMessage(), e);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException e, @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status, @NonNull WebRequest request) {

        final FieldErrorDto fieldErrorDto = FieldErrorDto.builder()
                .field("결여된 파라미터 명 : " + e.getParameterName())
                .build();

        final ErrorDto response = ErrorDto.builder()
                .code(MISSING_PARAMETER.getCode())
                .errors(List.of(fieldErrorDto))
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }*/

    //Spring security 오류

    @ExceptionHandler
    protected ResponseEntity<ErrorDto> handleBadCredentialException(BadCredentialsException e) {
        final ErrorDto response = ErrorDto.builder()
                .code(UNAUTHORIZED.getCode())
                .build();

        log.error("[Exception] : {}", e.getMessage(), e);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorDto> handleInternalAuthenticationServiceException(InternalAuthenticationServiceException e) {
        final ErrorDto response;
        if (e.getMessage().contains("AttributeConverter")) {
            response =  ErrorDto.builder()
                    .code(CONVERTED_FAIL.getCode())
                    .build();
        } else {
            response =  ErrorDto.builder()
                    .code(INTERNAL_AUTHENTICATION.getCode())
                    .build();
        }

        log.error("[Exception] : {}", e.getMessage(), e);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorDto> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        final FieldErrorDto fieldErrorDto = FieldErrorDto.builder()
                .field(e.getName())
                .value(e.getValue() == null ? "" : e.getValue().toString())
                .reason(e.getErrorCode())
                .build();

        final ErrorDto response = ErrorDto
                .builder()
                .code(INVALID_TYPE_VALUE.getCode())
                .errors(List.of(fieldErrorDto))
                .build();

        log.error("[Exception] : {}", e.getMessage(), e);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleIllegalArgumentExceptionJwtException(IllegalArgumentException e) {
        final ErrorDto response = ErrorDto.builder()
                .code(ILLEGAL_ARGUMENT.getCode())
                .message(ILLEGAL_ARGUMENT.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleIllegalArgumentExceptionJwtException(AuthenticationException e) {
        final ErrorDto response = ErrorDto.builder()
                .code(ACCESS_DENIED.getCode())
                .message(ACCESS_DENIED.getMessage())
                .build();

        log.error("[Exception] : {}", e.getMessage(), e);
        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleIllegalArgumentExceptionJwtException(AccessDeniedException e) {
        final ErrorDto response = ErrorDto.builder()
                .code(INVALID_AUTHENTICATION.getCode())
                .message(INVALID_AUTHENTICATION.getMessage())
                .build();

        log.error("[Exception] : {}", e.getMessage(), e);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler //무결성 제약조건 위반 오류
    public ResponseEntity<ErrorDto> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e) {
        final ErrorDto response = ErrorDto.builder()
                .code(DATA_INTEGRITY_CONSTRAINT_VIOLATION.getCode())
                .message(DATA_INTEGRITY_CONSTRAINT_VIOLATION.getMessage())
                .build();

        log.error("[Exception] : {}", e.getMessage(), e);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    // 그 밖에 발생하는 모든 예외처리가 이곳으로 모인다.
/*    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception e, @Nullable Object body, @NonNull HttpHeaders headers, @NonNull HttpStatusCode statusCode, @NonNull WebRequest request) {

        final FieldErrorDto fieldErrorDto = FieldErrorDto.builder()
                .field(e.getClass().getSimpleName())
                .reason("알수 없는 서버 에러발생")
                .build();

        final ErrorDto response = ErrorDto.builder()
                .code(INTERNAL_SERVER_ERROR.getCode())
                .errors(List.of(fieldErrorDto))
                .build();

        log.error("[Exception] : {}", e.getMessage(), e);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }*/

    // 비즈니스 요구사항에 따른 Exception
    @ExceptionHandler
    protected ResponseEntity<ErrorDto> handleBusinessException(BusinessException e) {
        final ErrorCode errorCode = e.getErrorCode();

        final ErrorDto response = ErrorDto
                .builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();

        log.error("[Exception] : {}", e.getMessage(), e);
        return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
    }

}
