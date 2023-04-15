package kyoongdev.kyoongdevspring.common.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(BaseException.class)
  protected ResponseEntity<ExceptionDTO> handleCustomException(BaseException e) {

    return ExceptionDTO.toResponseEntity(e.getExceptionCodeInterface()
    );
  }

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<ExceptionDTO> handleException(final Exception e) {

    return ResponseEntity
        .status(CommonErrorCode.INTERNAL_SERVER_ERROR.getHttpStatus())
        .body(ExceptionDTO.builder().status(CommonErrorCode.INTERNAL_SERVER_ERROR.getHttpStatus())
            .message(e.getMessage()).build());

  }

  @ExceptionHandler(JpaSystemException.class)
  protected ResponseEntity<ExceptionDTO> handleJPAException(final JpaSystemException e) {

    return ResponseEntity
        .status(CommonErrorCode.INTERNAL_SERVER_ERROR.getHttpStatus())
        .body(ExceptionDTO.builder().status(CommonErrorCode.INTERNAL_SERVER_ERROR.getHttpStatus())
            .message(e.getMessage()).build());
  }
}
