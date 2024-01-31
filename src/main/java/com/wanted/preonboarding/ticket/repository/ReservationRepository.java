package com.wanted.preonboarding.ticket.repository;

import com.wanted.preonboarding.ticket.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}