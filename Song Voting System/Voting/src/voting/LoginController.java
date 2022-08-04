package voting;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import animatefx.animation.FadeInDownBig;
import connectivity.DataBase;
import connectivity.User1;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;


public class LoginController implements Initializable {
 
  
   @FXML 
    private FontAwesomeIcon close;
    private PasswordField pass;
    connectivity.DataBase db;  
    @FXML
    private TextField username;

    @FXML
    private Pane password_field;
    @FXML
    private PasswordField password_invisible;

    @FXML
    private Pane text_password_field;   
    @FXML
    private TextField password_visible;

    @FXML
    private Button show;
    @FXML
    private Button hide;

    @FXML
    private Button login_button;
    
    String user,password;
    @FXML
    private Pane login_pane;
    @FXML
    private FontAwesomeIcon refresh;
    @FXML
    private Button login_section;
    @FXML
    private Button signup_section;
    @FXML
    private Pane username_field;
    @FXML
    private ProgressIndicator pb;
    
     ResultSet result;
    @FXML
    private ScrollPane scroll;
    @FXML
    private VBox vbox;
    @FXML
    private CheckBox check;
    
    Preferences p;
    int r=-1;
    Boolean showList=false;
    
    @FXML
    private void exit(ActionEvent event) {
        System.exit(0); }
    
