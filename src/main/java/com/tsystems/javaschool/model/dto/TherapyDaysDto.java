package com.tsystems.javaschool.model.dto;
import com.tsystems.javaschool.model.entity.enums.Week;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TherapyDaysDto {
    Week day;
    String time;

    public TherapyDaysDto(String day, String time) {
        this.time = time;
        this.day = Week.valueOf(day);
    }
}
