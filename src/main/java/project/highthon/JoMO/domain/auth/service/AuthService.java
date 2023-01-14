package project.highthon.JoMO.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.highthon.JoMO.domain.auth.dto.req.LoginDto;
import project.highthon.JoMO.domain.auth.dto.res.TokenResponseDto;
import project.highthon.JoMO.domain.user.service.UserService;
import project.highthon.JoMO.global.config.jwt.JwtUtil;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public TokenResponseDto createUserToken(LoginDto dto) {

        userService.login(dto);

        return TokenResponseDto.builder()
                .accessToken(jwtUtil.generateAccessToken(dto.getEmail()))
                .refreshToken(jwtUtil.generateRefreshToken(dto.getEmail()))
                .build();
    }
}
