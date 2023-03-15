package com.example.pro.enumPackage;
public enum Gender {
    MALE("1", "3"),
    FEMALE("2", "4");

    private final String code; // db에 저장될 값
    private final String anotherCode; // db에 저장될 값

    Gender(String code, String anotherCode) {
        this.code = code;
        this.anotherCode = anotherCode;
    }

    public String getCode(boolean isMale) {
        return isMale ? code : anotherCode;
    }
}
