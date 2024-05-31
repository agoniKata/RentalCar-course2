module com.example.projectcourse2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.projectcourse2 to javafx.fxml;
    exports com.example.projectcourse2;
}