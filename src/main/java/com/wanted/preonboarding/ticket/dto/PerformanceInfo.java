package com.wanted.preonboarding.ticket.dto;

import com.wanted.preonboarding.ticket.domain.Performance;
import com.wanted.preonboarding.ticket.domain.constant.PerformanceType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PerformanceInfo {
    private UUID id;
    private String name;
    private int price;
    private int round;
    private PerformanceType type;
    private LocalDateTime startDate;

    public static PerformanceInfo of(Performance performance) {
        return PerformanceInfo.builder()
                .id(performance.getId())
                .name(performance.getName())
                .price(performance.getPrice())
                .round(performance.getRound())
                .type(performance.getType())
                .startDate(performance.getStartDate())
                .build();
    }

    public Performance toEntity() {
        return Performance.builder()
                .name(name)
                .price(price)
                .round(round)
                .type(type)
                .startDate(startDate)
                .build();
    }

}
