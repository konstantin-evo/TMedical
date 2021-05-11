package com.tsystems.javaschool.model.entity;

import com.tsystems.javaschool.model.entity.enums.Gender;
import com.tsystems.javaschool.model.entity.enums.Role;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "user")
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "surname", nullable = false)
    @Size(min = 1, max = 45)
    private String surname;

    @Column(name = "first_name", nullable = false)
    @Size(min = 1, max = 45)
    private String firstName;

    @Column(name = "middle_name")
    @Size(min = 1, max = 45)
    private String middleName;

    @Column(name = "dbirth", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dbirth;

    @Column(name = "sex", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "addr")
    @Size(min = 1, max = 45)
    private String address;

    @Column(name = "email", unique=true)
    @Size(min = 1, max = 45)
    private String email;

    @Column(name = "password")
    @Size(min = 1, max = 360)
    private String password;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "enabled", columnDefinition = "boolean default true")
    private boolean enabled;

    @OneToOne(mappedBy = "user")
    private Patient patient;

    @OneToMany(mappedBy = "doctor")
    private List<Treatment> treatments;

    @OneToMany(mappedBy = "nurse")
    private List<TherapyCase> therapyCases;

}
