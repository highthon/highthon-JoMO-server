package project.highthon.JoMO.domain.accumulate.dto.req;

import jakarta.validation.constraints.Positive;
import lombok.Getter;
import project.highthon.JoMO.domain.accumulate.domain.Category;

@Getter
public class AccumulateRequestDto {

    private Category category;
    private String description;
    @Positive
    private Long amount;
}
