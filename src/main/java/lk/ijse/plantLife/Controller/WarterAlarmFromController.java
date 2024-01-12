package lk.ijse.plantLife.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.plantLife.utill.AlarmPlayer;

import java.io.IOException;

public class WarterAlarmFromController {
    public AnchorPane root;
    public TextField txtTime;

    public void btnbackOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/DashBoard.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dash Board");
        stage.centerOnScreen();
    }

    public void btnOkOnAction(ActionEvent actionEvent) {
        new Thread(){
            public void run(){
                new AlarmPlayer().playAlarmAtTime(txtTime.getText());
            }
        }.start();
    }
}
