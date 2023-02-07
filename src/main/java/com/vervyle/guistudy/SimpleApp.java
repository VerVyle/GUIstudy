package com.vervyle.guistudy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class SimpleApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage = makeStage();
        stage.show();
    }

    private Stage makeStage() throws Exception {
        Stage stage = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        return stage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
