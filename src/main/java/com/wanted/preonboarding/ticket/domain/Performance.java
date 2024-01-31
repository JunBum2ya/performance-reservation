package com.wanted.preonboarding.ticket.domain;

import com.wanted.preonboarding.core.converter.EnableConverter;
import com.wanted.preonboarding.ticket.domain.constant.PerformanceType;
import jakarta.persistence.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "performance",
        uniqueConstraints = { @UniqueConstraint(name = "performance_unique_key", columnNames = {"id","round"}) })
public class Performance {
    @Id
    @Comment("공연/전시 ID")
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;
    @Comment("공연/전시 이름")
    @Column(nullable = false)
    private String name;
    @Comment("가격")
    @Column(nullable = false)
    private int price;
    @Comment("회차")
    @Column(nullable = false)
    private int round;
    @Column(nullable = false)
    private PerformanceType type;
    @Column(nullable = false)
    private LocalDateTime startDate;
    @Convert(converter = EnableConverter.class)
    @Column(name = "is_reserve", nullable = false)
    private boolean reserve = false;
}
