package project.highthon.JoMO.domain.auth.dto.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginDto {

    @NotBlank
    private String email;
    @NotBlank
    private String pw;
}
