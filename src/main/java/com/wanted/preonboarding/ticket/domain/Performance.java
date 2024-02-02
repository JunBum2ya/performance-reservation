package com.wanted.preonboarding.ticket.domain;

import com.wanted.preonboarding.core.converter.EnableConverter;
import com.wanted.preonboarding.ticket.domain.constant.PerformanceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.*;

@Getter
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
    @Comment("NONE, CONCERT, EXHIBITION")
    @Column(nullable = false)
    private PerformanceType type;
    @Comment("공연 일시")
    @Column(nullable = false)
    private LocalDateTime startDate;
    @Convert(converter = EnableConverter.class)
    @Column(nullable = false)
    private boolean reserve;
    @OneToMany(mappedBy = "performance",cascade = CascadeType.ALL)
    private final Set<PerformanceSeat> seats;

    public Performance() {
        this.seats = new LinkedHashSet<>();
    }

    @Builder
    public Performance(UUID id,String name, int price, int round, PerformanceType type, LocalDateTime startDate, Collection<PerformanceSeat> seats) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.round = round;
        this.type = type;
        this.startDate = startDate;
        this.seats = new LinkedHashSet<>();
        if(seats != null) {
            seats.forEach(seat -> {
                seat.setPerformance(this);
                this.seats.add(seat);
            });
        }
    }

    public void update(String name, int price, int round, PerformanceType type, LocalDateTime startDate) {
        this.name = name;
        this.price = price;
        this.round = round;
        this.type = type;
        this.startDate = startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Performance that = (Performance) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
