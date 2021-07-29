package com.tsystems.javaschool.model.dto;

import java.io.Serializable;
import java.util.List;

public class CaseWrapper implements Serializable {
    private List<TherapyCaseDto> cases;

    public List<TherapyCaseDto> getCases() {
        return cases;
    }

    public void setCases(List<TherapyCaseDto> cases) {
        this.cases = cases;
    }
}
