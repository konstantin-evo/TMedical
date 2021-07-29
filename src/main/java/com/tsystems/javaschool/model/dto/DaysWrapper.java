package com.tsystems.javaschool.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class DaysWrapper implements Serializable {
    private  List<TherapyDaysDto> days;

    public void addDay(TherapyDaysDto day) {
        this.days.add(day);
    }

    public List<TherapyDaysDto> getDays() {
        return days;
    }

    public void setDays(List<TherapyDaysDto> days) {
        this.days = days;
    }
}
