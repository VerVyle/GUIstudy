module com.vervyle.guistudy {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.vervyle.guistudy to javafx.fxml;
    opens com.vervyle.guistudy.controllers to javafx.fxml;
    exports com.vervyle.guistudy;
    exports com.vervyle.guistudy.controllers;
}