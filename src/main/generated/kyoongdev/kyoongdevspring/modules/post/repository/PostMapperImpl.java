package kyoongdev.kyoongdevspring.modules.post.repository;

import javax.annotation.processing.Generated;
import kyoongdev.kyoongdevspring.modules.post.dto.UpdatePostDTO;
import kyoongdev.kyoongdevspring.modules.post.entity.Post;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-12T18:29:25+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public void updatePostFromRequest(UpdatePostDTO updatePostDTO, Post post) {
        if ( updatePostDTO == null ) {
            return;
        }

        if ( updatePostDTO.getTitle() != null ) {
            post.setTitle( updatePostDTO.getTitle() );
        }
        if ( updatePostDTO.getContent() != null ) {
            post.setContent( updatePostDTO.getContent() );
        }
    }
}
