/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package voting;

import connectivity.DataBase;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.io.*;
import java.sql.*;
import java.util.*;
import javafx.scene.Node;

/**
 *
 * @author me
 */
public class Admin_login implements Initializable {
  DataBase db=DataBase.create();
   @FXML
    private FontAwesomeIcon close;
     @FXML
    private Label messege;
   @FXML
    private PasswordField password; 
   @FXML
   private Button signin;
   @FXML
    private TextField userName;   
    @FXML
    private void exit(ActionEvent event) {
        System.exit(0); }
  @FXML
    private void validate(ActionEvent event) throws SQLException, IOException {
      String usern=userName.getText();
      String pass=password.getText();
      String sql="SELECT id From admin where username='"+usern+"' and password='"+pass+"';";
      ResultSet r=db.SelectFun(sql);
      if(!r.next()){
          // messege.setText("try again");
           messege.setVisible(true);
       }else{
           Stage stage=( Stage)close.getScene().getWindow();
           Parent root1 = FXMLLoader.load(getClass().getResource("Song.fxml"));
                  
           Scene scene1 = new Scene(root1);
           stage.setScene(scene1);
           stage.show();
       }
      //  System.exit(0);
   }
  @FXML
    private void goToWelcomePage() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("WelcomePage.fxml"));
        
        Stage stage=(Stage)close.getScene().getWindow();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
}
