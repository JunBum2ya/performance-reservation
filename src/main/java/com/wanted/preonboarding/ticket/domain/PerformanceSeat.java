package com.wanted.preonboarding.ticket.domain;

import com.wanted.preonboarding.core.converter.EnableConverter;
import com.wanted.preonboarding.core.domain.AuditingDateTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "performance_seat_info"
        ,uniqueConstraints = @UniqueConstraint(name = "performance_seat_info_unique",columnNames = {
                "performance_id","gate","line","seat"
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
    @OneToOne(mappedBy = "performanceSeat")
    private Reservation reservation;

    public void setPerformance(Performance performance) {
        if(this.performance != null) {
            this.performance.getSeats().remove(this);
        }
        this.performance = performance;
    }

    public void update(int gate,char line,int seat,boolean reserve) {
        this.gate = gate;
        this.line = line;
        this.seat = seat;
        this.reserve = reserve;
    }

    public void reserve() {
        this.reserve = true;
    }

    public void cancel() {
        this.reserve = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PerformanceSeat that = (PerformanceSeat) o;
        return gate == that.gate && line == that.line && seat == that.seat && Objects.equals(id, that.id) && Objects.equals(performance, that.performance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, performance, gate, line, seat);
    }
}
