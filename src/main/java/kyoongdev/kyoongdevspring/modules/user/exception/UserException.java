package kyoongdev.kyoongdevspring.modules.user.exception;

import kyoongdev.kyoongdevspring.common.exception.BaseException;


public class UserException extends BaseException {

  private final UserErrorCode errorCode;

  public UserException(UserErrorCode e) {
    this.errorCode = e;
  }

  @Override
  public UserErrorCode getExceptionCodeInterface() {
    return errorCode;
  }
}
