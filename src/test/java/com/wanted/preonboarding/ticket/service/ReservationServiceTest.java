package com.wanted.preonboarding.ticket.service;

import com.wanted.preonboarding.ticket.domain.Performance;
import com.wanted.preonboarding.ticket.domain.PerformanceSeat;
import com.wanted.preonboarding.ticket.domain.Reservation;
import com.wanted.preonboarding.ticket.domain.constant.PerformanceType;
import com.wanted.preonboarding.ticket.dto.ReservationInfo;
import com.wanted.preonboarding.ticket.repository.PerformanceSeatRepository;
import com.wanted.preonboarding.ticket.repository.ReservationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("[비즈니스 로직] 예약")
@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {
    @InjectMocks
    private ReservationService reservationService;
    @Mock
    private ReservationRepository reservationRepository;
    @Mock
    private PerformanceSeatRepository performanceSeatRepository;

    @DisplayName("reservationInfo의 데이터로 예약을 했을 경우 ReservationInfo가 반환됩니다.")
    @Test
    public void givenReservationInfo_whenReserve_thenReturnsReservationInfo() {
        //given
        Reservation reservation = createReservation(1,'A',3);
        ReservationInfo reservationInfo = ReservationInfo.of(reservation);
        given(performanceSeatRepository.findPerformanceSeatByGateAndLineAndSeatAndPerformance_Id(any(Integer.class),any(Character.class),any(Integer.class),any(UUID.class)))
                .willReturn(Optional.of(reservationInfo.getPerformanceSeatInfo().toEntity()));
        given(reservationRepository.save(any(Reservation.class))).willReturn(reservation);
        //when
        ReservationInfo testReservation = reservationService.reserve(reservationInfo);
        //then
        then(performanceSeatRepository).should().findPerformanceSeatByGateAndLineAndSeatAndPerformance_Id(any(Integer.class),any(Character.class),any(Integer.class),any(UUID.class));
        then(reservationRepository).should().save(any(Reservation.class));
        assertThat(testReservation.getName()).isEqualTo("testUser");
    }

    @DisplayName("고객 이름과 연락처 Pageable로 검색을 하면 ReservationInfo 페이지가 반환된다.")
    @Test
    public void givenNameAndPhoneNumberAndPageable_whenSearchReservation_thenReturnsReservationInfoPage() {
        //given
        String name = "testUser";
        String phoneNumber = "010-1234-5678";
        Pageable pageable = Pageable.ofSize(10);
        given(reservationRepository.findAllByNameAndPhoneNumberOrderByCreatedAt(any(String.class),any(String.class),any(Pageable.class)))
                .willReturn(Page.empty());
        //when
        Page<ReservationInfo> reservationInfoPage = reservationService.searchReservation(name,phoneNumber,pageable);
        //then
        then(reservationRepository).should().findAllByNameAndPhoneNumberOrderByCreatedAt(any(String.class),any(String.class),any(Pageable.class));
        assertThat(reservationInfoPage).isEmpty();
    }

    private Reservation createReservation(int gate, char line, int seat) {
        return Reservation.builder()
                .performanceSeat(createPerformanceSeat(gate,line,seat))
                .name("testUser")
                .phoneNumber("010-1234-5678")
                .build();
    }

    private Performance createPerformance() {
        return Performance.builder()
                .id(UUID.randomUUID())
                .name("위시")
                .price(30000)
                .type(PerformanceType.CONCERT)
                .round(3)
                .startDate(LocalDateTime.now())
                .seats(new ArrayList<>())
                .build();
    }

    private PerformanceSeat createPerformanceSeat(int gate, char line, int seat) {
        Performance performance = createPerformance();
        return PerformanceSeat.builder()
                .gate(gate)
                .line(line)
                .seat(seat)
                .reserve(true)
                .performance(performance)
                .build();
    }

}
