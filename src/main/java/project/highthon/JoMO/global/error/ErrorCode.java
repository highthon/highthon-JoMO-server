package project.highthon.JoMO.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    WRONG_EMAIL(HttpStatus.NOT_FOUND, "존재하지 않는 유저."),
    WRONG_PW(HttpStatus.CONFLICT, "회원 pw 불일치"),
    EXIST_EMAIL(HttpStatus.CONFLICT, "중복된 Email"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "찾을 수 없습니다"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류"),
    INVALID_TOKEN(HttpStatus.FORBIDDEN, "유효하지 않은 토큰"),
    TOKEN_NOT_PROVIDED(HttpStatus.BAD_REQUEST, "토큰이 입력되지 않았습니다"),
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "만료된 토큰"),
    PARAMETER_IS_BAD(HttpStatus.BAD_REQUEST, "파라미터가 잘못됐습니다");

    private final HttpStatus httpStatus;
    private final String message;
}
