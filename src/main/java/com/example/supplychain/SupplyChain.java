package com.example.supplychain;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class SupplyChain extends Application {
    public static final int width= 700,height=600,headerBar=50;
    Pane bodyPane=new Pane();
    Login login=new Login();
    SignUp signUp=new SignUp();
    ProductDetails productDetails=new ProductDetails();
    Button globalLoginButton;
    Button globalSignUpButton;
    Label customerEmailLabel=null;
    String customerEmail=null;
    private GridPane headerBar(){
        TextField searchField=new TextField();
        Button searchButton=new Button("Search");
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String productName=searchField.getText();

                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(productDetails.getAllProductsByName(productName));
            }
        });
        globalLoginButton=new Button("Login");
        customerEmailLabel=new Label("Welcome User");

        globalLoginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(loginPage());
            }
        });
        globalSignUpButton=new Button("SignUp");
        globalSignUpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(signUpPage());
            }
        });

        GridPane pane=new GridPane();
        pane.setMinSize(bodyPane.getMinWidth(),headerBar-10);
        pane.setVgap(5);
        pane.setHgap(5);
        pane.setAlignment(Pos.CENTER);
        pane.add(searchField,0,0);
        pane.add(searchButton,1,0);
        pane.add(globalLoginButton,2,0);
        pane.add(customerEmailLabel,3,0);
        pane.add(globalSignUpButton,4,0);

        return pane;
    }
    private GridPane footerBar(){

        Button addToCartButton=new Button("Add to Cart");
        Button buyNowButton=new Button("Buy Now");

        Label orderMessageLabel=new Label("");
        buyNowButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                Product selectedProduct= productDetails.getSelectedProduct();

                if(Order.placeOrder(customerEmail,selectedProduct)){
                    orderMessageLabel.setText("Ordered");

                }else{
                    orderMessageLabel.setText("Order Failed");
                }

            }

        });


        GridPane pane=new GridPane();
        pane.setMinSize(bodyPane.getMinWidth(),headerBar-10);
        pane.setVgap(5);
        pane.setHgap(50);
        pane.setAlignment(Pos.CENTER);
        pane.setTranslateY(headerBar+height);
        pane.add(addToCartButton,0,0);
        pane.add(buyNowButton,1,0);
        pane.add(orderMessageLabel,2,0);


        return pane;
    }
    private GridPane loginPage(){
        Label emailLabel=new Label("Email");
        Label passwordLabel=new Label("Password");
        Label messageLabel=new Label("messsage");

        TextField emailTextField=new TextField();
        PasswordField passwordField=new PasswordField();
        Button loginButton=new Button("Login");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent){
                String email=emailTextField.getText();
                String password=passwordField.getText();
//                messageLabel.setText(email + " " + password);
                if(login.customerLogin(email,password)){
                    messageLabel.setText("Login Successful");
                    customerEmailLabel.setText("Welcome : " + email);
                    customerEmail=email;
                    globalLoginButton.setDisable(true);
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().add(productDetails.getAllProducts());
                }else{
                    messageLabel.setText("Login Failed.... Incorrect credentials");
                }

            }
        });

        GridPane pane=new GridPane();
        pane.setMinSize(bodyPane.getMinWidth(),bodyPane.getMinHeight());
        pane.setVgap(5);
        pane.setHgap(5);
        pane.setAlignment(Pos.CENTER);
        pane.add(emailLabel,0,0);
        pane.add(emailTextField,1,0);
        pane.add(passwordLabel,0,1);
        pane.add(passwordField,1,1);
        pane.add(loginButton,0,2);
        pane.add(messageLabel,1,2);

        return pane;

    }
    private GridPane signUpPage(){
        Label emailLabel=new Label("Email");
        Label passwordLabel=new Label("Password");
        Label firstNameLabel=new Label("First Name");
        Label lastNameLabel=new Label("Last Name");
        Label mobileLabel=new Label("MobileNumber");
        Label messageLabel=new Label("");

        TextField emailTextField=new TextField();
        PasswordField passwordField=new PasswordField();
        TextField firstNameTextField=new TextField();
        TextField lastNameTextField=new TextField();
        TextField mobileTextField=new TextField();
        Button signUpButton=new Button("SignUp");
        signUpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent){

                String email=emailTextField.getText();
                String password=passwordField.getText();
                String firstName=firstNameTextField.getText();
                String lastName=lastNameTextField.getText();
                String mobile=mobileTextField.getText();
                if(signUp.addCustomer(email,password,firstName,mobile)){
                    messageLabel.setText("SignUp Successful");

                    globalSignUpButton.setDisable(true);
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().add(productDetails.getAllProducts());
                }else{
                    messageLabel.setText("SignUp Failed");
                }

            }
        });

        GridPane pane=new GridPane();
        pane.setMinSize(bodyPane.getMinWidth(),bodyPane.getMinHeight());
        pane.setVgap(5);
        pane.setHgap(5);
        pane.setAlignment(Pos.CENTER);
        pane.add(emailLabel,0,0);
        pane.add(emailTextField,1,0);
        pane.add(passwordLabel,0,1);
        pane.add(passwordField,1,1);
        pane.add(firstNameLabel,0,2);
        pane.add(firstNameTextField,1,2);
        pane.add(lastNameLabel,0,3);
        pane.add(lastNameTextField,1,3);
        pane.add(mobileLabel,0,4);
        pane.add(mobileTextField,1,4);
        pane.add(signUpButton,0,5);
        pane.add(messageLabel,1,5);

        return pane;

    }
    private Pane createContent(){
        Pane root=new Pane();
        root.setPrefSize(width,height+2*headerBar);
        bodyPane.setMinSize(width,height);
        bodyPane.setTranslateY(headerBar);
        bodyPane.getChildren().addAll(productDetails.getAllProducts());

        root.getChildren().addAll(headerBar(),bodyPane,footerBar());
        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}