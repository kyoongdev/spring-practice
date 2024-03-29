package kyoongdev.kyoongdevspring.modules.post.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kyoongdev.kyoongdevspring.common.PagingDTO;
import kyoongdev.kyoongdevspring.common.ResponseWithIdDTO;
import kyoongdev.kyoongdevspring.common.TestDTO;
import kyoongdev.kyoongdevspring.modules.post.dto.CreatePostDTO;
import kyoongdev.kyoongdevspring.modules.post.dto.PostDTO;
import kyoongdev.kyoongdevspring.modules.post.dto.UpdatePostDTO;
import kyoongdev.kyoongdevspring.modules.post.service.PostService;
import kyoongdev.kyoongdevspring.utils.aop.UserRole;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@Tag(name = "게시글", description = "게시글 조회")
@RestController
@RequestMapping("/posts")
public class PostController {

  public final PostService postService;


  public PostController(PostService postService) {
    this.postService = postService;
  }

  @Operation(summary = "게시글 조회", description = "게시글 조회 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PostDTO.class)))),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @GetMapping()
  @UserRole
  @Cacheable(value = "post")
  public PagingDTO<PostDTO> findPosts(Pageable pageable) {
    return postService.getPosts(pageable);
  }

  @Operation(summary = "유저의 게시글 조회", description = "유저의 게시글 조회 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PostDTO.class)))),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @GetMapping("{userId}")
  void findPostsByUserId(@PathVariable("userId") String userId,
      @Parameter TestDTO tes) {
//    return postService.getPostsByUserId(pageable, userId);
  }

  @Operation(summary = "게시글 조회", description = "게시글 조회 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PostDTO.class))),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @GetMapping("{id}/detail")
  @Cacheable(value = "post", key = "#id")
  public PostDTO findUser(@PathVariable("id") String id) {
    return postService.getPostWithDTO(id);
  }

  @Operation(summary = "게시글 생성", description = "게시글 생성 메서드입니다.")
  @CacheEvict(value = "post", allEntries = true)
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "생성 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseWithIdDTO.class))),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseWithIdDTO createPost(@RequestBody CreatePostDTO post) {
    return postService.createPost(post);
  }

  @PatchMapping("{id}")
  @CachePut(value="post", key="#id")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updatePost(@PathVariable("id") String id, @RequestBody UpdatePostDTO post) {
    postService.updatePost(id, post);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  void deletePost(@PathVariable("id") String id) {
    postService.deletePost(id);
  }
}
