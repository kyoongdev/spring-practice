package kyoongdev.kyoongdevspring.common.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.naming.AuthenticationException;
import kyoongdev.kyoongdevspring.common.exception.CustomException;
import kyoongdev.kyoongdevspring.common.exception.ErrorCode;
import kyoongdev.kyoongdevspring.modules.user.entity.User;
import kyoongdev.kyoongdevspring.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component

public class JwtInterceptor implements HandlerInterceptor {

  @Autowired
  private JwtProvider jwtProvider;
  @Autowired
  private UserService userService;

  @Autowired
  private AuthorizationExtractor authorizationExtractor;


  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    String token = authorizationExtractor.extract(request, "Bearer");
    if (token.isEmpty()) {
      throw new CustomException(ErrorCode.UNAUTHORIZED, "Token is empty");
    }

    if (!jwtProvider.validateToken(token)) {
      throw new CustomException(ErrorCode.UNAUTHORIZED, "Token is invalid");
    }

    String userId = jwtProvider.getAuthentication(token);
    User user = userService.findUserById(userId);

    request.setAttribute("user", user);

    return true;
  }

  private void verifyToken(String givenToken, String membersToken) {
    if (!givenToken.equals(membersToken)) {
      throw new IllegalArgumentException("사용자의 Token과 일치하지 않습니다.");
    }
//   jwtProvider.verifyToken(givenToken);
  }

}
