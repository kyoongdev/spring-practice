package kyoongdev.kyoongdevspring.common.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@Builder
public class ExceptionDTO {

  private HttpStatus status;

  private String message;

  public static ResponseEntity<ExceptionDTO> toResponseEntity(ErrorCodeInterface e) {
    return ResponseEntity
        .status(e.getHttpStatus())
        .body(ExceptionDTO.builder()
            .status(e.getHttpStatus())
            .message(e.getMessage())
            .build());
  }

}
