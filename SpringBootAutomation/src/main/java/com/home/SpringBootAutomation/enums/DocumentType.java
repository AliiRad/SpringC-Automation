package com.home.SpringBootAutomation.enums;

public enum DocumentType {
    receive("receive"),   //دریافت
    payment("payment");    //پرداخت
    private final String label;

    DocumentType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}