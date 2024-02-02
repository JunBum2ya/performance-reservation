package com.wanted.preonboarding.ticket.dto;

import com.wanted.preonboarding.ticket.domain.PerformanceSeat;
import com.wanted.preonboarding.ticket.domain.Reservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class ReservationInfo {
    private Long id;
    private PerformanceSeatInfo performanceSeatInfo;
    private String name;
    private String phoneNumber;

    public static ReservationInfo of(Reservation reservation) {
        return ReservationInfo.builder()
                .id(reservation.getId())
                .performanceSeatInfo(PerformanceSeatInfo.of(reservation.getPerformanceSeat()))
                .name(reservation.getName())
                .phoneNumber(reservation.getPhoneNumber())
                .build();
    }

    public Reservation toEntity(PerformanceSeat performanceSeat) {
        return Reservation.builder()
                .performanceSeat(performanceSeat)
                .name(name)
                .phoneNumber(phoneNumber)
                .build();
    }

    public Reservation toEntity() {
        return toEntity(performanceSeatInfo.toEntity());
    }

}
