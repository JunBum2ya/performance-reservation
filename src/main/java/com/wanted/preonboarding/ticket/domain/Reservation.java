package com.wanted.preonboarding.ticket.domain;

import com.wanted.preonboarding.core.domain.AuditingDateTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @Comment("예약자명")
    @Column(nullable = false)
    private String name;
    @Comment("예약자 휴대전화 번호")
    @Column(nullable = false)
    private String phoneNumber;
}
