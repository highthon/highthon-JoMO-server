package project.highthon.JoMO.domain.user.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import project.highthon.JoMO.domain.accumulate.domain.Accumulate;
import project.highthon.JoMO.domain.user.dto.req.UserInfoRequestDto;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String pw;

    @NotNull
    private String phone;

    @NotNull
    private Long point;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Accumulate> histories = new ArrayList<>();

    public User(UserInfoRequestDto userDto) {
        this.email = userDto.getEmail();
        this.pw = userDto.getPw();
        this.name = userDto.getName();
        this.phone = userDto.getPhone();
        this.point = 0L;
    }
}
