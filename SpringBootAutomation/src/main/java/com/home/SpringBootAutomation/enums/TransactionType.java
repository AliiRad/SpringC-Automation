package com.home.SpringBootAutomation.enums;

public enum TransactionType {
    card("card"),         //کارت
    cash("cash"),         //نقد
    check("check"),      //چک
    credit("credit");    //اعتبار
    private final String label;

    TransactionType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
