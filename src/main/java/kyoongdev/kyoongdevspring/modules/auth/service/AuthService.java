package kyoongdev.kyoongdevspring.modules.auth.service;

import kyoongdev.kyoongdevspring.common.JwtProvider;
import kyoongdev.kyoongdevspring.common.ResponseWithIdDTO;
import kyoongdev.kyoongdevspring.modules.auth.dto.LoginDTO;
import kyoongdev.kyoongdevspring.modules.auth.dto.TokenDTO;
import kyoongdev.kyoongdevspring.modules.user.dto.CreateUserDTO;
import kyoongdev.kyoongdevspring.modules.user.dto.UserDetailDTO;
import kyoongdev.kyoongdevspring.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    JwtProvider jwtProvider;
    UserService userService;
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthService(JwtProvider jwtProvider, UserService userService, PasswordEncoder passwordEncoder) {
        this.jwtProvider = jwtProvider;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    public TokenDTO login(LoginDTO props) {
        UserDetailDTO user = this.userService.findUserByEmail(props.getEmail());

        if (!user.checkPassword(props.getPassword(), this.passwordEncoder)) {
            throw new IllegalArgumentException("Password is not correct");
        }

        return jwtProvider.generateToken(user.getId());
    }

    public TokenDTO register(CreateUserDTO props) {
        ResponseWithIdDTO user = this.userService.createUser(props);


        return jwtProvider.generateToken(user.getId());
    }


}
