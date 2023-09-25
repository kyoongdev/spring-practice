package kyoongdev.kyoongdevspring.modules.post.dto;

import kyoongdev.kyoongdevspring.common.TestDTO;
import kyoongdev.kyoongdevspring.modules.post.entity.Post;
import kyoongdev.kyoongdevspring.modules.user.dto.UserDTO;
import lombok.Data;

@Data
public class PostDTO extends TestDTO {

  private String id;
  private String title;
  private String content;

  private UserDTO user;

  public PostDTO(Post post) {
    this.id = post.getId();
    this.title = post.getTitle();
    this.content = post.getContent();
    this.user = new UserDTO(post.getUser());
  }
}
