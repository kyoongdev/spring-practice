package kyoongdev.kyoongdevspring.modules.member.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller()
public class MemberController {

  @GetMapping("/members")
   String members() {
    return "members";
  }

}
