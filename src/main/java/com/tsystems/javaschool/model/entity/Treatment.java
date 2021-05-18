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
@Table(name = "treatment")
@Getter @Setter @NoArgsConstructor
@EqualsAndHashCode
public class Treatment {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "diagnosis", nullable = false)
    @Size(min = 1, max = 45)
    private String diagnosis;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "doctor_id")
    private UserEntity doctor;

    @OneToMany(mappedBy = "treatment", cascade = CascadeType.ALL)
    private List<Therapy> therapies;
}
