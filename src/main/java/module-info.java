module fr.theo {
    requires java.sql;
    requires javafx.fxml;
    requires javafx.media;
    requires transitive javafx.controls;

    opens fr.theo.control to javafx.fxml;
    opens fr.theo.data.table to javafx.base;
    opens fr.theo to javafx.fxml;

    exports fr.theo;
}