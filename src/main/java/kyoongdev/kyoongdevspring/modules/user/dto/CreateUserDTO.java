package kyoongdev.kyoongdevspring.modules.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kyoongdev.kyoongdevspring.modules.user.entity.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class CreateUserDTO {
    @Schema(nullable = false)

    private String name;
    @Schema(nullable = false)
    private String email;
    @Schema(nullable = false)
    private String password;

    public CreateUserDTO hashPassword(PasswordEncoder passwordEncoder) {
        if (this.password != null) {
            this.password = passwordEncoder.encode(this.password);
        }
        return this;
    }


    public User toEntity() {
        return User.builder().email(this.email).name(this.name).password(this.password).build();
    }
}
