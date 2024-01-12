package lk.ijse.plantLife;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher extends Application {
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) throws IOException {
        /*stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/Login_form.fxml"))));
        stage.setTitle("Login");
        stage.centerOnScreen();
        stage.show();*/
        Parent parent = FXMLLoader.load(this.getClass().getResource("/view/Login_form.fxml"));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

    }
}
