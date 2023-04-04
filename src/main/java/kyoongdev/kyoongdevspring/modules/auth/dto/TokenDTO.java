package kyoongdev.kyoongdevspring.modules.auth.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
public class TokenDTO {
    private String accessToken;
    private String refreshToken;
}
