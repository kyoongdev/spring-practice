package kyoongdev.kyoongdevspring.modules.user.dto;

import kyoongdev.kyoongdevspring.modules.user.entity.User;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Data
@SuperBuilder
public class UserDetailDTO {
    private String id;
    private String name;
    private String email;
    private String password;

    public boolean checkPassword(String plainPassword, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(plainPassword, this.password);
    }


}
