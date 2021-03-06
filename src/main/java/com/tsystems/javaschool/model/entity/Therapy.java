package com.tsystems.javaschool.model.entity;

import com.tsystems.javaschool.model.entity.enums.TherapyStatus;
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

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "dose")
    @Size(max = 100)
    private String dose;

    @Column(name = "number", nullable = false)
    private int number;

    @Column(name = "status", nullable = false)
    private TherapyStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treat_id", nullable = false)
    private Treatment treatment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medication_id")
    private Medication medication;

    @OneToMany(mappedBy = "therapy",cascade = CascadeType.ALL)
    private List<TherapyCase> therapyCases;
}
