package kyoongdev.kyoongdevspring.common.exception;

import org.springframework.http.HttpStatus;

public interface ErrorCodeInterface {

  HttpStatus getHttpStatus();

  String getMessage();
}
