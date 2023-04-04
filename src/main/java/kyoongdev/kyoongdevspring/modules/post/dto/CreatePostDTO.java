package kyoongdev.kyoongdevspring.modules.post.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kyoongdev.kyoongdevspring.modules.post.entity.Post;
import kyoongdev.kyoongdevspring.modules.user.entity.User;
import lombok.Data;

@Data
public class CreatePostDTO {
    @Schema(nullable = false)
    private String title;
    @Schema(nullable = false)
    private String content;


    @Schema(nullable = false)
    private String userId;


    public Post toEntity(User user) {
        return Post.builder().title(this.title).content(this.content).user(user).build();
    }

}
