package com.vervyle.guistudy.controllers;

import com.vervyle.guistudy.events.ValueChangedEvent;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import static java.lang.Math.ceil;
import static java.lang.Math.sqrt;

public class ButtonController implements Initializable {

    @FXML
    private RadioButton fourth_page_rb1;
    @FXML
    private RadioButton fourth_page_rb2;
    @FXML
    private RadioButton fourth_page_rb3;
    @FXML
    private TabPane main_tab_pane;
    @FXML
    private TextArea fourth_page_text_area;
    @FXML
    private Button second_page_button_back;
    @FXML
    private Button second_page_good_button;
    @FXML
    private ImageView second_page_good_image;
    @FXML
    private TextField first_page_text_field;
    @FXML
    private TextField sixth_page_text_field;
    @FXML
    private ToggleButton sixth_page_start_button;
    @FXML
    private Button sixth_page_cancel_button;
    @FXML
    private HBox seventh_page_new_file_creater;
    @FXML
    private ProgressIndicator sixth_page_progress_indicator;
    @FXML
    private ProgressBar sixth_page_progress_bar;
    @FXML
    private RadioMenuItem menu_auto_save;
    @FXML
    private TextField fifth_page_text_field_first;
    @FXML
    private TextField fifth_page_text_field_second;
    @FXML
    private RadioMenuItem menu_manual_save;
    @FXML
    AnchorPane seventh_page_split_pane_left_pane;
    @FXML
    SplitPane seventh_pane_split_pane;
    @FXML
    ColorPicker third_page_color_picker;
    @FXML
    GridPane third_page_grid;
    @FXML
    Spinner<Integer> third_page_spinner;

    @FXML
    void onButtonClicked(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        first_page_text_field.setText(button.getText());
    }

    private void firstPageInit() {
        System.out.println("FirstPage is initialized");
    }

    /**
     * @param path to your resource
     * @return file stream to resource
     */
    private FileInputStream makeFileInputStream(String path) {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return inputStream;
    }

    @FXML
    private void secondPageImageViewClear(ActionEvent actionEvent) {
        second_page_good_image.setImage(null);
    }

    @FXML
    private void secondPageImageViewSetImage(ActionEvent actionEvent) {
        onButtonClicked(actionEvent);
        Button button = (Button) actionEvent.getSource();
        String path = "C:\\Users\\wwwdo\\Desktop\\GUIstudy\\src\\main\\resources\\com\\vervyle\\guistudy\\images\\";
        path += (button.equals(second_page_good_button) ?
                "good_image.png" :
                "super_good_image.png");
        System.out.println(path);
        FileInputStream inputStream = makeFileInputStream(path);
        if (inputStream == null) {
            System.out.println("image is not found");
            return;
        }
        second_page_good_image.setImage(new Image(inputStream));
    }

    @FXML
    void SecondPageButtonBackgroundChange(ActionEvent actionEvent) {
        onButtonClicked(actionEvent);
        Random random = new Random();
        String rgbColor = "rgb(" + random.nextInt(256) + "," + random.nextInt(256) + "," + random.nextInt(256) + ");";
        second_page_button_back.setStyle("-fx-background-color:" + rgbColor);
    }

    private void secondPageInit() {
        System.out.println("SecondPage is initialized");
    }

