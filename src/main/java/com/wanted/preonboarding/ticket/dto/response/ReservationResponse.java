package com.wanted.preonboarding.ticket.dto.response;

import com.wanted.preonboarding.ticket.dto.ReservationInfo;

public class ReservationResponse {
    private String name;
    private String phoneNumber;
    private String performanceId;
    private int round;
    private String performanceName;
    private int gate;
    private char line;
    private int seat;

    public static ReservationResponse of(ReservationInfo reservationInfo) {
        return null;
    }

}
