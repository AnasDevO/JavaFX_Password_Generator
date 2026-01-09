package com.engineer.pwdgen;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXSlider;
import io.github.palexdev.materialfx.controls.MFXToggleButton;
import io.github.palexdev.materialfx.effects.DepthLevel;
import io.github.palexdev.materialfx.enums.SliderEnums;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;


import java.util.Objects;

public class PasswordViewBuilder implements Builder<Region> {
    private final PasswordModel passwordModel;
    private final StringProperty resultPassword = new SimpleStringProperty("");
    private final BooleanProperty upperCaseAllowed = new SimpleBooleanProperty(true);
    private final BooleanProperty numberAllowed = new SimpleBooleanProperty(true);
    private final BooleanProperty specialAllowed = new SimpleBooleanProperty(false);
    private static int vboxIndex = 0;
    private String passComplexity = "";

    public PasswordViewBuilder(PasswordModel passwordModel) {
        this.passwordModel = passwordModel;
    }

    @Override
    public Region build() {
        VBox HeaderScene = new VBox();
        HeaderScene.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("css/pgen.css")).toExternalForm());
        Image logo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("icon.png")));
        ImageView imageView = new ImageView(logo);
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);
        HeaderScene.getChildren().add(imageView);
        headify(HeaderScene, "Password Generator");
        headify(HeaderScene, "Create strong and secure passwords to keep your accounts online safe");
        HeaderScene.getChildren().add(PwRegion());
        HeaderScene.getChildren().add(PwLength(HeaderScene));
        VBox settingPane = new VBox();
        settingPane.setSpacing(13);
        settingPane.setAlignment(Pos.CENTER);
        settingPane.getChildren().add(PwSettings("Allow Uppercase", true, "check", upperCaseAllowed, HeaderScene));
        settingPane.getChildren().add(PwSettings("Allow Numbers", true, "check", numberAllowed, HeaderScene));
        settingPane.getChildren().add(PwSettings("Allow Special Characters", false,  "check", specialAllowed, HeaderScene));
        HeaderScene.getChildren().add(settingPane);
        VBox passComplexity = new VBox();
        passComplexity.setSpacing(13);
        passComplexity.setAlignment(Pos.CENTER);
        return HeaderScene;
    }

    private VBox PwLength(VBox scene) {
        VBox PwLength = new VBox();
        PwLength.setSpacing(15);
        PwLength.setAlignment(Pos.CENTER);
        PwLength.maxWidthProperty().bind(scene.widthProperty().multiply(0.75));

        MFXSlider slider = new MFXSlider();
        slider.setMin(10);
        slider.setMax(60);
        slider.setValue(30);

        slider.setDecimalPrecision(0);
        slider.setTickUnit(10);
        slider.setPopupSide(SliderEnums.SliderPopupSide.DEFAULT);
        slider.setPrefWidth(400);

        slider.valueProperty().addListener((obs, oldVal, newVal) -> {
            passwordModel.setPasswordLength(newVal.intValue());
        });

        Label lengthValue = new Label();
        lengthValue.getStyleClass().add("passLength");
        lengthValue.textProperty().bind(slider.valueProperty().asString("%.0f"));

        Label passLabel = new Label("Password Length: ");
        passLabel.getStyleClass().add("passLabel");


        Label strengthValue = new Label();
        strengthValue.getStyleClass().add("passLength");
        strengthValue.textProperty().bind(passwordModel.PasswordStrength.asString("%,.0f"));

        Label strengthLabel = new Label("The complexity of your password is ");
        strengthLabel.getStyleClass().add("passLabel");

        Label strengthLabel2 = new Label("Hours to crack (With an RTX 4090)");
        strengthLabel2.getStyleClass().add("passLabel");

        HBox lengthDisplay = new HBox(10, passLabel, lengthValue);
        VBox strengthDisplay = new VBox(10, strengthLabel, strengthValue, strengthLabel2);
        strengthDisplay.setAlignment(Pos.CENTER);
        lengthDisplay.setAlignment(Pos.CENTER);

        PwLength.getChildren().addAll(lengthDisplay, slider, strengthDisplay);


        return PwLength;
    }

    private HBox PwSettings(String label, boolean allow, String styleClass, BooleanProperty check, VBox parentContainer) {
        HBox PassBoxScene = new HBox();
        PassBoxScene.setAlignment(Pos.CENTER_LEFT);
        PassBoxScene.setSpacing(30);
        PassBoxScene.maxWidthProperty().bind(parentContainer.widthProperty().multiply(0.75));
        PassBoxScene.setPrefWidth(Region.USE_COMPUTED_SIZE);

        MFXToggleButton checkBox = new MFXToggleButton();
        checkBox.getStyleClass().add(styleClass);
        checkBox.setSelected(allow);
        CheckboxLabel(label, PassBoxScene);
        checkBox.selectedProperty().bindBidirectional(check);
        PassBoxScene.setAlignment(Pos.CENTER);
        PassBoxScene.getChildren().add(checkBox);
        return PassBoxScene;

    }

    private void CheckboxLabel(String label, HBox Scene) {
        Label checkboxLabel = new Label(label);
        checkboxLabel.setMinWidth(180);
        checkboxLabel.setPrefWidth(180);
        checkboxLabel.setMaxWidth(180);
        String styleClass = "heading-label-" + vboxIndex;
        vboxIndex += 1;
        checkboxLabel.getStyleClass().add(styleClass);
        Scene.setAlignment(Pos.CENTER_LEFT);
        Scene.getChildren().add(checkboxLabel);
    }

    private HBox PwRegion() {
        HBox PassW = new HBox();
        PassGenField(PassW);
        PassW.setPadding(new Insets(10));
        PassW.setAlignment(Pos.CENTER);
        PassW.setSpacing(10);
        PassGenerateButton(PassW);
        return PassW;
    }

    private void PassGenerateButton(HBox passW) {
        MFXButton PassGen = new MFXButton("Generate");
        PassGen.setDepthLevel(DepthLevel.LEVEL4);
        PassGen.getStyleClass().add("gen-button");
        PassGen.setOnAction(e -> {setPassword();});
        passW.getChildren().add(PassGen);
    }

    private Node PassGenField(HBox PassW) {
        TextField PassTextField = new TextField();
        PassTextField.textProperty().bindBidirectional(resultPassword);
        PassTextField.setEditable(false);
        PassTextField.setPromptText("Generate a password...");
        PassTextField.setFocusTraversable(false);
        PassW.getChildren().add(PassTextField);
        return PassW;
    }


    private void headify(VBox Scene, String text) {
        Scene.getChildren().add(headingLabel(text));
        Scene.setPadding(new Insets(10, 10, 0, 0));
        Scene.setAlignment(Pos.CENTER);
    }

    private Node headingLabel(String text) {
        return styledLabel(text, "heading-label-", false);
    }
    private Node normalisedHeadingLabel(String text) {
        return styledLabel(text, "heading-label-", false);
    }
    private Node styledLabel(String text, String styleClass, Boolean isOption) {
        Label label = new Label(text);
        styleClass = styleClass + vboxIndex;
        System.out.println(styleClass);
        vboxIndex += 1;
        System.out.println(vboxIndex);
        label.getStyleClass().add(styleClass);

        return label;
    }
    private void setPassword() {
        passwordModel.setnumberAllowed(numberAllowed.get());
        passwordModel.setUpperCaseAllowed(upperCaseAllowed.get());
        passwordModel.setspecialAllowed(specialAllowed.get());
        System.out.println(passwordModel.getspecialAllowed());
        System.out.println(passwordModel.getnumberAllowed());
        System.out.println(passwordModel.getUpperCaseAllowed());
        resultPassword.set(passwordModel.passwordProperty().get());
        System.out.println("The complexity of your password is "+passwordModel.PasswordStrength+" Hours to crack");

    }
}
