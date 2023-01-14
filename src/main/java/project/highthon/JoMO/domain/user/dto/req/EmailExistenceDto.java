package project.highthon.JoMO.domain.user.dto.req;

import jakarta.validation.constraints.Email;
import lombok.Getter;

@Getter
public class EmailExistenceDto {

    @Email
    private String email;
}
