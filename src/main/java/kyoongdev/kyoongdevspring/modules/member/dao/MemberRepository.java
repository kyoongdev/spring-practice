package kyoongdev.kyoongdevspring.modules.member.dao;


import java.util.List;
import kyoongdev.kyoongdevspring.modules.model.Member;

public interface MemberRepository {

  Long save(Member member);

  List<Member> getAll();

  Member getOne(Long id);

  Member getOneByName(String name);


}
