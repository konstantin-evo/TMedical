package com.tsystems.javaschool.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "therapy")
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class Therapy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "time pattern", nullable = false)
    @Size(min = 1, max = 45)
    private String time_pattern;

    @Column(name = "start_date", nullable = false)
    private LocalDate start_date;

    @Column(name = "end_date", nullable = false)
    private LocalDate end_date;

    @Column(name = "dose")
    @Size(min = 1, max = 25)
    private String dose;

    @Column(name = "number", nullable = false)
    @Min(0)
    private int number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treat_id")
    private Treatment treatment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medication")
    private Medication medication;
}
