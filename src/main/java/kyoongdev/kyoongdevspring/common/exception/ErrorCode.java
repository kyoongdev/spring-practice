package kyoongdev.kyoongdevspring.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

  NOT_FOUND(HttpStatus.NOT_FOUND),
  BAD_REQUEST(HttpStatus.BAD_REQUEST),
  UNAUTHORIZED(HttpStatus.UNAUTHORIZED),
  FORBIDDEN(HttpStatus.FORBIDDEN),
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR),
  CONFLICT(HttpStatus.CONFLICT);


  private final HttpStatus httpStatus;  // HttpStatus
}

