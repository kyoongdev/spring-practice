package kyoongdev.kyoongdevspring.modules.post.service;


import kyoongdev.kyoongdevspring.common.PagingDTO;
import kyoongdev.kyoongdevspring.common.ResponseWithIdDTO;
import kyoongdev.kyoongdevspring.common.exception.CustomException;
import kyoongdev.kyoongdevspring.common.exception.ErrorCode;
import kyoongdev.kyoongdevspring.modules.post.dto.CreatePostDTO;
import kyoongdev.kyoongdevspring.modules.post.dto.PostDTO;
import kyoongdev.kyoongdevspring.modules.post.dto.UpdatePostDTO;
import kyoongdev.kyoongdevspring.modules.post.entity.Post;
import kyoongdev.kyoongdevspring.modules.post.repository.PostMapper;
import kyoongdev.kyoongdevspring.modules.post.repository.PostRepository;
import kyoongdev.kyoongdevspring.modules.user.entity.User;
import kyoongdev.kyoongdevspring.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class PostService {

  PostRepository postRepository;
  PostMapper postMapper;
  UserService userService;

  @Autowired
  PostService(PostRepository postRepository, UserService userService, PostMapper postMapper) {
    this.postRepository = postRepository;
    this.postMapper = postMapper;
    this.userService = userService;
  }

  public Post findPostById(String id) {
    return postRepository.findById(id)
        .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND, "게시글을 찾을 수 없습니다."));
  }

  public PagingDTO<PostDTO> getPosts(Pageable pageable) {
    Long count = postRepository.countBy();
    List<PostDTO> posts = postRepository.findAll(pageable).stream().map(PostDTO::new)
        .collect(toList());

    return PagingDTO.<PostDTO>builder().data(posts).paging(PagingDTO.getPagination(pageable, count))
        .build();
  }

  public PagingDTO<PostDTO> getPostsByUserId(Pageable pageable, String userId) {
    Long count = postRepository.countBy();
    List<PostDTO> posts = postRepository.findAllByUserId(pageable, userId).stream()
        .map(PostDTO::new).collect(toList());

    return PagingDTO.<PostDTO>builder().data(posts).paging(PagingDTO.getPagination(pageable, count))
        .build();
  }

  public PostDTO getPostWithDTO(String id) {
    Optional<Post> post = postRepository.findById(id);
    if (post.isEmpty()) {
      throw new CustomException(ErrorCode.NOT_FOUND, "게시글을 찾을 수 없습니다.");
    }

    return post.map(PostDTO::new).get();
  }

  public ResponseWithIdDTO createPost(CreatePostDTO postDTO) {
    User user = userService.findUserById(postDTO.getUserId());

    Post post = postRepository.save(Post.builder()
        .title(postDTO.getTitle())
        .content(postDTO.getContent())
        .user(user)
        .build());

    return ResponseWithIdDTO.builder().id(post.getId()).build();
  }

  public void updatePost(String id, UpdatePostDTO postDTO) {
    Post post = findPostById(id);
    postMapper.updatePostFromRequest(postDTO, post);

    postRepository.save(post);
  }

  public void deletePost(String id) {
    postRepository.deleteById(id);
  }
}