package com.wanted.preonboarding.ticket.dto.request;

import com.wanted.preonboarding.ticket.dto.PerformanceInfo;
import com.wanted.preonboarding.ticket.dto.PerformanceSeatInfo;
import com.wanted.preonboarding.ticket.dto.ReservationInfo;

import java.util.UUID;

public class ReservationRequest {
    private String name;
    private String phoneNumber;
    private Integer amount;
    private String performanceId;
    private Integer round;
    private Integer gate;
    private Character line;
    private Integer seat;

    public ReservationInfo toDto() {
        return ReservationInfo.builder()
                .name(name)
                .phoneNumber(phoneNumber)
                .performanceSeatInfo(PerformanceSeatInfo.builder()
                        .gate(gate)
                        .line(line)
                        .seat(seat)
                        .performanceInfo(PerformanceInfo.builder()
                                .id(UUID.fromString(performanceId))
                                .round(round)
                                .build()
                        )
                        .build()
                )
                .build();
    }

}
