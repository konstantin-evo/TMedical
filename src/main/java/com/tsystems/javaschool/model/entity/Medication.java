package com.tsystems.javaschool.model.entity;

import com.tsystems.javaschool.model.entity.enums.TreatmentType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "medication")
@Getter @Setter @NoArgsConstructor
@EqualsAndHashCode
public class Medication {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TreatmentType type;

    @Column(name = "name", nullable = false, unique=true)
    @Size(min = 1, max = 25)
    private String name;

    @OneToMany(mappedBy = "medication")
    private List<Therapy> therapies;
}
