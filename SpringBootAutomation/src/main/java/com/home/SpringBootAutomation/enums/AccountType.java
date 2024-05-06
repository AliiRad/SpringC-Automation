package com.home.SpringBootAutomation.enums;

public enum AccountType {
    currentAccount("currentAccount"),         //حساب جاری
    savingsAccount("savingsAccount");          //حساب پس انداز
    private final String label;

    AccountType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}