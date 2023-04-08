package kyoongdev.kyoongdevspring.common.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

  @ExceptionHandler(CustomException.class)
  protected ResponseEntity<ErrorResponseEntity> handleCustomException(CustomException e) {
    log.error("handleCustomException", e);
    return ErrorResponseEntity.toResponseEntity(e.getErrorCode(), e.getMessage());
  }

  /*
   * HTTP 405 Exception
   */
//  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//  protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(
//      final HttpRequestMethodNotSupportedException e) {
//    log.error("handleHttpRequestMethodNotSupportedException: {}", e.getMessage());
//    return ResponseEntity
//        .status(ErrorCode..getStatus().value())
//        .body(new ErrorResponse(ErrorCode.METHOD_NOT_ALLOWED));
//  }

  /*
   * HTTP 500 Exception
   */
  @ExceptionHandler(Exception.class)
  protected ResponseEntity<CustomException> handleException(final Exception e) {
    log.error("handleException: {}", e.getMessage());
    return ResponseEntity
        .status(ErrorCode.INTERNAL_SERVER_ERROR.getHttpStatus())
        .body(new CustomException(ErrorCode.INTERNAL_SERVER_ERROR, "서버에 오류가 발생했습니다."));
  }
}
