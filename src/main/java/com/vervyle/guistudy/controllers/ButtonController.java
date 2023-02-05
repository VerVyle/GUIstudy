package com.vervyle.guistudy.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.InputStream;

public class ButtonController {
    @FXML
    Button second_page_button;
    @FXML
    Button third_page_button;
    @FXML
    Button second_page_super_good_button;
    @FXML
    Button second_page_good_button;
    @FXML
    ImageView second_page_good_image;
    @FXML
    TextField first_page_text_field;

    @FXML
    void onClicked(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        first_page_text_field.setText(button.getText());
    }

    @FXML
    void onGoodThingDone(ActionEvent actionEvent) {
        onClicked(actionEvent);
        InputStream inputStream;
        Button button = (Button) actionEvent.getSource();
        if (button.getText().equals("Good Button")) {
            try {
                inputStream = new FileInputStream("resources/images/good_image.png");
                second_page_good_image.setImage(new Image(inputStream));
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
            return;
        }
        try {
            inputStream = new FileInputStream("resources/images/good_image.png");
            second_page_good_image.setImage(new Image(inputStream));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

}
