package lk.ijse.plantLife.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;

public class LoginFormController {
    public Button btnLogin;
    @FXML
    private AnchorPane AnchorPane1;

    @FXML
    private AnchorPane AnchorPane2;

    @FXML
    private Label login;

    @FXML
    private Button loginbu;

    @FXML
    private Button pass;

    @FXML
    private TextField password;

    @FXML
    private Label system;

    @FXML
    private TextField userName;

    @FXML
    private Label welcome;

    private String userame = "Yashis";
    private String passWord = "Yashis0102";



    public String getUserame() {
        if (userName.equals(userame)) {
            return userame;
        } else {
            return null;
        }
    }
    public void setUserame(String userame) {
        this.userame = userame;
    }

    public String getPassWord() {
        return passWord;
    }
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        String password = userame;
        Parent parent=FXMLLoader.load(getClass().getResource("/view/DashBoard.fxml"));
        Scene scene=new Scene(parent);
        Stage stage= (Stage) AnchorPane1.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("DashBoard");
        stage.centerOnScreen();
    }
}
