package kyoongdev.kyoongdevspring.common.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kyoongdev.kyoongdevspring.modules.user.entity.User;
import kyoongdev.kyoongdevspring.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtInterceptor implements HandlerInterceptor {

  private final JwtProvider jwtProvider;
  private final UserService userService;

  private final AuthorizationExtractor authorizationExtractor;

  @Autowired
  public JwtInterceptor(JwtProvider jwtProvider, UserService userService,
      AuthorizationExtractor authorizationExtractor) {
    this.jwtProvider = jwtProvider;
    this.userService = userService;
    this.authorizationExtractor = authorizationExtractor;
  }


  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    String token = authorizationExtractor.extract(request, "Bearer");
    if (!token.isEmpty()) {
      if (!jwtProvider.validateToken(token)) {
        throw new IllegalArgumentException("Token is invalid");
      }

      String userId = jwtProvider.getAuthentication(token);
      User user = userService.findUserById(userId);

      request.setAttribute("user", user);
    }

    return true;
  }


}
