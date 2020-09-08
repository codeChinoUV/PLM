module org.chinosoft {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.sql;
    requires mysql.connector.java;
    requires java.desktop;

    opens org.chinosoft to javafx.fxml, javafx.controls;
    opens org.chinosoft.controlador to javafx.fxml;
    opens org.chinosoft.modelo to javafx.base;
    exports org.chinosoft;
    exports org.chinosoft.controlador;
}