package project.highthon.JoMO.domain.accumulate.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import project.highthon.JoMO.domain.user.domain.User;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "accumulate")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Accumulate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accumulate_id")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String description;

    @NotNull
    private Long amount;

    @NotNull
    @CreatedDate
    private LocalDateTime localDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public Accumulate(Category category, String description, Long amount, User user) {
        this.category = category;
        this.description = description;
        this.amount = amount;
        this.user = user;
    }
}
