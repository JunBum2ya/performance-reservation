package com.wanted.preonboarding.ticket.repository;

import com.wanted.preonboarding.ticket.domain.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Page<Reservation> findAllByNameAndPhoneNumberOrderByCreatedAt(String name, String phoneNumber,Pageable pageable);
}