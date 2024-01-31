package com.wanted.preonboarding.ticket.repository;

import com.wanted.preonboarding.ticket.domain.Performance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PerformanceRepository extends JpaRepository<Performance, UUID> {
}