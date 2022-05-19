module mastermicro.functionplotter {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires org.codehaus.groovy;
    requires javaluator;
    requires junit;

    opens mastermicro.functionplotter to javafx.fxml;
    exports mastermicro.functionplotter;
    exports test;
    opens test to javafx.fxml;
}