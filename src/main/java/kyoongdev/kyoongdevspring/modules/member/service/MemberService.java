package kyoongdev.kyoongdevspring.modules.member.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import kyoongdev.kyoongdevspring.modules.member.dao.MemberRepository;
import kyoongdev.kyoongdevspring.modules.model.Member;

public class MemberService implements MemberRepository {

  private static final Map<Long, Member> store = new HashMap<>();
  private Long sequence = 0L;

  @Override
  public Long save(Member member) {
    member.setId(++sequence);
    store.put(member.getId(), member);
    return member.getId();
  }

  @Override
  public List<Member> getAll() {
    return new ArrayList<>(store.values());
  }

  @Override
  public Optional<Member> getOne(Long id) {

    return Optional.ofNullable(store.get(id));
  }

  @Override
  public Optional<Member> getOneByName(String name) {
    return store.values().stream()
        .filter(member -> member.getName().equals(name))
        .findAny()
        ;
  }

}
