package com.github.vova.olexienko.library.api.mapper;

import com.github.vova.olexienko.library.api.dto.user.RegisterDto;
import com.github.vova.olexienko.library.api.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(RegisterDto dto);
}