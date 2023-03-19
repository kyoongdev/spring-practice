package kyoongdev.kyoongdevspring.modules.member.dao;


import java.util.List;
import kyoongdev.kyoongdevspring.modules.member.dto.UpsertUserDTO;
import kyoongdev.kyoongdevspring.modules.member.dto.UserDTO;
import kyoongdev.kyoongdevspring.modules.member.model.Member;

public interface MemberRepository {

  Long create(UpsertUserDTO member);

  List<UserDTO> getAll();

  UserDTO getOne(Long id);

  void update(Long id, UpsertUserDTO member);

  void delete(Long id);

}