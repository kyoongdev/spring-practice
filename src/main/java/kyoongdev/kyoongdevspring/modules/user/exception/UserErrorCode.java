package kyoongdev.kyoongdevspring.modules.user.exception;

import kyoongdev.kyoongdevspring.common.exception.ErrorCodeInterface;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.HttpStatus;


@AllArgsConstructor
public enum UserErrorCode implements ErrorCodeInterface {


  USER_EXIST(HttpStatus.CONFLICT, "User Already Exists"),
  USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found");
  private final HttpStatus httpStatus;
  private final String message;


  @Override
  public String getMessage() {
    return message;
  }

  @Override
  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

}
