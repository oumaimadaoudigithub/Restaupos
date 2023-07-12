package com.example.restauouma;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    String passwordText = null;

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
                if (validateFields()) {
                    String choiceName = mychoicebox.getValue();

                    DBUtils.signUpUser(event, tf_firstname.getText(), tf_lastname.getText(), tf_email.getText(),
                            tf_username1.getText(), tf_password1.getText(), tf_confirmpassword.getText(), choiceName);
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
    private void checkField(Event event) {
        if (event.getEventType() == KeyEvent.KEY_PRESSED && ((KeyEvent) event).getCode() == KeyCode.TAB) {
            emailText = tf_email.getText();
            if (validateEmail(emailText)) {
                tf_email.setStyle("-fx-border-color:none");
            } else {
                tf_email.setStyle("-fx-border-color:red");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Invalid email");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void checkPassword(Event event) {
        if (event.getEventType() == KeyEvent.KEY_PRESSED && ((KeyEvent) event).getCode() == KeyCode.TAB) {
            passwordText = tf_password1.getText();
            if (validatePassword(passwordText)) {
                tf_password1.setStyle("-fx-border-color:none");
            } else {
                tf_password1.setStyle("-fx-border-color:red");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Invalid password");
                alert.showAndWait();
            }
        }
    }

    /*@FXML
    private void checkPassword(KeyEvent event){
        passwordText = tf_password1.getText();
        if(validatePassword(passwordText)){
            tf_password1.setStyle("-fx-border-color:none");
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Invalid Password");
            alert.showAndWait();
            tf_password1.setStyle("-fx-border-color:red");
        }
    }*/


    private boolean validateEmail(String emailText) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ "[a-zA-Z0-9_+&*-]+)*@"+ "(?:[a-zA-Z0-9-]+\\.)+[a-z"+ "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (tf_email == null){
            return false;
        }
        return pat.matcher(emailText).matches();
    }
    private boolean validatePassword(String passwordText) {
        Pattern p = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})");
        if (tf_password1 == null){
            return false;
        }
        return p.matcher(passwordText).matches();
    }
    private boolean validateFields() {
        String firstName = tf_firstname.getText().trim();
        String lastName = tf_lastname.getText().trim();
        String email = tf_email.getText().trim();
        String username = tf_username1.getText().trim();
        String password = tf_password1.getText().trim();
        String confirmPassword = tf_confirmpassword.getText().trim();
        String choiceName = mychoicebox.getValue();

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || username.isEmpty() ||
                password.isEmpty() || confirmPassword.isEmpty() || choiceName == null || choiceName.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill in all fields to sign up !");
            alert.showAndWait();
            return false;
        }

        if (!validateEmail(email)) {
            tf_email.setStyle("-fx-border-color:red");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Invalid email");
            alert.showAndWait();
            return false;
        }

        if (!validatePassword(password)) {
            tf_password1.setStyle("-fx-border-color:red");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Invalid password");
            alert.showAndWait();
            return false;
        }

        if (!password.equals(confirmPassword)) {
            tf_password1.setStyle("-fx-border-color:red");
            tf_confirmpassword.setStyle("-fx-border-color:red");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Passwords did not match");
            alert.showAndWait();
            return false;
        }
        // All fields are valid
        return true;
    }
}
