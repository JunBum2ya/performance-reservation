package com.wanted.preonboarding.ticket.dto;

import com.wanted.preonboarding.ticket.domain.PerformanceSeat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class PerformanceSeatInfo {
    private Long id;
    private PerformanceInfo performanceInfo;
    private int gate;
    private char line;
    private int seat;
    private boolean reserve;

    public static PerformanceSeatInfo of(PerformanceSeat performanceSeat) {
        return PerformanceSeatInfo.builder()
                .id(performanceSeat.getId())
                .performanceInfo(PerformanceInfo.of(performanceSeat.getPerformance()))
                .gate(performanceSeat.getGate())
                .line(performanceSeat.getLine())
                .seat(performanceSeat.getSeat())
                .reserve(performanceSeat.isReserve())
                .build();
    }
    public PerformanceSeat toEntity() {
        return PerformanceSeat.builder()
                .performance(performanceInfo.toEntity())
                .gate(gate)
                .line(line)
                .seat(seat)
                .reserve(reserve)
                .build();
    }

}
