package controller;


/**
 * Created by acer on 26.09.2016.
 */
public enum UserType {
    PUPIL ("Ученик"),
    TEACHER ("Учитель"),
    PARENT ("Родитель");

    private String value;

    UserType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
