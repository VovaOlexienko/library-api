package com.github.vova.olexienko.library.api.service.user;

import com.github.vova.olexienko.library.api.constant.RoleName;
import com.github.vova.olexienko.library.api.dto.user.LoginDto;
import com.github.vova.olexienko.library.api.dto.user.RegisterDto;
import com.github.vova.olexienko.library.api.entity.Role;
import com.github.vova.olexienko.library.api.entity.User;
import com.github.vova.olexienko.library.api.mapper.UserMapper;
import com.github.vova.olexienko.library.api.repository.RoleRepository;
import com.github.vova.olexienko.library.api.repository.UserRepository;
import com.github.vova.olexienko.library.api.util.AuthorizationUtil;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorizationUtil authorizationUtil;
    private final UserMapper userMapper;

    @Override
    public String register(RegisterDto dto) {
        User user = userMapper.toEntity(dto);
        user.setHash(passwordEncoder.encode(dto.getPassword()));
        Role role = roleRepository.findByRoleName(RoleName.ROLE_USER)
                .orElseThrow(() -> new ValidationException("Role with name = [USER] is absent"));
        user.setRoles(List.of(role));
        return authorizationUtil.generateToken(userRepository.save(user).getEmail());
    }

    @Override
    public String login(LoginDto dto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authorizationUtil.generateToken(dto.getEmail());
    }

    @Override
    public List<RoleName> getUserRoles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Objects.nonNull(authentication) ? convertToUserRoles(authentication) : Collections.emptyList();
    }

    private List<RoleName> convertToUserRoles(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(authority -> RoleName.valueOf(authority.getAuthority()))
                .toList();
    }
}