package project.highthon.JoMO.domain.user.dto.res;

import lombok.*;
import project.highthon.JoMO.domain.user.domain.User;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserInfoResponseDto {

    private String name;
    private String email;
    private String phone;
    private Long point;

    public UserInfoResponseDto(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.point = user.getPoint();
        this.phone = user.getPhone();
    }
}
