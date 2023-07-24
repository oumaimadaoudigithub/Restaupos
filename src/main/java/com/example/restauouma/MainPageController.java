package com.example.restauouma;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {

    private List<Table> floor1Tables;
    private List<Table> floor2Tables;


    @FXML
    private Button categories_btn;

    @FXML
    private Button dashbord_btn;

    @FXML
    private Button logout_btn;

    @FXML
    private AnchorPane main_page;

    @FXML
    private Label main_username;

    @FXML
    private Button Appetizer_btn;

    @FXML
    private Button Beverage_btn;

    @FXML
    private Button Burger_btn;

    @FXML
    private Button Dessert_btn;

    @FXML
    private Button Pizza_btn;

    @FXML
    private Button Salad_btn;

    @FXML
    private Button Sandwiche_btn;

    @FXML
    private Button Soup_btn;

    @FXML
    private Button menu_btn;

    @FXML
    private Button parametres_btn;

    @FXML
    private Button products_btn;

    @FXML
    private Button rapport_btn;
    @FXML
    private AnchorPane pane_main;

    @FXML
    private AnchorPane categories_btns;


    @FXML
    private AnchorPane Rapport_page;

    @FXML
    private AnchorPane Table_floor1;

    @FXML
    private AnchorPane Table_floor2;

    @FXML
    private Button users_btn;

    @FXML
    private AnchorPane chart;

    @FXML
    private Button floor1_btn;

    @FXML
    private Button floor2_btn;

    @FXML
    private Button new_order;

    @FXML
    private AnchorPane detail_prod;

    @FXML
    private TextField search_bar_prod;

    @FXML
    private Label search_label_prod;

    @FXML
    private AnchorPane table_prods;

    @FXML
    private TextField search_bar_cat;

    @FXML
    private Label search_label_cat;

    @FXML
    private AnchorPane table_cat;

    @FXML
    private AnchorPane detail_cat;

    @FXML
    private Button prod_addBtn;

    @FXML
    private TableColumn<productData, String> prod_col_productDate;

    @FXML
    private TableColumn<productData, String> prod_col_productID;

    @FXML
    private TableColumn<productData, String> prod_col_productName;

    @FXML
    private TableColumn<productData, String> prod_col_productPrice;

    @FXML
    private TableColumn<productData, String> prod_col_productPttc;

    @FXML
    private TableColumn<productData, String> prod_col_productQty;

    @FXML
    private TableColumn<productData, String> prod_col_productType;

    @FXML
    private TableColumn<productData, String> prod_col_tva;

    @FXML
    private TableColumn<productData, String> prod_col_productStatus;

    @FXML
    private Button prod_deleteBtn;

    @FXML
    private ImageView prod_imageView;

    @FXML
    private Button prod_importBtn;

    @FXML
    private TableView<productData> prod_tableView;

    @FXML
    private Button prod_updateBtn;

    @FXML
    private TextField prod_price;

    @FXML
    private TextField prod_productID;

    @FXML
    private TextField prod_productName;

    @FXML
    private TextField prod_Pttc;

    @FXML
    private TextField prod_tva;

    @FXML
    private TextField prod_Qty;

    @FXML
    private ComboBox<String> prod_Type;
    @FXML
    private AnchorPane Burger_scroll;
    @FXML
    private AnchorPane Pizza_scroll;
    @FXML
    private AnchorPane Sandwich_scroll;
    @FXML
    private AnchorPane Soup_scroll;
    @FXML
    private AnchorPane Appetizer_scroll;

    @FXML
    private ComboBox<String> prod_Status;

    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;

    private Image image;
    @FXML
    private TextField menu_amount;

    @FXML
    private Label menu_change;

    @FXML
    private TableColumn<?, ?> menu_col_productName;

    @FXML
    private TableColumn<?, ?> menu_col_productPrice;

    @FXML
    private TableColumn<?, ?> menu_col_productQty;


    @FXML
    private AnchorPane menu_form;

    @FXML
    private GridPane menu_gridPane;

    @FXML
    private Button menu_payBtn;

    @FXML
    private Button menu_recieptBtn;

    @FXML
    private Button menu_removeBtn;

    @FXML
    private ScrollPane menu_scrollPane;

    @FXML
    private TableView<?> menu_tableView;

    @FXML
    private Label menu_total;

    @FXML
    private List<Button> tableButtons;

    @FXML
    private AnchorPane product_form;

    @FXML
    private AnchorPane cat_form;

    @FXML
    private AnchorPane rapport_form;

    private String[] statusList = {"Available", "Unavailable"};

    public void productStatusList() {

        List<String> statusL = new ArrayList<>();

        for (String data: statusList){
            statusL.add(data);

        }
        ObservableList listData = FXCollections.observableArrayList(statusL);
        prod_Status.setItems(listData);
    }


    @FXML
    private void handleTableButtonClick(ActionEvent event) {
        // Get the clicked button
        Button clickedButton = (Button) event.getSource();

        // Get the label inside the button
        Label label = (Label) clickedButton.getGraphic();

        // Get the table number from the label text
        String labelText = label.getText();
        int tableNumber = Integer.parseInt(labelText);

        // Show the input dialog to enter the number of visitors
        int numberOfVisitors = showNumberOfVisitorsInputDialog();

        // Rest of your logic to handle the number of visitors and food selection
        // ...
    }

    public int showNumberOfVisitorsInputDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Number of Visitors");
        dialog.setHeaderText("Enter the number of visitors for the table:");
        Optional<String> result = dialog.showAndWait();

        // Convert the user input (String) to an integer and return the value
        // If the user canceled the input or entered an empty string, return -1
        return result.map(str -> {
            if (str.isEmpty()) {
                return -1;
            } else {
                return Integer.parseInt(str);
            }
        }).orElse(-1);
    }




    private void handleTableSelection(Table table) {
        // Display a window/dialog to enter the number of visitors for the selected table
        int numberOfVisitors = showNumberOfVisitorsInputDialog();

        // Proceed with selecting food categories for the table
        // selectFoodCategoriesForTable(table, numberOfVisitors);
    }
   /* private void selectFoodCategoriesForTable(Table table, int numberOfVisitors) {
        // Display a window with food categories and let the user select the desired categories
        // Update the table object with the selected food categories and number of visitors
        // ...
    }*/


    private void initializeFloor1Tables() {
        floor1Tables = new ArrayList<>();

        // For example, add 10 tables to floor1Tables with table numbers 1 to 10
        for (int tableNumber = 1; tableNumber <= 9; tableNumber++) {
            floor1Tables.add(new Table(tableNumber));
        }
    }
    private void initializeFloor2Tables() {
        floor2Tables = new ArrayList<>();

        // For example, add 8 tables to floor2Tables with table numbers 11 to 18
        for (int tableNumber = 10; tableNumber <= 18; tableNumber++) {
            floor2Tables.add(new Table(tableNumber));
        }
    }

    public void productUpdateBtn(){
        if(prod_productID.getText().isEmpty()
                || prod_productName.getText().isEmpty()
                || prod_Type.getSelectionModel().getSelectedItem() == null
                || prod_Status.getSelectionModel().getSelectedItem() == null
                || prod_price.getText().isEmpty()
                || prod_Pttc.getText().isEmpty()
                || prod_tva.getText().isEmpty()
                || prod_Qty.getText().isEmpty()
                || data.path == null || data.id == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

        }else {
            String path = data.path;
            path = path.replace("\\", "\\\\");
            String updateData = "UPDATE llx_product SET " +
                    "prod_id = '"+prod_productID.getText()+"', prod_name = '"
                    +prod_productName.getText()+"', status = '"
                    +prod_Status.getSelectionModel().getSelectedItem()+"', type = '"
                    +prod_Type.getSelectionModel().getSelectedItem()+"', price = '"
                    +prod_price.getText()+"', price_ttc = '"
                    +prod_Pttc.getText()+"', tva_tx = '"
                    +prod_tva.getText()+"', quantite = '"
                    +prod_Qty.getText()+"', image = '"
                    +path+"', date = '"+data.date+"' WHERE rowid = "+ data.id;

            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/reataupos", "root", "");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to update Product ID: " +prod_productID.getText() +"?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)){
                    preparedStatement = connection.prepareStatement(updateData);
                    preparedStatement.executeUpdate();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Seccessfully Updated!");
                    alert.showAndWait();
                    // to update my table view
                    productShowData();
                    // to clear your fields
                    productClearBtn();
                }else {
                     alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cancelled.");
                    alert.showAndWait();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    public void productDeleteBtn(){
        if( data.id == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to Delete Product ID: "+prod_productID.getText()+"?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get().equals(ButtonType.OK)){
                String deleteData = "DELETE FROM llx_product WHERE rowid = "+ data.id;
                try {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/reataupos", "root", "");
                    preparedStatement = connection.prepareStatement(deleteData);
                    preparedStatement.executeUpdate();

                     alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully deleted!");
                    alert.showAndWait();
                    productShowData();
                    productClearBtn();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Cancelled");
                alert.showAndWait();
            }
        }
    }

    public void productClearBtn(){
        prod_productID.setText("");
        prod_productName.setText("");
        prod_Type.getSelectionModel().clearSelection();
        prod_Status.getSelectionModel().clearSelection();
        prod_price.setText("");
        prod_Pttc.setText("");
        data.path = "";
        data.id = 0;
        prod_tva.setText("");
        prod_Qty.setText("");
        prod_imageView.setImage(null);
    }

    private ObservableList<productData> cardListData = FXCollections.observableArrayList();

    public void productAddBtn(){

        if(prod_productID.getText().isEmpty()
          || prod_productName.getText().isEmpty()
          || prod_Type.getSelectionModel().getSelectedItem() == null
                || prod_Status.getSelectionModel().getSelectedItem() == null
          || prod_price.getText().isEmpty()
          || prod_Pttc.getText().isEmpty()
          || prod_tva.getText().isEmpty()
          || prod_Qty.getText().isEmpty()
          || data.path == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

        }else {
            //check product Id
            String checkProdID = "SELECT prod_id FROM llx_product WHERE prod_id = '"
                    +prod_productID.getText()+"'";
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/reataupos", "root", "");
                statement = connection.createStatement();
                resultSet = statement.executeQuery(checkProdID);
                if (resultSet.next()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText(prod_productID.getText()+" is already taken");
                    alert.showAndWait();
                }else {
                    String insertData = "INSERT INTO llx_product " +
                            "(prod_id, prod_name, type, price, price_ttc, tva_tx, quantite, image, date, status) "
                            + "VALUES(?,?,?,?,?,?,?,?,?,?)";
                    preparedStatement = connection.prepareStatement(insertData);
                    preparedStatement.setString(1, prod_productID.getText());
                    preparedStatement.setString(2, prod_productName.getText());
                    preparedStatement.setString(3, (String) prod_Type.getSelectionModel().getSelectedItem());
                    preparedStatement.setString(4, prod_price.getText());
                    preparedStatement.setString(5, prod_Pttc.getText());
                    preparedStatement.setString(6, prod_tva.getText());
                    preparedStatement.setString(7, prod_Qty.getText());


                    String path = data.path;
                    path = path.replace("\\", "\\\\");
                    preparedStatement.setString(8, path);
                    // to get current date
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    preparedStatement.setDate(9, sqlDate);
                    preparedStatement.setString(10, (String) prod_Status.getSelectionModel().getSelectedItem());
                    preparedStatement.executeUpdate();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfuly Added!");
                    alert.showAndWait();
                    productShowData();
                    productClearBtn();

                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    // import Btn (from file)
    public void productImportBtn(){
        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image File", "*png", "jpg"));

        File file = openFile.showOpenDialog(main_page.getScene().getWindow());
        if(file != null){
            data.path = file.getAbsolutePath();
            image = new Image(file.toURI().toString(), 136, 119, false, true);
            prod_imageView.setImage(image);
        }

    }



    // merge all datas
    public ObservableList<productData> productDataList(){
        ObservableList<productData> listData = FXCollections.observableArrayList();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/reataupos", "root", "");
            preparedStatement = connection.prepareStatement("SELECT * FROM llx_product");
            resultSet = preparedStatement.executeQuery();
            productData prodData;

            while (resultSet.next()){

                prodData = new productData(resultSet.getInt("rowid")
                        , resultSet.getString("prod_id")
                        , resultSet.getString("prod_name")
                        , resultSet.getString("status")
                        , resultSet.getInt("quantite")
                        , resultSet.getDouble("price")
                        , resultSet.getDouble("price_ttc")
                        , resultSet.getDouble("tva_tx")
                        , resultSet.getString("type")
                        , resultSet.getDate("date")
                        , resultSet.getString("image"));

                listData.add(prodData);

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listData;

    }

    public ObservableList<productData> menuGetData(){

        String sql = "SELECT * FROM llx_product";
        ObservableList<productData> listData = FXCollections.observableArrayList();
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/reataupos", "root", "");
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            productData prod;
            while (resultSet.next()){
                prod = new productData(resultSet.getInt("rowid"),
                        resultSet.getString("prod_id"),
                        resultSet.getString("prod_name"),
                        resultSet.getDouble("price"),
                        resultSet.getString("image"),
                        resultSet.getDate("date"));
                listData.add(prod);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return listData;
    }

    public void menuDisplayCard(){
        cardListData.clear();
        cardListData.addAll(menuGetData());

        int row = 0;
        int column = 0;

        menu_gridPane.getChildren().clear();
        menu_gridPane.getRowConstraints().clear();
        menu_gridPane.getColumnConstraints().clear();


        for (int q = 0; q < cardListData.size(); q++){

            try {
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("cardProduct.fxml"));
                AnchorPane pane = load.load();
                cardProductController cardC = load.getController();
                cardC.setData(cardListData.get(q));

                if (column == 3){
                    column = 0;
                    row+=1;
                }

                menu_gridPane.add(pane, column++, row);
                GridPane.setMargin(pane, new Insets(10));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    private int cID;
    public void customerID(){
        String sql = "SELECT MAX(customer_id) FROM customer";
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/reataupos", "root", "");
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                cID = resultSet.getInt("MAX(customer_id)");
            }
            String checkCID = "SELECT MAX(customer_id) FROM reciept";
            preparedStatement = connection.prepareStatement(checkCID);
            resultSet = preparedStatement.executeQuery();
            int checkID = 0;
            if (resultSet.next()){
                checkID = resultSet.getInt("MAX(customer_id)");
            }
            if (cID == 0){
                cID+=1;
            } else if (cID == checkID) {
                cID+=1;
            }
            data.cID = cID;
        }catch (SQLException e){
            e.printStackTrace();
        }


    }


    public ObservableList<productData> productListData;

    // To Show Data On Our Table
    public void productShowData(){
        productListData = productDataList();
        prod_col_productID.setCellValueFactory(new PropertyValueFactory<>("productId"));
        prod_col_productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        prod_col_productType.setCellValueFactory(new PropertyValueFactory<>("type"));
        prod_col_productPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        prod_col_productPttc.setCellValueFactory(new PropertyValueFactory<>("priceTtc"));
        prod_col_tva.setCellValueFactory(new PropertyValueFactory<>("tvaTaxe"));
        prod_col_productQty.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        prod_col_productDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        prod_col_productStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        prod_tableView.setItems(productListData);

    }

    public void productSelectData(){
        productData prodData = prod_tableView.getSelectionModel().getSelectedItem();
        int num = prod_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) return;
        prod_productID.setText(prodData.getProductId());
        prod_productName.setText(prodData.getProductName());
        prod_Status.setValue(prodData.getStatus()); // Set the selected value
        prod_Type.setValue(prodData.getType());
        prod_price.setText(String.valueOf(prodData.getPrice()));
        prod_Pttc.setText(String.valueOf(prodData.getPriceTtc()));
        prod_tva.setText(String.valueOf(prodData.getTvaTaxe()));
        prod_Qty.setText(String.valueOf(prodData.getQty()));

        data.path = prodData.getImage();

        String path = "File:" + prodData.getImage();
        data.date = String.valueOf(prodData.getDate());
        data.id = prodData.getId();

        image = new Image(path, 136, 119, false, true);
        prod_imageView.setImage(image);
    }

    private String[] typeList = {"Appetizers","Pizzas","Sandwiches","Salads","Soups","Burgers","Beverages","Desserts"};
    public void productTypeList(){
        List<String> typeL = new ArrayList<>();

        for (String data: typeList){
            typeL.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(typeL);
        prod_Type.setItems(listData);
    }



    public void SwitchForm(ActionEvent event){
        if (event.getSource() == menu_btn){
            menu_form.setVisible(true);
            product_form.setVisible(false);
            rapport_form.setVisible(false);
            cat_form.setVisible(false);
            menuDisplayCard();
        } else if (event.getSource() == rapport_btn) {
            rapport_form.setVisible(true);
            menu_form.setVisible(false);
            product_form.setVisible(false);
            cat_form.setVisible(false);
        }  else if (event.getSource() == products_btn) {
            product_form.setVisible(true);
            menu_form.setVisible(false);
            rapport_form.setVisible(false);
            cat_form.setVisible(false);
            productTypeList();
            productShowData();
            productStatusList();
        } else if (event.getSource() == categories_btn){
            product_form.setVisible(false);
            menu_form.setVisible(false);
            rapport_form.setVisible(false);
            cat_form.setVisible(true);
        }
    }




    public void logout(){
        try{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout");
            Optional<ButtonType> option = alert.showAndWait();

            if(option.get().equals(ButtonType.OK)){
                logout_btn.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setTitle("log In");
                stage.setScene(scene);
                stage.show();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void displayUsername(){
        String user = data.display_username;
        user =user.substring(0,1).toUpperCase() + user.substring(1);
        main_username.setText(user);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayUsername();
        productTypeList();
        productStatusList();
        productShowData();
        //initializeFloor1Tables();
        //initializeFloor2Tables();
        menuDisplayCard();
    }
}
