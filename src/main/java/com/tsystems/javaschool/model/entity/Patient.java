package com.tsystems.javaschool.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "patient")
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class Patient {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Insurance> insurances;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
    private List<Treatment> treatments;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_user")
    private UserEntity user;

}
