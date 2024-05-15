package com.home.SpringBootAutomation.enums;

public enum TypeOfEmployment {
    fullTime("fullTime"),
    partTime("partTime"),
    project("project");
    private final String label;

    TypeOfEmployment(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
