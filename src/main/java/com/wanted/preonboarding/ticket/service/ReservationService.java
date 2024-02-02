package com.wanted.preonboarding.ticket.service;

import com.wanted.preonboarding.ticket.domain.PerformanceSeat;
import com.wanted.preonboarding.ticket.domain.Reservation;
import com.wanted.preonboarding.ticket.dto.PerformanceSeatInfo;
import com.wanted.preonboarding.ticket.dto.ReservationInfo;
import com.wanted.preonboarding.ticket.repository.PerformanceSeatRepository;
import com.wanted.preonboarding.ticket.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class ReservationService {
    private final PerformanceSeatRepository performanceSeatRepository;
    private final ReservationRepository reservationRepository;

    /**
     * 예약 기능
     * @param reservationInfo
     * @return
     */
    public ReservationInfo reserve(ReservationInfo reservationInfo) {
        PerformanceSeatInfo performanceSeatInfo = reservationInfo.getPerformanceSeatInfo();
        PerformanceSeat performanceSeat = performanceSeatRepository
                .findPerformanceSeatByGateAndLineAndSeatAndPerformance_Id(performanceSeatInfo.getGate(),performanceSeatInfo.getLine(), performanceSeatInfo.getSeat(), performanceSeatInfo.getPerformanceInfo().getId())
                .orElseThrow();
        performanceSeat.reserve(); //좌석 예약 처리
        Reservation reservation = reservationRepository.save(reservationInfo.toEntity(performanceSeat)); //DB 저장
        return ReservationInfo.of(reservation);
    }

    @Transactional(readOnly = true)
    public Page<ReservationInfo> searchReservation(String name, String phoneNumber, Pageable pageable) {
        return reservationRepository.findAllByNameAndPhoneNumberOrderByCreatedAt(name,phoneNumber,pageable)
                .map(ReservationInfo::of);
    }

}
