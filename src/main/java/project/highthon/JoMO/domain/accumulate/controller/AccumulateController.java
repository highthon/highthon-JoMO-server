package project.highthon.JoMO.domain.accumulate.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.highthon.JoMO.domain.accumulate.dto.req.AccumulateRequestDto;
import project.highthon.JoMO.domain.accumulate.dto.res.AccumulateCreateResponseDto;
import project.highthon.JoMO.domain.accumulate.dto.res.AccumulateResponseDto;
import project.highthon.JoMO.domain.accumulate.service.AccumulateService;
import project.highthon.JoMO.domain.user.domain.User;
import project.highthon.JoMO.global.annotation.CheckToken;
import project.highthon.JoMO.global.response.DataResponse;

import java.util.List;

@RestController
@RequestMapping("/accumulate")
@RequiredArgsConstructor
public class AccumulateController {

    private final AccumulateService accumulateService;

    @CheckToken
    @PostMapping("")
    public ResponseEntity<DataResponse<AccumulateCreateResponseDto>> createAccumulate(@Valid @RequestBody AccumulateRequestDto dto, @RequestAttribute User user) {
        AccumulateCreateResponseDto responseDto = accumulateService.create(dto, user);
        return DataResponse.ok("적립 성공", responseDto);
    }

    @CheckToken
    @GetMapping("/list")
    public ResponseEntity<DataResponse<List<AccumulateResponseDto>>> getAccumulate(@RequestAttribute User user) {
        List<AccumulateResponseDto> list = accumulateService.getAccumulate(user);
        return DataResponse.ok("적립 내역 조회 성공", list);
    }
}
