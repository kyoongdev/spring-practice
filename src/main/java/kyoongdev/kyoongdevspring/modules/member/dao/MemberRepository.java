package kyoongdev.kyoongdevspring.modules.member.dao;


import java.util.List;
import java.util.Optional;
import kyoongdev.kyoongdevspring.modules.model.Member;

public interface MemberRepository {

  Long save(Member member);

  List<Member> getAll();

  Optional<Member> getOne(Long id);

  Optional<Member> getOneByName(String name);


}