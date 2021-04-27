package com.tsystems.javaschool.model.entity;

import com.tsystems.javaschool.model.entity.enums.TherapyStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.Clock;
import java.time.LocalTime;

@Entity
@Table(name = "therapy_case")
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class TherapyCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private TherapyStatus status;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "time", nullable = false)
    private LocalTime time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nurse_id", nullable = false)
    private UserEntity nurse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "therapy_id", nullable = false)
    private Therapy therapy;
}
