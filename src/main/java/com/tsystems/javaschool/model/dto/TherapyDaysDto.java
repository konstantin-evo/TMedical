package com.tsystems.javaschool.model.dto;
import com.tsystems.javaschool.model.entity.enums.Week;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class TherapyDaysDto implements Serializable {
    Week day;
    String time;

    public TherapyDaysDto(String day, String time) {
        this.time = time;
        this.day = Week.valueOf(day);
    }
}
