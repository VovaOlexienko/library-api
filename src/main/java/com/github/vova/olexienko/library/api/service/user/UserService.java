package com.github.vova.olexienko.library.api.service.user;

import com.github.vova.olexienko.library.api.constant.RoleName;
import com.github.vova.olexienko.library.api.dto.user.LoginDto;
import com.github.vova.olexienko.library.api.dto.user.RegisterDto;

import java.util.List;

public interface UserService {

    String register(RegisterDto registerDto);

    String login(LoginDto loginDto);

    List<RoleName> getUserRoles();
}