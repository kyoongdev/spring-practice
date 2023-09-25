package kyoongdev.kyoongdevspring.common.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kyoongdev.kyoongdevspring.common.exception.CommonErrorCode;
import kyoongdev.kyoongdevspring.common.exception.CommonException;
import kyoongdev.kyoongdevspring.utils.aop.UserRole;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import kyoongdev.kyoongdevspring.modules.user.entity.User;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

@Component
public class RoleInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) {

    boolean isUserRole = checkAnnotation(handler, UserRole.class);

    if (isUserRole) {
      User user = (User) request.getAttribute("user");

      //TODO: user가 서비스 사용자인지 혹은 관리자인지
      if (user == null) {
        throw new CommonException(CommonErrorCode.FORBIDDEN);
      }
      return true;
    }

    return true;
  }

  private boolean checkAnnotation(Object handler, Class<UserRole> roleClass) {
    if (handler instanceof ResourceHttpRequestHandler) {
      return true;
    }

    HandlerMethod handlerMethod = (HandlerMethod) handler;

    //Role anntotation이 있는 경우
    if (null != handlerMethod.getMethodAnnotation(roleClass) || null != handlerMethod.getBeanType()
        .getAnnotation(roleClass)) {
      return true;
    }

    //annotation이 없는 경우
    return false;
  }


}
