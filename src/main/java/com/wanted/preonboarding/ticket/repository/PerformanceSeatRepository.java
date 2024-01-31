package com.wanted.preonboarding.ticket.repository;

import com.wanted.preonboarding.ticket.domain.PerformanceSeat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceSeatRepository extends JpaRepository<PerformanceSeat, Long> {
}