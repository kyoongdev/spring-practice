package kyoongdev.kyoongdevspring.modules.post.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UpdatePostDTO {

    @Schema(nullable = true)

    private String title;
    @Schema(nullable = true)
    private String content;
}
