package project.highthon.JoMO.domain.accumulate.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class AccumulateResponseDto {

    private Long amount;
    private LocalDateTime dateTime;
}
