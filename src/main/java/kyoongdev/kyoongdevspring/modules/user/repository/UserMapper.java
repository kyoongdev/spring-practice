package kyoongdev.kyoongdevspring.modules.user.repository;


import kyoongdev.kyoongdevspring.modules.user.dto.UpdateUserDTO;
import kyoongdev.kyoongdevspring.modules.user.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromRequest(UpdateUserDTO updateUserDTO, @MappingTarget User user);
}
