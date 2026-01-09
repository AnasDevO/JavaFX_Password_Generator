package com.engineer.pwdgen;

import javafx.beans.property.*;

import java.security.SecureRandom;

public class PasswordModel {
    private final BooleanProperty upperCaseAllowed = new SimpleBooleanProperty(true);
    private final BooleanProperty numberAllowed = new SimpleBooleanProperty(true);
    private final BooleanProperty specialAllowed = new SimpleBooleanProperty(true);
    private final StringProperty password = new SimpleStringProperty();
    private final IntegerProperty length = new SimpleIntegerProperty(30);

    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS   = "0123456789";
    private static final String SPECIAL   = "!@#$%^&*()-_=+[]{}";
    private int finalPoolLength;
    public DoubleProperty PasswordStrength =  new SimpleDoubleProperty(0);

    private void generatePassword() {
        StringBuilder poolString = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        password.set("");
        poolString.append(LOWERCASE);
        if(upperCaseAllowed.get()) {
            poolString.append(UPPERCASE);
        }
        if(numberAllowed.get()) {
            poolString.append(NUMBERS);
        }
        if(specialAllowed.get()) {
            poolString.append(SPECIAL);
        }
        char[] finalPool = poolString.toString().toCharArray();
        finalPoolLength = poolString.length();
        StringBuilder finalPassword = new StringBuilder();
        for(int i = 0; i < length.get(); i++) {
            finalPassword.append(finalPool[secureRandom.nextInt(finalPoolLength)]);
        }
        password.set(finalPassword.toString());
        CalcStrength();
    }

    private void CalcStrength() {
        double complexity = (double) (Math.log(Math.pow(finalPoolLength, length.get()))/Math.log(2));
        double finalProprety = (double) (Math.pow(2,complexity)/(164*Math.pow(10,9)*3600));
        PasswordStrength.set(finalProprety);
    }

    public StringProperty passwordProperty() {
        generatePassword();
        return password;
    }
    public void setPasswordLength(int passLength) {
        length.set(passLength);
    }
    public returnData getPassLength() {
        IntegerProperty integerValue = new SimpleIntegerProperty(length.get());
        Integer CleanValue = integerValue.get();
        StringProperty stringProperty = new SimpleStringProperty(CleanValue.toString());
        return new returnData(integerValue, stringProperty);
    }


    public void setUpperCaseAllowed(Boolean value) {
        upperCaseAllowed.set(value);
    }
    public BooleanProperty getUpperCaseAllowed() {
        return new SimpleBooleanProperty(upperCaseAllowed.get());
    }
    public void setnumberAllowed(Boolean value) {
        numberAllowed.set(value);
    }
    public BooleanProperty getnumberAllowed() {
        return new SimpleBooleanProperty(numberAllowed.get());
    }
    public void setspecialAllowed(Boolean value) {
        specialAllowed.set(value);
    }
    public BooleanProperty getspecialAllowed() {
        return new SimpleBooleanProperty(specialAllowed.get());
    }


}
