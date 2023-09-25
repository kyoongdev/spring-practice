package kyoongdev.kyoongdevspring.utils.aop;

import jakarta.servlet.http.HttpServletRequest;
import kyoongdev.kyoongdevspring.common.exception.CommonErrorCode;
import kyoongdev.kyoongdevspring.common.exception.CommonException;
import kyoongdev.kyoongdevspring.modules.user.entity.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class RoleAspect {

  @Before("@annotation(kyoongdev.kyoongdevspring.utils.aop.UserRole)")
  public void userRole(JoinPoint joinPoint) {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    User user = (User) request.getAttribute("user");

    if (user == null) {
      throw new CommonException(CommonErrorCode.FORBIDDEN);
    }
  }
}


