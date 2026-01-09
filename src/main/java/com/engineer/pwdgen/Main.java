package com.engineer.pwdgen;

import io.github.palexdev.materialfx.theming.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import io.github.palexdev.materialfx.theming.UserAgentBuilder;
import io.github.palexdev.materialfx.theming.JavaFXThemes;

import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        UserAgentBuilder.builder()
                .themes(JavaFXThemes.MODENA) // Optional if you don't need JavaFX's default theme, still recommended though
                .themes(MaterialFXStylesheets.forAssemble(true)) // Adds the MaterialFX's default theme. The boolean argument is to include legacy controls
                .setDeploy(true) // Whether to deploy each theme's assets on a temporary dir on the disk
                .setResolveAssets(true) // Whether to try resolving @import statements and resources urls
                .build() // Assembles all the added themes into a single CSSFragment (very powerful class check its documentation)
                .setGlobal(); // Finally, sets the produced stylesheet as the global User-Agent stylesheet
        Scene MainStage = new Scene(new PasswordController().getView(), 800, 600);
        primaryStage.setTitle("Engineer's Password Generator");
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("icon.png"))));
        primaryStage.setScene(MainStage);
        primaryStage.show();

    }
    public Region CreateContent(){
        HBox root = new HBox();
        root.setSpacing(10);
        return root;
    }

}
