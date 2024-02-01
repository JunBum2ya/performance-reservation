package com.wanted.preonboarding.ticket.service;

import com.wanted.preonboarding.ticket.domain.PerformanceSeat;
import com.wanted.preonboarding.ticket.domain.Reservation;
import com.wanted.preonboarding.ticket.dto.ReservationInfo;
import com.wanted.preonboarding.ticket.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationInfo reserve(ReservationInfo reservationInfo) {
        Reservation reservation = reservationRepository.save(reservationInfo.toEntity()); //DB 저장
        PerformanceSeat performanceSeat = reservation.getPerformanceSeat();
        performanceSeat.reserve(); //좌석 예약 처리
        return ReservationInfo.of(reservation);
    }

}
