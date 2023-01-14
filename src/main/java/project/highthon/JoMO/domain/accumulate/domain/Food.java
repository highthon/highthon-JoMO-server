package project.highthon.JoMO.domain.accumulate.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Food {

    COFFEE("별다방 아이스 아메리카노", 2000),
    CHICKEN("후라이드 치킨", 15000);

    private final String menu;
    private final int price;
}
