package kyoongdev.kyoongdevspring.modules.auth.controller;


import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kyoongdev.kyoongdevspring.modules.auth.dto.LoginDTO;
import kyoongdev.kyoongdevspring.modules.auth.dto.TokenDTO;
import kyoongdev.kyoongdevspring.modules.auth.service.AuthService;
import kyoongdev.kyoongdevspring.modules.user.dto.CreateUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth")
@RestController
@RequestMapping("/auth")
public class AuthController {
    public final AuthService authService;

    @Autowired
    AuthController(AuthService authService) {
        this.authService = authService;
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TokenDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request")

    })
    @PostMapping("login")
    TokenDTO login(@RequestBody LoginDTO body) {
        return authService.login(body);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원가입 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TokenDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request")

    })
    @PostMapping("register")
    TokenDTO register(@RequestBody CreateUserDTO body) {
        return authService.register(body);
    }
}
