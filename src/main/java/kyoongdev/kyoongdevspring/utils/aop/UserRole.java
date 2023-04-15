package kyoongdev.kyoongdevspring.utils.aop;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD}) // 1
@Retention(RetentionPolicy.RUNTIME) // 2
public @interface UserRole {

}
