package com.engineer.pwdgen;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class returnData {
    public IntegerProperty returnIntProprety;
    public StringProperty returnStrProprety;
    public returnData(IntegerProperty returnIntProprety, StringProperty returnStrProprety) {
        this.returnIntProprety = returnIntProprety;
        this.returnStrProprety = returnStrProprety;
    }
}
