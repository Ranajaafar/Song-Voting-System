/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package voting;

import connectivity.DataBase;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class SignupController implements Initializable {
    DataBase db;  

    @FXML
    private Pane login_pane;
    @FXML
    private FontAwesomeIcon close;
    @FXML
    private FontAwesomeIcon refresh;
    @FXML
    private Button login_section;
    @FXML
    private Button signup_section;
    @FXML
    private Pane username_field;
    @FXML
    private TextField username;
    @FXML
    private Pane password_field;
    @FXML
    private PasswordField password_invisible;
    @FXML
    private Button show;
    @FXML
    private Pane text_password_field;
    @FXML
    private TextField password_visible;
    @FXML
    private Button hide;

    @FXML
    private TextField email;

    String user,em,password;
    @FXML
    private Button signup_button;
    @FXML
    private Pane username_field1;
    @FXML
    private ProgressIndicator pb;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      db=DataBase.create();
        // TODO
    }    

       @FXML
    private void exit(ActionEvent event) {
        System.exit(0); }
    
    @FXML
    private void refresh(ActionEvent event) throws IOException {
       Stage stage=(Stage)close.getScene().getWindow();
 
       Parent root1 = FXMLLoader.load(getClass().getResource("signup.fxml"));
       Scene scene1 = new Scene(root1);
       stage.setScene(scene1);
       stage.show(); 
     }
    
     @FXML  
    private void viewpassword(ActionEvent e){
        if(e.getSource().equals(show)){
            text_password_field.setVisible(true);
            password_field.setVisible(false);
            password_visible.setText(password_invisible.getText());}
        else if(e.getSource().equals(hide)){
                text_password_field.setVisible(false);
                password_field.setVisible(true);
                password_invisible.setText(password_visible.getText());
        }
    }

   @FXML    
   private void signup(ActionEvent event) throws IOException, SQLException {
         new createAccount().start();
     }
   
       private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();     
    }
       
       public boolean isEmailValid(String email){
           java.util.regex.Pattern pattern =java.util.regex.Pattern.compile( "^.+@.+\\..+$");
           java.util.regex.Matcher matcher = pattern.matcher(email);
           return matcher.matches();
       }

    
     @FXML
    private void loginSection(ActionEvent event) throws IOException {
         Stage stage=( Stage)close.getScene().getWindow();
         Parent root1 = FXMLLoader.load(getClass().getResource("login.fxml"));
                  
         Scene scene1 = new Scene(root1);
         stage.setScene(scene1);
         stage.show(); 
        
    }
    
       class createAccount extends Thread{
       
        @Override
        public void run(){
            
            try {
                pb.setVisible(true);
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
                    
            Platform.runLater(new Runnable(){
                        
                        public void run(){
                            pb.setVisible(false);
                            
                            user=username.getText().trim();
         
                            em=email.getText().trim();

                            if(text_password_field.isVisible())
                                password=password_visible.getText().trim(); 
                            else
                                password=password_invisible.getText().trim(); 

                            if(user.isEmpty() || password.isEmpty() || em.isEmpty()){
                                 showAlert(Alert.AlertType.ERROR,signup_button.getScene().getWindow(),"ERROR","Enter a valid username, email or password");
                            }else if(!isEmailValid(em)){
                                showAlert(Alert.AlertType.ERROR,signup_button.getScene().getWindow(),"ERROR","Enter a valid email address");}
                           else{

                           String query = "INSERT INTO user (username,password,email) VALUES ('"+user+"','"+password+"','"+em+"')";
                                try {
                                    db.InsertFun(query);
                                } catch (SQLException ex) {
                                    Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
                                }


                           showAlert(Alert.AlertType.CONFIRMATION,signup_button.getScene().getWindow(),"SUCCESS","Account created successfully!");

                           Stage stage=(Stage)close.getScene().getWindow();
                           Parent root1 = null;
                                try {
                                    root1 = FXMLLoader.load(getClass().getResource("login.fxml"));
                                } catch (IOException ex) {
                                    Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
                                }

                           Scene scene1 = new Scene(root1);
                           stage.setScene(scene1);
                           stage.show(); 
                        }
                       }
                    });
                } }

    
    
}
