package com.tsystems.javaschool.model.entity;

import com.tsystems.javaschool.model.entity.enums.Role;
import javax.persistence.*;

@Entity
@Table(name = "med_stuff")
public class MedicalStaff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "is_deleted", columnDefinition = "boolean default false")
    private boolean is_deleted;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;
}
