package project.highthon.JoMO.domain.auth.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.highthon.JoMO.domain.auth.dto.req.LoginDto;
import project.highthon.JoMO.domain.auth.dto.res.AccessTokenDto;
import project.highthon.JoMO.domain.auth.dto.res.TokenResponseDto;
import project.highthon.JoMO.domain.auth.service.AuthService;
import project.highthon.JoMO.global.annotation.CheckToken;
import project.highthon.JoMO.global.response.DataResponse;

@Api(tags = "Auth-Controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @ApiOperation(value = "Login & Token 받기", notes = "Login 성공 시 AccessToken 과 RefreshToken 을 얻는다.")
    @PostMapping("/login")
    public ResponseEntity<DataResponse<TokenResponseDto>> login(@Valid @RequestBody LoginDto dto){
        TokenResponseDto token = authService.createUserToken(dto);
        return DataResponse.ok("인증 성공", token);
    }

    @CheckToken
    @GetMapping("/refreshToken")
    public ResponseEntity<DataResponse<AccessTokenDto>> getAccessToken(@RequestAttribute("accessToken") AccessTokenDto accessTokenDto) {
        return DataResponse.ok("토큰 생성 성공", accessTokenDto);
    }
}

