package com.example.restauouma;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class SignUpController implements Initializable {

    @FXML
    private Button button_signup;

    @FXML
    private Button button_log_in;

    @FXML
    private TextField tf_firstname;

    @FXML
    private TextField tf_lastname;

    @FXML
    private TextField tf_email;

    String emailText = null;

    @FXML
    private TextField tf_username1;

    @FXML
    private TextField tf_password1;

    @FXML
    private TextField tf_confirmpassword;

    @FXML
    private ChoiceBox<String> mychoicebox;

    private String[] jobs = {"Admin", "Client"};
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {




        mychoicebox.getItems().addAll(jobs);
        button_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String choiceName = mychoicebox.getValue();
                if (!tf_username1.getText().trim().isEmpty() && !tf_password1.getText().trim().isEmpty() &&
                tf_firstname.getText().trim().isEmpty() && tf_lastname.getText().trim().isEmpty() &&
                tf_email.getText().trim().isEmpty() && tf_confirmpassword.getText().trim().isEmpty() &&
                mychoicebox.getValue().trim().isEmpty()){
                    DBUtils.signUpUser(event, tf_firstname.getText(), tf_lastname.getText(), tf_email.getText(),
                            tf_username1.getText(), tf_password1.getText(), tf_confirmpassword.getText(), choiceName);

                }else {
                    System.out.println("Please fill in all information");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all informations to sign up!");
                    alert.show();
                }

            }
        });
        button_log_in.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "sample.fxml", "Log in!", null);
            }
        });

    }


    @FXML
    private void checkField(KeyEvent event){
        emailText = tf_email.getText();
        if(is_Valid(emailText)){
            tf_email.setStyle("-fx-border-color:none");
        }else {
            tf_email.setStyle("-fx-border-color:red");
        }
    }

    private boolean is_Valid(String emailText) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@"+
                             "(?:[a-zA-Z0-9-]+\\.)+[a-z"+
                             "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (tf_email == null){
            return false;
        }
        return pat.matcher(emailText).matches();
    }
}
