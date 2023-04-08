package kyoongdev.kyoongdevspring.common.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.ResponseEntity;


@Data
@Builder
public class ErrorResponseEntity {

  private int status;

  private String message;

  public static ResponseEntity<ErrorResponseEntity> toResponseEntity(ErrorCode e, String message) {
    return ResponseEntity
        .status(e.getHttpStatus())
        .body(ErrorResponseEntity.builder()
            .status(e.getHttpStatus().value())
            .message(message)
            .build());
  }

}
