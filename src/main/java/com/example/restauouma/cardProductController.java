package com.example.restauouma;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class cardProductController implements Initializable {

    @FXML
    private AnchorPane card_form;

    @FXML
    private Button prodcard_addBtn;

    @FXML
    private ImageView prodcard_imageView;

    @FXML
    private Label prodcard_name;

    @FXML
    private Label prodcard_price;

    @FXML
    private Spinner<Integer> prodcard_spinner;

    private productData prodData;
    private Image image;

    private SpinnerValueFactory<Integer> spin;

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    private String prodID;
    private String type;
    private String date;

    private String prod_image;

    public void setData(productData prodData){
        this.prodData = prodData;
        prodID = prodData.getProductId();
        prod_image = prodData.getImage();
        type = prodData.getType();
        date = String.valueOf(prodData.getDate());
        prodcard_name.setText(prodData.getProductName());
        prodcard_price.setText("$"+String.valueOf(prodData.getPrice()));
        String path = "File:" + prodData.getImage();
        image = new Image(path, 199, 98, false, true);
        prodcard_imageView.setImage(image);
        pr = prodData.getPrice();
    }
    private int qty;

    public void setQuantity(){
        spin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        prodcard_spinner.setValueFactory(spin);
    }
    private double totalP;
    private double pr;
    public void addBtn(){
        //MainPageController mForm = new MainPageController();
        //mForm.customerID();
        qty = prodcard_spinner.getValue();
        String check = "";
        String checkAvailable = "SELECT status FROM llx_product WHERE prod_id ='"
                               +prodID+"'";
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/reataupos", "root", "");
            preparedStatement = connection.prepareStatement(checkAvailable);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                check = resultSet.getString("status");
            }
            if (!check.equals("Available") || qty == 0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Something went wrong!");
                alert.showAndWait();
            }else{
                int CQTY= 0;
                String checkQuantity = "SELECT quantite FROM llx_product WHERE prod_id = '"
                        +prodID+"'";
                preparedStatement = connection.prepareStatement(checkQuantity);
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()){
                    CQTY = resultSet.getInt("quantite");
                }
                if (CQTY == 0){
                    String updateQuantity = "UPDATE llx_product SET prod_name = '"
                            + prodcard_name.getText()
                            + "', price = '" + pr
                            + "', quantite = 0, date = '" + date
                            + "', image = '" + prod_image
                            + "', status = 'Unavailable' WHERE prod_id = '"
                            + prodID + "'";
                    preparedStatement = connection.prepareStatement(updateQuantity);
                    preparedStatement.executeUpdate();

                }
                if (CQTY < qty){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("This product is Out of stock");
                    alert.showAndWait();
                }else {
                    String insertData = "INSERT INTO customer " +
                            "(customer_id, prod_name, quantity, price, date, em_username)" +
                            "VALUES (?,?,?,?,?,?)";
                    preparedStatement = connection.prepareStatement(insertData);
                    preparedStatement.setString(1, String.valueOf(data.cID));
                    preparedStatement.setString(2, prodcard_name.getText());
                    preparedStatement.setString(3, String.valueOf(qty));
                    totalP = (qty * pr);
                    preparedStatement.setString(4, String.valueOf(totalP));

                    java.util.Date date = new java.util.Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    preparedStatement.setString(5, String.valueOf(sqlDate));
                    preparedStatement.setString(6, data.display_username);
                    preparedStatement.executeUpdate();
                    int upQuantity = CQTY - qty;
                    prod_image = prod_image.replace("\\", "\\\\");
                    //System.out.println(date);
                    //String updateQuantity = "UPDATE llx_product SET prod_name = '"
                            //+prodcard_name.getText()+"', price = '"
                            //+pr+"', quantite = "
                            //+upQuantity+", date = '"+date+"', image = '"+prod_image+"', status = '"+check+"' WHERE prod_id = '"
                            //+prodID+"'";

                    //preparedStatement = connection.prepareStatement(updateQuantity);
                    //preparedStatement.executeUpdate();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setQuantity();

    }
}
