package com.wanted.preonboarding.ticket.domain;

import com.wanted.preonboarding.core.domain.AuditingDateTimeEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "reservation")
public class Reservation extends AuditingDateTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance_id",nullable = false)
    private PerformanceSeat performanceSeat;
    @Column(nullable = false)
    private String name;
    private String phoneNumber;
    private int round;
    private int gate;
    private char line;
    private int seat;

}
