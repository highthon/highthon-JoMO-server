package project.highthon.JoMO.domain.accumulate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.highthon.JoMO.domain.accumulate.domain.Accumulate;
import project.highthon.JoMO.domain.accumulate.domain.AccumulateRepository;
import project.highthon.JoMO.domain.accumulate.domain.Food;
import project.highthon.JoMO.domain.accumulate.dto.req.AccumulateRequestDto;
import project.highthon.JoMO.domain.accumulate.dto.res.AccumulateCreateResponseDto;
import project.highthon.JoMO.domain.accumulate.dto.res.AccumulateResponseDto;
import project.highthon.JoMO.domain.user.domain.User;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccumulateService {

    private final AccumulateRepository accumulateRepository;

    public List<AccumulateResponseDto> getAccumulate(User user) {
        return accumulateRepository.findAllByUser(user).stream()
                .map(accumulate -> AccumulateResponseDto.builder()
                        .amount(accumulate.getAmount())
                        .dateTime(accumulate.getLocalDateTime())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional
    public AccumulateCreateResponseDto create(AccumulateRequestDto dto, User user) {
        Accumulate accumulate = Accumulate.builder()
                .category(dto.getCategory())
                .description(dto.getDescription())
                .amount(dto.getAmount())
                .user(user)
                .build();
        accumulateRepository.save(accumulate);

        Food food = randomFood();

        int countFood = countFood(dto.getAmount(), food);

        return AccumulateCreateResponseDto.builder()
                .amount(dto.getAmount())
                .food(food)
                .count(Long.valueOf(countFood))
                .build();
    }

    private Food randomFood() {
        Random random = new Random();

        if(random.nextBoolean()==true) return Food.COFFEE;
        else return Food.CHICKEN;
    }

    private int countFood(Long amount, Food food) {
        return amount.intValue()/food.getPrice();
    }
}
