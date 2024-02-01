package com.wanted.preonboarding.ticket.service;

import com.wanted.preonboarding.ticket.domain.PerformanceSeat;
import com.wanted.preonboarding.ticket.domain.Reservation;
import com.wanted.preonboarding.ticket.dto.ReservationInfo;
import com.wanted.preonboarding.ticket.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationInfo reserve(ReservationInfo reservationInfo) {
        Reservation reservation = reservationRepository.save(reservationInfo.toEntity()); //DB 저장
        PerformanceSeat performanceSeat = reservation.getPerformanceSeat();
        performanceSeat.reserve(); //좌석 예약 처리
        return ReservationInfo.of(reservation);
    }

    @Transactional(readOnly = true)
    public Page<ReservationInfo> searchReservation(String name, String phoneNumber, Pageable pageable) {
        return reservationRepository.findAllByNameAndPhoneNumberOrderByCreatedAt(name,phoneNumber,pageable)
                .map(ReservationInfo::of);
    }

}
