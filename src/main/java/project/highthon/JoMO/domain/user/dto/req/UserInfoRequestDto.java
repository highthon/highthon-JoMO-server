package project.highthon.JoMO.domain.user.dto.req;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserInfoRequestDto {

    @NotBlank
    private String name;
    @Email
    private String email;
    @NotBlank
    private String pw;
    @NotBlank
    private String phone;
}
