package com.tsystems.javaschool.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "patient")
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "id_user")
    private List<Insurance> Insurances;

    @OneToMany(mappedBy = "id_user")
    private List<Treatment> Treatments;
}
