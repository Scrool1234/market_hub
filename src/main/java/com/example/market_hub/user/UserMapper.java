package com.example.market_hub.user;

import com.example.market_hub.user.dto.UserDTO;
import com.example.market_hub.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toUserDTO(User user);
}
