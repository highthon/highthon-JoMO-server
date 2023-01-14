package project.highthon.JoMO.domain.accumulate.dto.res;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import project.highthon.JoMO.domain.accumulate.domain.Food;

@Getter
@Builder
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class AccumulateCreateResponseDto {

    private Long amount;
    private Food food;
    private Long count;
}
