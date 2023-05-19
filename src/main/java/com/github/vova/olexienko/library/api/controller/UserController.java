package com.github.vova.olexienko.library.api.controller;

import com.github.vova.olexienko.library.api.constant.RoleName;
import com.github.vova.olexienko.library.api.dto.user.LoginDto;
import com.github.vova.olexienko.library.api.dto.user.RegisterDto;
import com.github.vova.olexienko.library.api.service.user.UserService;
import com.github.vova.olexienko.library.api.util.AuthorizationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;
    private final AuthorizationUtil authorizationUtil;

    @GetMapping("/role")
    public List<RoleName> getUserRoles() {
        return userService.getUserRoles();
    }

    @PostMapping("register")
    public ResponseEntity<Object> register(@RequestBody RegisterDto registerDto) {
        return buildResponse(authorizationUtil.createAuthorizationCookie(userService.register(registerDto)));
    }

    @PostMapping("login")
    public ResponseEntity<Object> login(@RequestBody LoginDto loginDto) {
        return buildResponse(authorizationUtil.createAuthorizationCookie(userService.login(loginDto)));
    }

    @PostMapping("logout")
    public ResponseEntity<Object> logout() {
        return buildResponse(authorizationUtil.deleteAuthorizationCookie());
    }

    private ResponseEntity<Object> buildResponse(HttpCookie authorizationCookie) {
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, authorizationCookie.toString())
                .build();
    }
}
