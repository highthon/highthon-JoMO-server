package project.highthon.JoMO.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.highthon.JoMO.domain.auth.dto.req.LoginDto;
import project.highthon.JoMO.domain.user.domain.User;
import project.highthon.JoMO.domain.user.domain.UserRepository;
import project.highthon.JoMO.domain.user.dto.req.EmailExistenceDto;
import project.highthon.JoMO.domain.user.dto.req.UserInfoRequestDto;
import project.highthon.JoMO.global.error.CustomError;
import project.highthon.JoMO.global.error.ErrorCode;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User save(UserInfoRequestDto dto) {
        Optional<User> byEmail = userRepository.findByEmail(dto.getEmail());
        return byEmail.orElseGet(() -> userRepository.save(new User(dto)));
    }

    public void exist(EmailExistenceDto dto) {
        Optional<User> byEmail = userRepository.findByEmail(dto.getEmail());
        byEmail.ifPresent(user -> { throw CustomError.of(ErrorCode.EXIST_EMAIL); });
    }

    public void login(LoginDto dto) {
        Optional.ofNullable(userRepository.findByEmail(dto.getEmail())).ifPresentOrElse(user -> {
            if(user.get().getPw()!= dto.getPw()){
                CustomError.of(ErrorCode.WRONG_PW);
            }
        }, () -> {CustomError.of(ErrorCode.WRONG_EMAIL);});
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> {
            throw CustomError.of(ErrorCode.NOT_FOUND);
        });
    }
}
