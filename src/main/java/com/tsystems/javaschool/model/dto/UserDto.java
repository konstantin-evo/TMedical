package com.tsystems.javaschool.model.dto;

import com.tsystems.javaschool.model.entity.enums.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UserDto implements Serializable {
    private int id;
    private String surname;
    private String first_name;
    private String middle_name;
    private String dbirth;
    private Gender gender;
    private String address;
    private String email;
}