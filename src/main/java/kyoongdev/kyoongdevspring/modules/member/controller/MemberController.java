package kyoongdev.kyoongdevspring.modules.member.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import kyoongdev.kyoongdevspring.common.ResponseWithIdDTO;
import kyoongdev.kyoongdevspring.modules.member.dto.UpsertUserDTO;
import kyoongdev.kyoongdevspring.modules.member.dto.UserDTO;
import kyoongdev.kyoongdevspring.modules.member.model.Member;
import kyoongdev.kyoongdevspring.modules.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "멤버", description = "멤버 조회")
@RestController
@RequestMapping("/members")
public class MemberController {


  public final MemberService memberService;


  MemberController(MemberService memberService) {
    this.memberService = memberService;
  }

  @Operation(summary = "members 조회", description = "members 조회 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserDTO.class)))),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @GetMapping()
  List<UserDTO> findUsers() {
    return memberService.getAll();
  }

  @Operation(summary = "member 조회", description = "member 조회 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class))),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @GetMapping("{id}")
  UserDTO findUser(@PathVariable("id") Long id) {

    return memberService.getOne(id);
  }

  @Operation(summary = "member 생성", description = "member 생성 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "생성 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseWithIdDTO.class))),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  ResponseWithIdDTO createUser(@RequestBody UpsertUserDTO member) {
    Long id = memberService.create(member);
    return new ResponseWithIdDTO(id);
  }

  @PatchMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  void updateUser(@PathVariable("id") Long id, @RequestBody UpsertUserDTO user) {
    memberService.update(id, user);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  void deleteUser(@PathVariable("id") Long id) {
    memberService.delete(id);
  }

}
