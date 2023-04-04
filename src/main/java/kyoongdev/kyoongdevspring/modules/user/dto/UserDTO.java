package kyoongdev.kyoongdevspring.modules.user.dto;


import kyoongdev.kyoongdevspring.modules.user.entity.User;
import lombok.Data;

@Data
public class UserDTO {
    private String id;
    private String name;
    private String email;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
