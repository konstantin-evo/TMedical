package com.tsystems.javaschool.model.entity;

import com.tsystems.javaschool.model.entity.enums.Treatment;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "medication")
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private Treatment type;

    @Column(name = "name", nullable = false)
    @Size(min = 1, max = 25)
    private String name;

    @OneToMany(mappedBy = "medication")
    private List<Therapy> Therapies;
}
