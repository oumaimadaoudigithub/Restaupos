package com.example.restauouma;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class DBUtils {


    public DBUtils() {}

    public void connect() {
        try {
            // Establish the database connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/reataupos", "root", "");

            // If the connection is successful, print a success message
            System.out.println("Database connection established successfully!");

            // Perform your database operations here...

            // Close the connection
            connection.close();
        } catch (SQLException e) {
            // If an exception occurs, print an error message
            System.out.println("Failed to establish the database connection.");
            e.printStackTrace();
        }
    }

    public static void changeScene(ActionEvent event, String fxmlFile, String title){
        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
            root = loader.load();
        } catch (IOException e){
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 1100, 600));
        stage.show();
    }

    public static void signUpUser(ActionEvent event, String firstname, String lastname, String email, String username, String password, String confpassword, String job){
         Connection connection = null;
         PreparedStatement psInsert = null;
         PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;

        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/reataupos", "root", "");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM llx_user WHERE username = ?");
            psCheckUserExists.setString(1, username);
            resultSet = psCheckUserExists.executeQuery();

            if (resultSet.isBeforeFirst()){
                System.out.println("User already exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this username.");
                alert.show();
            }else{
                psInsert = connection.prepareStatement("INSERT INTO llx_user (firstname,lastname,email,username,password,confpassword,job) VALUES (?, ?, ?, ?, ?, ?, ?)");
                psInsert.setString(1, firstname);
                psInsert.setString(2, lastname);
                psInsert.setString(3, email);
                psInsert.setString(4, username);
                psInsert.setString(5, password);
                psInsert.setString(6, confpassword);
                psInsert.setString(7, job);
                psInsert.executeUpdate();

                changeScene(event, "mainPage.fxml", "welcome");


            }
        }catch (SQLException e){
             e.printStackTrace();
        } finally {
            if (resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (psCheckUserExists != null){
                try{
                    psCheckUserExists.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (psInsert != null){
                try{
                    psInsert.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try{
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void logInUser(ActionEvent event, String username, String password){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/reataupos", "root", "");
            preparedStatement = connection.prepareStatement("SELECT password FROM llx_user WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if(!resultSet.isBeforeFirst()){
                System.out.println("user not found in the database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect");
                alert.show();
            }else {
                while(resultSet.next()){
                    data.display_username = username;
                    String retrievedHashedPassword = resultSet.getString("password");
                    String enteredHashedPassword = hashPassword(password);
                    if (retrievedHashedPassword.equals(enteredHashedPassword)) {
                        changeScene(event, "mainPage.fxml", "welcome");
                    }else {
                        System.out.println("passwords did not match!");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("The provided credentials are incorrect!");
                        alert.show();
                    }
                }

            }
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(resultSet != null){
                try{
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null){
                try{
                    preparedStatement.close();
                } catch(SQLException e)  {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static String hashPassword(String password) {
        try {
            // Create MD5 MessageDigest instance
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Convert the password to bytes
            byte[] passwordBytes = password.getBytes();

            // Compute the hash value of the password
            byte[] hashedBytes = md.digest(passwordBytes);

            // Convert the hashed bytes to a hexadecimal string representation
            StringBuilder sb = new StringBuilder();
            for (byte hashedByte : hashedBytes) {
                sb.append(Integer.toString((hashedByte & 0xff) + 0x100, 16).substring(1));
            }

            // Return the hashed password as a string
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

}


