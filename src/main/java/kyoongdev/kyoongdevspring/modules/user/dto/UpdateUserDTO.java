package kyoongdev.kyoongdevspring.modules.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kyoongdev.kyoongdevspring.modules.user.entity.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class UpdateUserDTO {

    @Schema(nullable = true)
    private String name;
    @Schema(nullable = true)
    private String email;
    @Schema(nullable = true)
    private String password;

    public UpdateUserDTO hashPassword(PasswordEncoder passwordEncoder) {
        if (this.password != null) {
            this.password = passwordEncoder.encode(this.password);
        }
        return this;
    }

    public boolean checkPassword(String plainPassword, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(plainPassword, this.password);
    }


}
