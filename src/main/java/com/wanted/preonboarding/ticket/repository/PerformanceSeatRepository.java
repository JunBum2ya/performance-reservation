package com.wanted.preonboarding.ticket.repository;

import com.wanted.preonboarding.ticket.domain.PerformanceSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PerformanceSeatRepository extends JpaRepository<PerformanceSeat, Long> {
    Optional<PerformanceSeat> findPerformanceSeatByGateAndLineAndSeatAndPerformance_Id(int gate, char line, int seat, UUID performance_id);
}