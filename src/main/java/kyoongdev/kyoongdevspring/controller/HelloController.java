package kyoongdev.kyoongdevspring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import springfox.documentation.annotations.ApiIgnore;

@Controller
public class HelloController {

  @GetMapping("/hello")
  public String hello(Model model) {
    return "HELLO";
  }
}
