module com.sc {
    requires transitive javafx.controls;
    requires javafx.fxml;

    opens com.sc to javafx.fxml;
    exports com.sc;
}
