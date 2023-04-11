package kyoongdev.kyoongdevspring.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CommonErrorCode implements ErrorCodeInterface {

  NOT_FOUND(HttpStatus.NOT_FOUND, "Not Found"),
  BAD_REQUEST(HttpStatus.BAD_REQUEST, "Bad Request"),
  UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "Unauthorized"),
  FORBIDDEN(HttpStatus.FORBIDDEN, "Forbidden"),
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error"),
  CONFLICT(HttpStatus.CONFLICT, "Conflict");


  private final HttpStatus httpStatus;  // HttpStatus
  private final String message;  // HttpStatus

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }


  public String getMessage() {
    return message;
  }


}
