package com.tsystems.javaschool.model.dto;

import com.tsystems.javaschool.model.entity.enums.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserDto implements Serializable {
    private int id;
    private String surname;
    private String firstName;
    private String middleName;
    private String fullName;
    private String dbirth;
    private Gender gender;
    private String address;
    private String email;
}