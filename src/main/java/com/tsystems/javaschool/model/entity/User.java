package com.tsystems.javaschool.model.entity;

import com.tsystems.javaschool.model.entity.enums.Gender;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "surname", nullable = false)
    @Size(min = 1, max = 45)
    private String surname;

    @Column(name = "first_name", nullable = false)
    @Size(min = 1, max = 45)
    private String first_name;

    @Column(name = "middle_name")
    @Size(min = 1, max = 45)
    private String middle_name;

    @Column(name = "dbirth", nullable = false)
    private LocalDate dbirth;

    @Column(name = "sex", nullable = false)
    private Gender gender;

    @Column(name = "addr")
    @Size(min = 1, max = 45)
    private String address;

    @Column(name = "email")
    @Size(min = 1, max = 45)
    private String email;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Patient patient;

    @OneToOne
    @JoinColumn(name = "user_id")
    private MedicalStaff med_staff;
}
