package kyoongdev.kyoongdevspring.common.exception;

import lombok.Getter;

@Getter
public abstract class BaseException extends RuntimeException {


  public abstract ErrorCodeInterface getExceptionCodeInterface();
}
