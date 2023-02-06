package com.vervyle.guistudy;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SimpleApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage = makeStage();
        Scene scene = stage.getScene();
        stage.show();
    }

    private Stage makeStage() throws Exception {
        Stage stage = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Scene scene = stage.getScene();
        TabPane anchorPane = (TabPane) stage.getScene().getRoot().getChildrenUnmodifiable().get(0);
        Tab tab = (Tab) anchorPane.getTabs().get(4);
        Tab radioButtonTab = (Tab) anchorPane.getTabs().get(3);
        AnchorPane anchorPaneRadio = (AnchorPane) radioButtonTab.getContent();
        VBox vBoxRadio = (VBox) anchorPaneRadio.getChildrenUnmodifiable().get(0);
        RadioButton rb1 = (RadioButton) vBoxRadio.getChildrenUnmodifiable().get(0);
        RadioButton rb2 = (RadioButton) vBoxRadio.getChildrenUnmodifiable().get(1);
        RadioButton rb3 = (RadioButton) vBoxRadio.getChildrenUnmodifiable().get(2);
        TextArea textAreaRadio = (TextArea) vBoxRadio.getChildrenUnmodifiable().get(3);
        rb1.setOnAction(actionEvent -> {
            textAreaRadio.setText("Selected button: " + rb1.getText());
        });
        rb2.setOnAction(actionEvent -> {
            textAreaRadio.setText("Selected button: " + rb2.getText());
        });
        rb3.setOnAction(actionEvent -> {
            textAreaRadio.setText("Selected button: " + rb3.getText());
        });
        ToggleGroup toggleGroup = new ToggleGroup();
        rb1.setToggleGroup(toggleGroup);
        rb2.setToggleGroup(toggleGroup);
        rb3.setToggleGroup(toggleGroup);
        AnchorPane anchorPane1 = (AnchorPane) tab.getContent();
        VBox vBox = (VBox) anchorPane1.getChildrenUnmodifiable().get(0);
        TextField textField = (TextField) vBox.getChildrenUnmodifiable().get(1);
        anchorPane.setOnMouseMoved(event -> {
            String msg =
                    "(x: " + event.getX() + ", y: " + event.getY() + ") -- " +
                            "(sceneX: " + event.getSceneX() + ", sceneY: " + event.getSceneY() + ") -- " +
                            "(screenX: " + event.getScreenX() + ", screenY: " + event.getScreenY() + ")";

            textField.setText(msg);
        });
        textField.setOnMouseMoved(event -> {
            String msg =
                    "(x: " + event.getX() + ", y: " + event.getY() + ") -- " +
                            "(sceneX: " + event.getSceneX() + ", sceneY: " + event.getSceneY() + ") -- " +
                            "(screenX: " + event.getScreenX() + ", screenY: " + event.getScreenY() + ")";

            textField.setText(msg);
        });

        return stage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
