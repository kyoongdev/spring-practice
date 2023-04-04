package kyoongdev.kyoongdevspring.modules.post.repository;

import kyoongdev.kyoongdevspring.modules.post.entity.Post;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface PostRepository extends JpaRepository<Post, String> {
    Optional<Post> findById(String id);

    Optional<Post> findByTitle(String title);

    List<Post> findAllByUserId(Pageable pageable, String userId);

    Long countBy();


}