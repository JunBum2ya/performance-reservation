package com.wanted.preonboarding.ticket.service;

import com.wanted.preonboarding.ticket.domain.Performance;
import com.wanted.preonboarding.ticket.dto.PerformanceInfo;
import com.wanted.preonboarding.ticket.repository.PerformanceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PerformanceService {
    private final PerformanceRepository performanceRepository;

    public Page<PerformanceInfo> searchPerformance(boolean isReserve, Pageable pageable) {
        return performanceRepository.findAll(pageable).map(PerformanceInfo::of);
    }

    public Optional<PerformanceInfo> getPerformance(UUID uuid) {
        return performanceRepository.findById(uuid).map(PerformanceInfo::of);
    }
}
