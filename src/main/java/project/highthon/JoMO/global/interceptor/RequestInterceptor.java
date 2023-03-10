package project.highthon.JoMO.global.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import project.highthon.JoMO.domain.auth.dto.res.AccessTokenDto;
import project.highthon.JoMO.domain.user.domain.User;
import project.highthon.JoMO.global.annotation.CheckToken;
import project.highthon.JoMO.global.config.jwt.JwtUtil;
import project.highthon.JoMO.global.config.jwt.TokenType;
import project.highthon.JoMO.global.error.CustomError;
import project.highthon.JoMO.global.error.ErrorCode;

import java.util.Enumeration;

@Component
@RequiredArgsConstructor
public class RequestInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        if (!(handlerMethod.getMethod().isAnnotationPresent(CheckToken.class))) {
            return true;
        }

        try {
            String token = getTokenOfRequest(request).split(" ")[1];

            TokenType tokenType = jwtUtil.checkTokenType(token);
            User user = jwtUtil.getUserByToken(token);

            if (checkTokenType(request, tokenType, user) == true) {
                return true;
            }

            request.setAttribute("user", user);

        } catch (ArrayIndexOutOfBoundsException e) {
            throw CustomError.of(ErrorCode.TOKEN_NOT_PROVIDED);
        }

        return true;
    }

    private String getTokenOfRequest(HttpServletRequest request) {
        Enumeration<String> headers = request.getHeaders("Authorization");

        while (headers.hasMoreElements()) {
            String value = headers.nextElement();
            if (value != null) {
                return value;
            }
        }

        return Strings.EMPTY;
    }

    private boolean checkTokenType(HttpServletRequest request, TokenType tokenType, User user) {
        if (tokenType.equals(TokenType.REFRESHTOKEN)) {

            String accessToken = jwtUtil.generateAccessToken(user.getEmail());
            AccessTokenDto accessTokenDto = new AccessTokenDto(accessToken);
            request.setAttribute("accessToken", accessTokenDto);

            return true;
        } else return false;
    }
}
