package project.highthon.JoMO.domain.accumulate.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {

    TREND("유행비용"),
    ELECTRONIC("전자제품"),
    COSMETIC("화장품/이너"),
    DELIVERY("외식/배달"),
    INITIAL("시발비용"),
    CLOTH("의류/신발"),
    FURNITURE("인테리어/가구");


    private final String title;
}
