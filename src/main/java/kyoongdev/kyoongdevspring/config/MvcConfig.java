package kyoongdev.kyoongdevspring.config;

import kyoongdev.kyoongdevspring.common.auth.JwtInterceptor;
import kyoongdev.kyoongdevspring.common.auth.RoleInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

  @Autowired
  private JwtInterceptor jwtInterceptor;
  @Autowired
  private RoleInterceptor roleInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(jwtInterceptor).excludePathPatterns("/auth/**");
    registry.addInterceptor(roleInterceptor).excludePathPatterns("/api-docs")
        .excludePathPatterns("/swagger-ui/**");
  }
}
