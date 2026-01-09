package com.engineer.pwdgen;

import javafx.scene.layout.Region;
import javafx.util.Builder;

public class PasswordController {
    private PasswordModel passwordModel;
    private Builder<Region> viewBuilder;
    private PasswordIterator passwordIterator;

    public PasswordController() {
        PasswordModel passwordModel = new PasswordModel();
        viewBuilder = new PasswordViewBuilder(passwordModel);
        passwordIterator = new PasswordIterator(passwordModel);
    }

    public Region getView() {
        return viewBuilder.build();
    }
}
