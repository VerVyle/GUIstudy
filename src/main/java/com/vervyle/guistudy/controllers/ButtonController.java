package com.vervyle.guistudy.controllers;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class ButtonController implements Initializable {

    String oldStyle;
    boolean isChanged;
    @FXML
    Button second_page_button;
    @FXML
    Button third_page_button;
    @FXML
    Button second_page_super_good_button;
    @FXML
    TextField fifth_tab_text_field;
    @FXML
    Button second_page_button_back;
    @FXML
    Button second_page_good_button;
    @FXML
    ImageView second_page_good_image;
    @FXML
    TextField first_page_text_field;

    @FXML
    TextField sixth_page_text_field;
    @FXML
    private Tab sixth_page;
    @FXML
    private ToggleButton sixth_page_start_button;
    @FXML
    private ColorPicker sixth_page_color_picker;

    @FXML
    private HBox sixth_page_hbox;

    @FXML
    private AnchorPane sixth_page_pane;

    @FXML
    private Spinner<?> sixth_page_spinner;

    @FXML
    private ToolBar sixth_page_toolbar;
    @FXML
    private Button sixth_page_cancel_button;
    @FXML
    private VBox sixth_page_vbox;
    @FXML
    private ProgressIndicator sixth_page_progress_indicator;
    @FXML
    private ProgressBar sixth_page_progress_bar;

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
                inputStream = new FileInputStream("C:\\Users\\timur\\IdeaProjects\\GUIstudy\\src\\main\\java\\com\\vervyle\\guistudy\\controllers\\good_image.png");
                second_page_good_image.setImage(new Image(inputStream));
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
            return;
        }
        try {
            inputStream = new FileInputStream("C:\\Users\\timur\\IdeaProjects\\GUIstudy\\src\\main\\java\\com\\vervyle\\guistudy\\controllers\\super_good_image.png");
            second_page_good_image.setImage(new Image(inputStream));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void onClear(ActionEvent actionEvent) {
        onClicked(actionEvent);
        second_page_good_image.setImage(null);
    }

    @FXML
    void backgroundChange(ActionEvent actionEvent) {
        onClicked(actionEvent);
        Button button = (Button) actionEvent.getSource();
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                String bstyle = String.format("-fx-text-fill: %s;-fx-fill: %s;-fx-background-color: %s;", "#FFFFFF", "#FFFFFF", "#FFFFFF");
                if (!isChanged) {
                    isChanged = true;
                    oldStyle = button.getStyle();
                    button.setStyle(bstyle);
                    return;
                }
                isChanged = false;
                button.setStyle(oldStyle);
            }
        };
        timer.scheduleAtFixedRate(task, 0, 2000);
    }

    class MyTask extends Task<Double> {
        @Override
        protected Double call() throws Exception {
            Double progress = Double.valueOf(0);
            while (progress < 1.0) {
                progress += 0.1;
                this.updateProgress(progress, 1.0);
                this.updateMessage(String.valueOf(progress));
                Thread.sleep(500);
            }
            return progress;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sixth_page_cancel_button.setDisable(true);
        final Label label = new Label();
        label.setMinWidth(250);
        label.setTextFill(Color.BLUE);
        sixth_page_start_button.setOnAction(actionEvent -> {
            sixth_page_start_button.setDisable(true);
            sixth_page_cancel_button.setDisable(false);
            sixth_page_progress_bar.setProgress(0);
            sixth_page_progress_indicator.setProgress(0);
            MyTask myTask = new MyTask();
            sixth_page_progress_bar.progressProperty().unbind();
            sixth_page_progress_bar.progressProperty().bind(myTask.progressProperty());
            sixth_page_progress_indicator.progressProperty().unbind();
            sixth_page_progress_indicator.progressProperty().bind(myTask.progressProperty());
            sixth_page_text_field.textProperty().unbind();
            sixth_page_text_field.textProperty().bind(myTask.messageProperty());
            myTask.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, workerStateEvent -> {
                Double value = myTask.getValue();
                sixth_page_text_field.textProperty().unbind();
                sixth_page_progress_bar.progressProperty().unbind();
                sixth_page_progress_indicator.progressProperty().unbind();
                sixth_page_text_field.setText("Finally Value: " + value);
                sixth_page_start_button.setDisable(false);
                sixth_page_cancel_button.setDisable(true);
            });
            sixth_page_cancel_button.setOnAction(actionEvent1 -> {
                sixth_page_start_button.setDisable(false);
                sixth_page_cancel_button.setDisable(true);
                myTask.cancel(true);
                sixth_page_progress_bar.progressProperty().unbind();
                sixth_page_progress_indicator.progressProperty().unbind();
                sixth_page_text_field.textProperty().unbind();
                sixth_page_text_field.setText("Progress interrupted!");
                sixth_page_progress_bar.setProgress(0);
                sixth_page_progress_indicator.setProgress(0);
            });
            new Thread(myTask).start();

        });
        String text = "Cursor is outside the text field!";
        isChanged = false;
        fifth_tab_text_field.setText(text);
        fifth_tab_text_field.setOnMouseMoved(event -> {
            String msg =
                    "(x: " + event.getX() + ", y: " + event.getY() + ") -- " +
                            "(sceneX: " + event.getSceneX() + ", sceneY: " + event.getSceneY() + ") -- " +
                            "(screenX: " + event.getScreenX() + ", screenY: " + event.getScreenY() + ")";

            fifth_tab_text_field.setText(msg);
        });
        fifth_tab_text_field.setOnMouseExited(mouseEvent -> {
            fifth_tab_text_field.setText(text);
        });
    }
}
