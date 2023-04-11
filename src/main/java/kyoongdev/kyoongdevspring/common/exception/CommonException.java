package kyoongdev.kyoongdevspring.common.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class CommonException extends BaseException {

  private final CommonErrorCode errorCodeInterface;


  @Override
  public CommonErrorCode getExceptionCodeInterface() {
    return errorCodeInterface;
  }


}
