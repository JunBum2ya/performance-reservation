package com.wanted.preonboarding.ticket.domain;

import com.wanted.preonboarding.core.converter.EnableConverter;
import com.wanted.preonboarding.core.domain.AuditingDateTimeEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "performance_seat_info",uniqueConstraints = @UniqueConstraint(name = "seat_unique_key",columnNames = {
        "performance_id","round","gate","line","seat"
}))
public class PerformanceSeat extends AuditingDateTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Comment("공연전시ID")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance_id",nullable = false)
    private Performance performance;
    @Comment("회차(FK)")
    @Column(nullable = false)
    private int round;
    @Comment("입장 게이트")
    @Column(nullable = false)
    private int gate;
    @Comment("좌석 열")
    @Column(nullable = false)
    private char line;
    @Comment("좌석 행")
    @Column(nullable = false)
    private int seat;
    @Convert(converter = EnableConverter.class)
    @Column(nullable = false)
    private boolean reserve = true;

}
