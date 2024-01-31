package com.wanted.preonboarding.ticket.repository;

import com.wanted.preonboarding.ticket.domain.Performance;
import com.wanted.preonboarding.ticket.domain.PerformanceSeat;
import com.wanted.preonboarding.ticket.domain.Reservation;
import com.wanted.preonboarding.ticket.domain.constant.PerformanceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("testdb")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("[Model] Reservation")
@DataJpaTest
public class ReservationRepositoryTest {
    @Autowired
    private PerformanceRepository performanceRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    private PerformanceSeat performanceSeat;
    private List<Reservation> reservationList;

    @BeforeEach
    public void initTestPerformance() {
        this.reservationList = new ArrayList<>();
        Performance performance = createPerformance();
        performanceRepository.save(performance);
        this.performanceSeat = performance.getSeats().stream().findFirst().orElseThrow();
        for(PerformanceSeat seat : performance.getSeats()) {
            Reservation reservation = createReservation(seat);
            this.reservationList.add(reservationRepository.save(reservation));
        }
    }

    @DisplayName("reservation을 저장하면 reservation이 반환된다.")
    @Test
    public void givenReservation_thenSaveReservation_thenReturnsReservation() {
        //given
        Reservation reservation = createReservation(performanceSeat);
        //when
        Reservation savedReservation = reservationRepository.save(reservation);
        //then
        assertThat(savedReservation).isNotNull();
        assertThat(savedReservation.getId()).isNotNull();
    }

    @DisplayName("전체 검색을 하면 전체 리스트가 반환된다.")
    @Test
    public void givenNothing_whenFindAllReservation_thenReturnsReservationList() {
        //given
        //when
        List<Reservation> reservationList = reservationRepository.findAll();
        //then
        assertThat(reservationList).isNotEmpty();
        assertThat(reservationList.size()).isEqualTo(3);
    }

    @DisplayName("ID로 검색을 하면 reservation이 반환된다")
    @Test
    public void givenId_whenFindReservation_thenReturnsReservation() {
        //given
        Long id = this.reservationList.getFirst().getId();
        //when
        Optional<Reservation> reservation = reservationRepository.findById(id);
        //then
        assertThat(reservation).isNotEmpty();
        assertThat(reservation.get().getPhoneNumber()).isEqualTo("010-1234-5678");
    }

    @DisplayName("ID로 삭제를 한다.")
    @Test
    public void givenId_whenDeleteReservation_thenReturnsNothing() {
        //given
        Long id = this.reservationList.getFirst().getId();
        //when
        reservationRepository.deleteById(id);
        //then
        Optional<Reservation> reservation = reservationRepository.findById(id);
        assertThat(reservation).isEmpty();
    }

    private Reservation createReservation(PerformanceSeat performanceSeat) {
        return Reservation.builder()
                .performanceSeat(performanceSeat)
                .name("testUser")
                .phoneNumber("010-1234-5678")
                .build();
    }

    private Performance createPerformance() {
        return Performance.builder()
                .name("위시")
                .price(30000)
                .type(PerformanceType.CONCERT)
                .round(3)
                .startDate(LocalDateTime.now())
                .seats(List.of(createPerformanceSeat(1,'A','1')
                        ,createPerformanceSeat(1,'A','2')
                        ,createPerformanceSeat(1,'B','1'))
                )
                .build();
    }

    private PerformanceSeat createPerformanceSeat(int gate, char line, int seat) {
        return PerformanceSeat.builder()
                .gate(gate)
                .line(line)
                .seat(seat)
                .reserve(true)
                .build();
    }
}