    private void spinnerInit() {
        SpinnerValueFactory<Integer> spinnerValueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 256, 1);
        third_page_spinner.setValueFactory(spinnerValueFactory);
        third_page_spinner.setEditable(true);
    }

    private void thirdPageColorPickerInit() {
        third_page_color_picker.setOnAction(actionEvent -> {
            ObservableList<Node> list = third_page_grid.getChildren();
            Color color = third_page_color_picker.getValue();
            for (int i = 0; i < list.size(); i++) {
                AnchorPane anchorPane = (AnchorPane) list.get(i);
                String rgbColor = "rgb(" + color.getRed() * 256 + "," + color.getGreen() * 256 + "," + color.getBlue() * 256 + ");";
                int value = third_page_spinner.getValue();
                anchorPane.setStyle("-fx-background-color:" + rgbColor + ";-fx-background-insets:" + value + "px");
            }
        });
    }

    private void thirdPageInit() {
        spinnerInit();
        thirdPageColorPickerInit();
        System.out.println("ThirdPage is initialized");
    }

    /**
     * initializes radio buttons on fourthPage in app
     */
    private void fourthPageRadioButtonInit() {
        ToggleGroup fourthPageToggleGroup = new ToggleGroup();
        fourth_page_rb1.setToggleGroup(fourthPageToggleGroup);
        fourth_page_rb2.setToggleGroup(fourthPageToggleGroup);
        fourth_page_rb3.setToggleGroup(fourthPageToggleGroup);


        fourth_page_rb1.setOnAction(actionEvent -> {
            fourth_page_text_area.setText("Selected radio button: " + fourth_page_rb1.getText());
        });
        fourth_page_rb2.setOnAction(actionEvent -> {
            fourth_page_text_area.setText("Selected radio button: " + fourth_page_rb2.getText());
        });
        fourth_page_rb3.setOnAction(actionEvent -> {
            fourth_page_text_area.setText("Selected radio button: " + fourth_page_rb3.getText());
        });
    }

    private void fourthPageInit() {
        fourthPageRadioButtonInit();
        System.out.println("FourthPage is initialized");
    }

    /**
     * initializes MouseEvent.MOUSE_MOVED event handlers on fifthPage
     * first textField listens to movements only inside it
     * second textField listens to movements in all the scene
     */
    private void fifthPageEventHandlersInit() {
        String text = "Cursor is outside the text field!";
        fifth_page_text_field_first.setOnMouseMoved(event -> {
            String msg =
                    "(x: " + event.getX() + ", y: " + event.getY() + ") -- " +
                            "(sceneX: " + event.getSceneX() + ", sceneY: " + event.getSceneY() + ") -- " +
                            "(screenX: " + event.getScreenX() + ", screenY: " + event.getScreenY() + ")";

            fifth_page_text_field_second.setText(msg);
            fifth_page_text_field_first.setText(msg);
        });
        fifth_page_text_field_first.setOnMouseExited(mouseEvent -> {
            fifth_page_text_field_first.setText(text);
        });
        main_tab_pane.setOnMouseMoved(event -> {
            String msg =
                    "(x: " + event.getX() + ", y: " + event.getY() + ") -- " +
                            "(sceneX: " + event.getSceneX() + ", sceneY: " + event.getSceneY() + ") -- " +
                            "(screenX: " + event.getScreenX() + ", screenY: " + event.getScreenY() + ")";

            fifth_page_text_field_second.setText(msg);
        });
        fifth_page_text_field_second.setOnMouseMoved(event -> {
            String msg =
                    "(x: " + event.getX() + ", y: " + event.getY() + ") -- " +
                            "(sceneX: " + event.getSceneX() + ", sceneY: " + event.getSceneY() + ") -- " +
                            "(screenX: " + event.getScreenX() + ", screenY: " + event.getScreenY() + ")";

            fifth_page_text_field_second.setText(msg);
        });
    }

    private void fifthPageInit() {
        fifthPageEventHandlersInit();
        System.out.println("FifthPage is initialized");
    }

    private void sixthPageInit() {
        sixth_page_cancel_button.setDisable(true);
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
        System.out.println("SixthPage is initialized");
    }

    /**
     * inner class for updating a counter 0..1.0 every 0.5 seconds
     * starts in a new Thread
     */
    static class MyTask extends Task<Double> {
        @Override
        protected Double call() throws Exception {
            Double progress = (double) 0;
            while (progress < 1.0) {
                progress += 0.1;
                this.updateProgress(progress, 1.0);
                this.updateMessage(String.valueOf(progress));
                Thread.sleep(500);
            }
            return progress;
        }
    }

    /**
     *
     */
    private void seventhPageMenuRadioButtonInit() {
        ToggleGroup toggleGroup = new ToggleGroup();
        menu_auto_save.setToggleGroup(toggleGroup);
        menu_manual_save.setToggleGroup(toggleGroup);
    }

    @FXML
    public void seventhPageFileManagerOpen(ActionEvent actionEvent) {
        seventh_page_new_file_creater.setVisible(true);
    }

    @FXML
    public void seventhPageFileManagerClose(ActionEvent actionEvent) {
        seventh_page_new_file_creater.setVisible(false);
    }

    private void seventhPageInit() {
        seventhPageMenuRadioButtonInit();
        seventh_page_new_file_creater.setVisible(false);
        System.out.println("SeventhPage is initialized");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstPageInit();
        secondPageInit();
        thirdPageInit();
        fourthPageInit();
        fifthPageInit();
        sixthPageInit();
        seventhPageInit();
    }
}
