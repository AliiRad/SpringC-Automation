package com.home.SpringBootAutomation.enums;

public enum AppointmentType {
    recruitment("recruitment") ,    //استخدام
    changeDirection("changeDirection"), //تغیر سمت
    changeOfSalary("changeOfSalary");  //تغیر حقوق
    private final String label;

    AppointmentType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
