package com.wanted.preonboarding.ticket.service;

import com.wanted.preonboarding.ticket.domain.Performance;
import com.wanted.preonboarding.ticket.domain.PerformanceSeat;
import com.wanted.preonboarding.ticket.domain.constant.PerformanceType;
import com.wanted.preonboarding.ticket.dto.PerformanceInfo;
import com.wanted.preonboarding.ticket.repository.PerformanceRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("[비즈니스 로직] 공연 & 전시장")
@ExtendWith(MockitoExtension.class)
public class PerformanceServiceTest {
    @InjectMocks
    private PerformanceService performanceService;
    @Mock
    private PerformanceRepository performanceRepository;

    @DisplayName("전체 검색을 했을 경우 PerformanceInfo 페이지를 반환한다.")
    @Test
    public void givenNothing_whenSearchPerformance_thenReturnsPerformanceInfoPage() {
        //given
        given(performanceRepository.findAll(any(Pageable.class))).willReturn(Page.empty());
        //when
        Page<PerformanceInfo> performanceInfoPage = performanceService.searchPerformance(true,Pageable.ofSize(10));
        //then
        then(performanceRepository).should().findAll(any(Pageable.class));
        assertThat(performanceInfoPage).isEmpty();
    }

    @DisplayName("UUID로 PerformanceInfo를 찾는다.")
    @Test
    public void givenUUID_whenGetPerformance_thenReturnsPerformanceInfo() {
        //given
        UUID uuid = UUID.randomUUID();
        given(performanceRepository.findById(any(UUID.class))).willReturn(Optional.of(createPerformance()));
        //when
        Optional<PerformanceInfo> performanceInfo = performanceService.getPerformance(uuid);
        //then
        then(performanceRepository).should().findById(any(UUID.class));
        assertThat(performanceInfo).isNotEmpty();
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
