package com.wanted.preonboarding.ticket.repository;

import com.wanted.preonboarding.ticket.domain.Performance;
import com.wanted.preonboarding.ticket.domain.PerformanceSeat;
import com.wanted.preonboarding.ticket.domain.constant.PerformanceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("testdb")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("[Model] Performance")
@DataJpaTest
public class PerformanceRepositoryTest {

    private List<Performance> testCases;

    @Autowired
    private PerformanceRepository performanceRepository;

    @BeforeEach
    public void addTestPerformance() {
        testCases = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            testCases.add(performanceRepository.save(createPerformance()));
        }
    }

    @DisplayName("performance를 저장할 경우 performance가 반환된다.")
    @Test
    public void givenPerformance_whenSavingPerformance_thenReturnsPerformance() {
        //given
        Performance performance = createPerformance();
        //when
        Performance savedPerformance = performanceRepository.save(performance);
        //then
        assertThat(savedPerformance).isNotNull();
        assertThat(savedPerformance.getId()).isNotNull();
        assertThat(savedPerformance.getSeats().size()).isEqualTo(3);
        assertThat(savedPerformance.getSeats().stream().allMatch(performanceSeat -> performanceSeat.getId() != null)).isTrue();
    }

    @DisplayName("전체 검색 시 List를 반환 받는다.")
    @Test
    public void givenNothing_whenFindAllPerformance_thenReturnsPerformanceList() {
        //given
        //when
        List<Performance> performanceList = performanceRepository.findAll();
        //then
        assertThat(performanceList).isNotEmpty();
        assertThat(performanceList.size()).isEqualTo(testCases.size());
    }

    @DisplayName("UUID를 이용하여 검색을 할 경우 Performance를 반환받는다.")
    @Test
    public void givenUUID_whenFindPerformance_thenReturnsPerformance() {
        //given
        UUID uuid = testCases.getFirst().getId();
        //when
        Optional<Performance> performance = performanceRepository.findById(uuid);
        //then
        assertThat(performance).isNotEmpty();
        assertThat(performance.map(Performance::getSeats).orElse(new HashSet<>()).size()).isEqualTo(3);
    }

    @DisplayName("performance를 수정한다.")
    @Test
    public void givenPerformance_whenUpdatePerformance_thenModifiedPerformance() {
        //given
        UUID uuid = testCases.getFirst().getId();
        Performance performance = performanceRepository.findById(uuid).orElseThrow();
        //when
        performance.update("위키드",5000,3,PerformanceType.CONCERT,LocalDateTime.now());
        //then
        Performance updatedPerformance = performanceRepository.findById(uuid).orElseThrow();
        assertThat(updatedPerformance).isNotNull();
        assertThat(updatedPerformance.getName()).isEqualTo("위키드");
        assertThat(updatedPerformance.getPrice()).isEqualTo(5000);
        assertThat(updatedPerformance.getRound()).isEqualTo(3);
        assertThat(updatedPerformance.getType()).isEqualTo(PerformanceType.CONCERT);
    }

    @DisplayName("UUID를 이용하여 삭제를 한다.")
    @Test
    public void givenUUID_whenDeletePerformance_thenReturnsNothing() {
        //given
        UUID uuid = testCases.getFirst().getId();
        //when
        performanceRepository.deleteById(uuid);
        //then
        Optional<Performance> performance = performanceRepository.findById(uuid);
        assertThat(performance).isEmpty();
    }

    private Performance createPerformance() {
        return Performance.builder()
                .name("위시")
                .price(30000)
                .type(PerformanceType.CONCERT)
                .round(3)
                .startDate(LocalDateTime.now())
                .seats(List.of(createPerformanceSeat(1,'A',1)
                        ,createPerformanceSeat(1,'A',2)
                        ,createPerformanceSeat(1,'B',1))
                )
                .build();
    }

    private PerformanceSeat createPerformanceSeat(int gate,char line, int seat) {
        return PerformanceSeat.builder()
                .gate(gate)
                .line(line)
                .seat(seat)
                .reserve(true)
                .build();
    }
}
