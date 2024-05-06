package com.home.SpringBootAutomation.enums;

public enum AccountStatus {
    active("active"),           //فعال
    inactive("inactive");       //غیر فعال

    private final String label;

    AccountStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
