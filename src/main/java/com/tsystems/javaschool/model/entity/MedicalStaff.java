package com.tsystems.javaschool.model.entity;

import com.tsystems.javaschool.model.entity.enums.Role;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "med_stuff")
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class MedicalStaff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "is_deleted", columnDefinition = "boolean default false")
    private boolean is_deleted;

    @Column(name = "USERNAME", nullable = false)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @OneToMany(mappedBy = "doctor")
    private List<Treatment> treatments;

    @OneToMany(mappedBy = "nurse")
    private List<TherapyCase> therapyCases;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_user")
    private User user;
}
