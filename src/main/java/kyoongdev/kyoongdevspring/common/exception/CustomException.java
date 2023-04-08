package kyoongdev.kyoongdevspring.common.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {

  final ErrorCode errorCode;
  final String message;

}