    @FXML
    private void refresh(ActionEvent event) throws IOException {
       Stage stage=(Stage)close.getScene().getWindow();
 
       Parent root1 = FXMLLoader.load(getClass().getResource("login.fxml"));
       Scene scene1 = new Scene(root1);
       stage.setScene(scene1);
       stage.show(); 
     }
     @FXML
    private void goToWelcomePage() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("WelcomePage.fxml"));
        
        Stage stage=(Stage)close.getScene().getWindow();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
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
    private void login(ActionEvent event){    
         new validateAccount().start(); 
    }
    
    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();     
    }
   

    @FXML
    private void signupSection(ActionEvent event) throws IOException {
         Stage stage=( Stage)close.getScene().getWindow();
         Parent root1 = FXMLLoader.load(getClass().getResource("signup.fxml"));
                  
         Scene scene1 = new Scene(root1);
         stage.setScene(scene1);
         stage.show(); 
    }
    

   class validateAccount extends Thread{
       
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
                            
                            
                            user=username.getText();
         
                            if(text_password_field.isVisible())
                                password=password_visible.getText(); 
                            else
                                password=password_invisible.getText(); 

                            if(user.isEmpty() || password.isEmpty()){
                                 showAlert(Alert.AlertType.ERROR,login_button.getScene().getWindow(),"ERROR","Enter a valid username or password");
                            }else{
          
  String query = "SELECT * FROM user WHERE (username=\'"+user+"\' || email=\'"+user+"\') && password=\'"+password+"\'";
                                ResultSet result = null;
                                
                                try {
                                    result = db.SelectFun(query);
                                } catch (SQLException ex) {
                                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                                }
         
                                try {
                                    if(!result.next()){
                                        showAlert(Alert.AlertType.ERROR,login_button.getScene().getWindow(),"ERROR","You don't have an account yet, Sign Up!");
                                    }
                                    else{
                                       int id_user=result.getInt("id");
                                       User1 user_1=User1.getInstance(id_user, result.getString("Username"), result.getString("Password"), result.getString("email"));  
                                       boolean b=check.isSelected();
                                       
                                        if(b==true){
                                            
                                             String USERNAME="username"+r;
                                             String PASSWORD="password"+r;
                                             
                                             p.put(USERNAME, user);
                                             p.put(PASSWORD, password);

                                             int newNbUser=Integer.parseInt(p.get("nbUser", null));
                                             newNbUser++;
                                             p.remove("nbUser");
                                             p.put("nbUser",""+newNbUser); }
                                         Stage stage=( Stage)close.getScene().getWindow();
                                         Parent root1 = FXMLLoader.load(getClass().getResource("Vote.fxml"));
                  
                                          Scene scene1 = new Scene(root1);
                                          stage.setScene(scene1);
                                          stage.show(); 
                                       
                                         // stage.setX(22);
                                          //stage.setY(22);
                                       //  new FadeInDownBig(root1).play();
                                        
                                    }                      } catch (SQLException ex) {
                                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (IOException ex) {
                                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                    });
                } }
    
    
   
   
   
   
     @FXML
    private void removeScrollPane(MouseEvent e) {
          scroll.setVisible(false);
    } 
    
     @FXML
    private void ShowScrollPane(MouseEvent event){
            if(showList==true){
                scroll.setFitToWidth(true);
                scroll.setVisible(true);
            }
    }
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
          db=DataBase.create();

          p=Preferences.userNodeForPackage(this.getClass());
          
         int i=0;
         
         if(p!=null){
         
           if(p.get("nbUser", null)==null){
               
                p.put("nbUser",""+0);
                showList=false;
                r=0; }
           else{
             Integer nb = Integer.parseInt(p.get("nbUser", null));
             
             for(int kl=0;kl<nb;kl++){

                 
                while(p.get("username"+i ,null)==null){
                      if(r==-1)
                          r=i;
                      i++;
                 }

                 
                 String usr = p.get("username"+i, null);
                 String pas = p.get("password"+i, null);
                 
                 Pane item = new Pane();
                 item.setId(""+i);
                 item.setPrefHeight(30);
                 item.setPrefWidth(225.0);
                 item.setCursor(Cursor.HAND);
                 
                 item.setStyle("-fx-border-width: 0px 0px  1px 0px; -fx-border-color: #aeaeae");

                 item.setOnMouseClicked(e->{
                        
                        username.setText(usr);     
                      
                        if(text_password_field.isVisible())
                            password_visible.setText(pas); 
                        else
                            password_invisible.setText(pas);
                        
                      
                        scroll.setVisible(false);



                });

          
                 Label itemLabel = new Label(usr);

                         
                 PasswordField itemPass = new PasswordField();
                 
                 itemPass.setText(pas);
                 itemPass.setEditable(false);
                 itemPass.setPrefWidth(100);
                 itemPass.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,CornerRadii.EMPTY,Insets.EMPTY)));
                 itemPass.setLayoutX(-5);
                 itemPass.setLayoutY(11);
                 itemPass.setCursor(Cursor.HAND);
                 itemPass.setOnMouseClicked(e->{

                      username.setText(usr);
                     
                      if(text_password_field.isVisible())
                            password_visible.setText(pas); 
                      else
                            password_invisible.setText(pas);
                      
                      scroll.setVisible(false);
                    
                 });
                 item.getChildren().addAll(itemLabel,itemPass);

                 
                 FontAwesomeIcon F = new FontAwesomeIcon();
                 F.setGlyphName("MINUS");
                 F.setSize("12");
                 F.setFill(Color.rgb(250, 0, 0)); 
                 Button removeBtn = new Button();
                 removeBtn.setGraphic(F);
                 removeBtn.setFont(Font.font("System",1));
                 removeBtn.setLayoutX(190);
                 removeBtn.setLayoutY(7);
                 removeBtn.setPrefHeight(15);
                 removeBtn.setPrefWidth(15);
                 removeBtn.setOnAction(e->{
                 
                     Button b1=(Button)e.getSource();
                     Pane paneToRemove=(Pane)b1.getParent();
                     
                     Integer paneId = Integer.parseInt(paneToRemove.getId());
                     
                     if(r>paneId)
                       r=paneId;

                     p.remove("username"+paneId);
                     p.remove("password"+paneId);
                     
                     vbox.getChildren().remove(paneToRemove);
                     
                     int nbUser = Integer.parseInt(p.get("nbUser", null));
                     nbUser--;
                     p.remove("nbUser");
                     p.put("nbUser",""+nbUser);
                     
                     if(nbUser==0){
                         scroll.setVisible(false);
                         showList=false;
                     }
                 });
         
                 item.getChildren().addAll(removeBtn);
                 i++;
                 
                 vbox.getChildren().add(item);
       
                 
                 showList=true;
             }
               System.out.println("Accounts List restored!");   
           if(r == -1)
              r=i;
         }
       }


    
    } 
    
   
    
    class checkSavedAccounts extends Thread{
       
        @Override
        public void run(){
            
          p=Preferences.userNodeForPackage(this.getClass());
          
         int i=0;
         
         if(p!=null){
           // if there is no element in the list  
           if(p.get("nbUser", null)==null){
                p.put("nbUser",""+0);
                showList=false;
                r=0; }
           else{
               
             Integer nb = Integer.parseInt(p.get("nbUser", null));
             for(int kl=0;kl<nb;kl++){
                
                while(p.get("username"+i ,null)==null){
                      if(r==-1)
                          r=i;
                      i++;
                 }
                
                 String usr = p.get("username"+i, null);
                 String pas = p.get("password"+i, null);  
                 
                 Pane item = new Pane();
                 item.setId(""+i);
                 item.setPrefHeight(30);
                 item.setPrefWidth(225.0);
                 item.setCursor(Cursor.HAND);
                 
                 item.setStyle("-fx-border-width: 0px 0px  1px 0px; -fx-border-color: #aeaeae");

                 item.setOnMouseClicked(e->{

                      username.setText(usr);
                      password_invisible.setText(pas);
                      
                      scroll.setVisible(false);
                      
   

                });
                 
          
                 Label itemLabel = new Label(usr);
              
                         
                 PasswordField itemPass = new PasswordField();
                 
                 itemPass.setText(pas);
                 itemPass.setEditable(false);
                 itemPass.setPrefWidth(100);
                 itemPass.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,CornerRadii.EMPTY,Insets.EMPTY)));
                 itemPass.setLayoutX(-5);
                 itemPass.setLayoutY(11);
                 itemPass.setCursor(Cursor.HAND);
                 itemPass.setOnMouseClicked(e->{

                      username.setText(usr);
                      password_invisible.setText(pas);
                      
                      scroll.setVisible(false);
                    
                 });
                 item.getChildren().addAll(itemLabel,itemPass);
                 
                 
                 FontAwesomeIcon F = new FontAwesomeIcon();
                 F.setGlyphName("MINUS");
                 F.setSize("12");
                 F.setFill(Color.rgb(250, 0, 0)); 
                 Button removeBtn = new Button();
                 removeBtn.setGraphic(F);
                 removeBtn.setFont(Font.font("System",1));
                 removeBtn.setLayoutX(190);
                 removeBtn.setLayoutY(7);
                 removeBtn.setPrefHeight(15);
                 removeBtn.setPrefWidth(15);
                 removeBtn.setOnAction(e->{
                 
                     Button b1=(Button)e.getSource();
                     Pane paneToRemove=(Pane)b1.getParent();
                     
                     Integer paneId = Integer.parseInt(paneToRemove.getId());
                     
                     if(r>paneId)
                       r=paneId;

                     p.remove("username"+paneId);
                     p.remove("password"+paneId);
                     
                     vbox.getChildren().remove(paneToRemove);
                     
                     int nbUser = Integer.parseInt(p.get("nbUser", null));
                     nbUser--;
                     p.remove("nbUser");
                     p.put("nbUser",""+nbUser);   
                     
                     if(nbUser==0){
                         scroll.setVisible(false);
                         showList=false;
                     }
                 });
         
                 item.getChildren().addAll(removeBtn);
                 i++;
                 
                 vbox.getChildren().add(item);
                 
                 showList=true;
             }
             
           if(r == -1)
              r=i;
         }
       }
         
        } 
    }
    
    
    
}
