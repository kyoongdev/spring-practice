package kyoongdev.kyoongdevspring.modules.user.service;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;


import kyoongdev.kyoongdevspring.common.PagingDTO;
import kyoongdev.kyoongdevspring.common.ResponseWithIdDTO;
import kyoongdev.kyoongdevspring.modules.user.dto.CreateUserDTO;
import kyoongdev.kyoongdevspring.modules.user.dto.UpdateUserDTO;
import kyoongdev.kyoongdevspring.modules.user.dto.UserDTO;
import kyoongdev.kyoongdevspring.modules.user.dto.UserDetailDTO;
import kyoongdev.kyoongdevspring.modules.user.entity.User;
import kyoongdev.kyoongdevspring.modules.user.exception.UserErrorCode;
import kyoongdev.kyoongdevspring.modules.user.exception.UserException;
import kyoongdev.kyoongdevspring.modules.user.repository.UserMapper;
import kyoongdev.kyoongdevspring.modules.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

  UserRepository userRepository;
  PasswordEncoder passwordEncoder;
  UserMapper userMapper;


  @Autowired
  UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
      UserMapper userMapper) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.userMapper = userMapper;
  }

  public User findUserById(String id) {
    Optional<User> user = userRepository.findById(id);
    if (user.isEmpty()) {
      throw new UserException(UserErrorCode.USER_NOT_FOUND);
    }

    return user.get();
  }


  public UserDetailDTO findUserByEmail(String email) {
    Optional<User> user = userRepository.findByEmail(email);
    if (user.isEmpty()) {
      throw new UserException(UserErrorCode.USER_NOT_FOUND);
    }

    return UserDetailDTO.builder()
        .email(user.get().getEmail())
        .id(user.get().getId())
        .name(user.get().getName())
        .password(user.get().getPassword())
        .build();
  }


  public UserDTO getUserWithDTO(String id) {
    Optional<User> user = userRepository.findById(id);
    if (user.isEmpty()) {
      throw new UserException(UserErrorCode.USER_NOT_FOUND);
    }

    return user.map(UserDTO::new).get();
  }

  public Optional<User> getUserByName(String name) {
    return userRepository.findByName(name);
  }

  public PagingDTO<UserDTO> getUsers(Pageable pageable) {
    Long count = userRepository.countBy();
    List<UserDTO> users = userRepository.findAll(pageable).stream().map(UserDTO::new)
        .collect(toList());

    return PagingDTO.<UserDTO>builder().data(users).paging(PagingDTO.getPagination(pageable, count))
        .build();
  }

  public ResponseWithIdDTO createUser(CreateUserDTO props) {
    Optional<User> isExist = getUserByName(props.getName());

    if (isExist.isPresent()) {
      throw new UserException(UserErrorCode.USER_EXIST);
    }

    User user = userRepository.save(props.hashPassword(passwordEncoder).toEntity());

    return ResponseWithIdDTO.builder().id(user.getId()).build();
  }

  public void updateUser(String id, UpdateUserDTO props) {
    User user = this.findUserById(id);

    props.hashPassword(passwordEncoder);
    userMapper.updateUserFromRequest(props, user);

    userRepository.save(user);
  }

  public void deleteUser(String id) {
    this.findUserById(id);
    userRepository.deleteById(id);
  }
}
