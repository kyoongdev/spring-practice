package kyoongdev.kyoongdevspring.modules.member.dto;

import kyoongdev.kyoongdevspring.modules.member.model.Member;
import org.springframework.lang.NonNull;

public class UserDTO {


  Long id;

  String name;

  String email;

  public UserDTO(Member user) {
    this.id = user.getId();
    this.name = user.getName();
    this.email = user.getEmail();
  }

  public void setId(Long length) {
    this.id = ++length;
  }

  public Long getId() {
    return id;
  }

  public void setName(String name) {
    if (name != null) {
      this.name = name;
    }
  }

  public String getName() {
    return name;
  }

  public void setEmail(String email) {
    if (email != null) {
      this.email = email;
    }

  }

  public String getEmail() {
    return email;
  }

  public Member convertMember() {
    Member member = new Member();
    member.setId(this.id);
    member.setName(this.getName());
    member.setEmail(this.getEmail());

    return member;

  }
}
