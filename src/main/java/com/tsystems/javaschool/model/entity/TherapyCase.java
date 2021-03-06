package com.tsystems.javaschool.model.entity;

import com.tsystems.javaschool.model.entity.enums.TherapyStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "therapy_case")
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class TherapyCase {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "timeCase", nullable = false)
    private LocalTime time;

    @Column(name = "status", nullable = false)
    private TherapyStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nurse_id")
    private UserEntity nurse;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "therapy_id", nullable = false)
    private Therapy therapy;
}
