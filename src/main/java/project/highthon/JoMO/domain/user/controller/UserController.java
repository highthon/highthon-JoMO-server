package project.highthon.JoMO.domain.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.highthon.JoMO.domain.user.domain.User;
import project.highthon.JoMO.domain.user.dto.req.EmailExistenceDto;
import project.highthon.JoMO.domain.user.dto.req.UserInfoRequestDto;
import project.highthon.JoMO.domain.user.dto.res.UserInfoResponseDto;
import project.highthon.JoMO.domain.user.service.UserService;
import project.highthon.JoMO.global.annotation.CheckToken;
import project.highthon.JoMO.global.response.DataResponse;
import project.highthon.JoMO.global.response.Response;

@Api(tags = "User-Controller")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "회원가입")
    @PostMapping("/join")
    public ResponseEntity<Response> join(@Valid @RequestBody UserInfoRequestDto requestDto) {
        userService.save(requestDto);
        return Response.ok("회원가입 성공");
    }

    @PostMapping("/existence")
    public ResponseEntity<Response> exist(@Valid @RequestBody EmailExistenceDto dto) {
        userService.exist(dto);
        return Response.ok("올바른 Email");
    }

    @CheckToken
    @ApiOperation(value = "AccessToken 인증으로 내정보 받기")
    @GetMapping("/my")
    public ResponseEntity<DataResponse<UserInfoResponseDto>> getUserInfo(@RequestAttribute User user) {
        return DataResponse.ok("유저 정보 조회 성공", new UserInfoResponseDto(user));
    }
}
