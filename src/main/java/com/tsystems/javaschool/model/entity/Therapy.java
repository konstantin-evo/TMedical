package com.tsystems.javaschool.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "therapy")
@Getter @Setter @NoArgsConstructor
@EqualsAndHashCode
public class Therapy {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "time_pattern", nullable = false)
    @Size(min = 1, max = 100)
    private String timePattern;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "dose")
    @Size(min = 1, max = 25)
    private String dose;

    @Column(name = "number", nullable = false)
    private int number;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "treat_id")
    private Treatment treatment;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "medication_id")
    private Medication medication;

    @OneToMany(mappedBy = "therapy")
    private List<TherapyCase> TherapyCases;
}
