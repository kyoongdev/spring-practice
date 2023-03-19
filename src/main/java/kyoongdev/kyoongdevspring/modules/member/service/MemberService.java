package kyoongdev.kyoongdevspring.modules.member.service;

import static java.util.stream.Collectors.toList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import kyoongdev.kyoongdevspring.modules.member.dao.MemberRepository;
import kyoongdev.kyoongdevspring.modules.member.dto.UpsertUserDTO;
import kyoongdev.kyoongdevspring.modules.member.dto.UserDTO;
import kyoongdev.kyoongdevspring.modules.member.model.Member;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;


@Service
public class MemberService implements MemberRepository {

  private static final Map<Long, Member> store = new HashMap<>();
  private Long sequence = 0L;

  @Override
  public Long create(UpsertUserDTO user) {
    Member member = new Member();

    member.setEmail(user.getEmail());
    member.setName(user.getName());
    member.setId(++sequence);

    store.put(member.getId(), member);
    return member.getId();
  }

  @Override
  public List<UserDTO> getAll() {

    return store.values().stream().map(member ->
        new UserDTO(member)
    ).collect(toList());

  }

  @Override
  public UserDTO getOne(Long id) {
    Optional<Member> user = Optional.ofNullable(store.get(id));
    if (!user.isPresent()) {
      throw new NotFoundException("유저를 찾을 수 없습니다.");
    }

    return new UserDTO(user.get());
  }

  @Override
  public void update(Long id, UpsertUserDTO user) {
    UserDTO isExist = this.getOne(id);

    isExist.setEmail(user.getEmail());
    isExist.setName(user.getName());

    Member member = isExist.convertMember();
    store.put(id, member);
  }

  @Override
  public void delete(Long id) {
    this.getOne(id);
    store.remove(id);
  }


}
