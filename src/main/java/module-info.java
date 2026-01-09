module com.engineer.pwdgen {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires MaterialFX;

    opens com.engineer.pwdgen to javafx.fxml;
    exports com.engineer.pwdgen;
}