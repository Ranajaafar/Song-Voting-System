/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package voting;


import animatefx.animation.FadeInDownBig;
import connectivity.DataBase;
import connectivity.User1;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.animation.TranslateTransition;
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
import javafx.scene.control.ProgressBar;
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
import observer.Observer;
import observer.Subject;

/**
 *
 * @author me
 */
public class ReviewController implements Initializable,Subject {
    DataBase db;
      @FXML
    private Label label;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label label3;

    @FXML
    private Label label4;

    @FXML
    private Label label5;

    @FXML
    private FontAwesomeIcon nb1;

    @FXML
    private FontAwesomeIcon nb2;

    @FXML
    private FontAwesomeIcon nb3;

    @FXML
    private FontAwesomeIcon nb4;

    @FXML
    private FontAwesomeIcon nb5;
 @FXML
    private ProgressBar pg1;

    @FXML
    private ProgressBar pg2;

    @FXML
    private ProgressBar pg3;

    @FXML
    private ProgressBar pg4;

    @FXML
    private ProgressBar pg5;

   @FXML 
    private FontAwesomeIcon close;
   User1 user;
   @FXML 
   private Button logoutBtn;
   private List<Observer> observer=new ArrayList<Observer>();
    public void subscribe(Observer o){
         observer.add(o);
    }
     public void unsubscribe(Observer o){
         observer.remove(o);
    }

    int nb;
    public void notifyObserver(){
       for(Observer o : observer){
            o.update("");
       }
    }
       @FXML
   private void onLogoutClick(ActionEvent e) throws IOException{
        Stage stage=( Stage)close.getScene().getWindow();
         Parent root1 = FXMLLoader.load(getClass().getResource("login.fxml"));
                  
         Scene scene1 = new Scene(root1);
         stage.setScene(scene1);
         stage.show(); 
         notifyObserver();
   }

 @FXML
   private void Vote(ActionEvent e) throws IOException{
        Stage stage=( Stage)close.getScene().getWindow();
         Parent root1 = FXMLLoader.load(getClass().getResource("Vote.fxml"));
                  
         Scene scene1 = new Scene(root1);
         stage.setScene(scene1);
         stage.show(); 
   }
@FXML
   private void Result(ActionEvent e) throws IOException{
        Stage stage=( Stage)close.getScene().getWindow();
         Parent root1 = FXMLLoader.load(getClass().getResource("Result.fxml"));
                  
         Scene scene1 = new Scene(root1);
         stage.setScene(scene1);
         stage.show(); 
   }

    @FXML
    private Button post;
    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();     
    }
    

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0); }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        user=User1.getInstance(1, "", "", "");
        subscribe(user);
        // TODO
        nb=0;
        db=DataBase.create();
        nb1.setOnMouseClicked(e->{
           nb1.setFill(Color.rgb(255,255,0));
           nb2.setFill(Color.rgb(255,255,255));
           nb3.setFill(Color.rgb(255,255,255));
           nb4.setFill(Color.rgb(255,255,255));
           nb5.setFill(Color.rgb(255,255,255));
           nb=1;
           label.setText("I just hate it");

        });
       nb2.setOnMouseClicked(e->{
           nb1.setFill(Color.rgb(255,255,0));
           nb2.setFill(Color.rgb(255,255,0));
           nb3.setFill(Color.rgb(255,255,255));
           nb4.setFill(Color.rgb(255,255,255));
           nb5.setFill(Color.rgb(255,255,255));
           nb=2;
           label.setText("I don't like it");

        });
        nb3.setOnMouseClicked(e->{
           nb=3;
           nb1.setFill(Color.rgb(255,255,0));
           nb2.setFill(Color.rgb(255,255,0));
           nb3.setFill(Color.rgb(255,255,0));
           nb4.setFill(Color.rgb(255,255,255));
           nb5.setFill(Color.rgb(255,255,255));
           label.setText("It good");

        });
        nb4.setOnMouseClicked(e->{
           nb=4;
           nb1.setFill(Color.rgb(255,255,0));
           nb2.setFill(Color.rgb(255,255,0));
           nb3.setFill(Color.rgb(255,255,0));
           nb4.setFill(Color.rgb(255,255,0));
           nb5.setFill(Color.rgb(255,255,255));
           label.setText("I like it");

        });
        nb5.setOnMouseClicked(e->{
           nb=5;
           nb1.setFill(Color.rgb(255,255,0));
           nb2.setFill(Color.rgb(255,255,0));
           nb3.setFill(Color.rgb(255,255,0));
           nb4.setFill(Color.rgb(255,255,0));
           nb5.setFill(Color.rgb(255,255,0));
           label.setText("I love it");

        });
        
        post.setOnAction(e->{
            String query="";
            if(nb>0){
              if(nb==1)
                   query="UPDATE REVIEW_VISIT SET NB1=NB1+1 WHERE ID=1";
              else if(nb==2)
                   query="UPDATE REVIEW_VISIT SET NB2=NB2+1 WHERE ID=1";
              else if(nb==3)
                   query="UPDATE REVIEW_VISIT SET NB3=NB3+1 WHERE ID=1";
              else if(nb==4)
                   query="UPDATE REVIEW_VISIT SET NB4=NB4+1 WHERE ID=1";
              else if(nb==5)
                   query="UPDATE REVIEW_VISIT SET NB5=NB5+1 WHERE ID=1";
            //System.out.println(query+"");
            try{ 
                db.InsertFun(query);
               Stage stage=(Stage)pg5.getScene().getWindow();
 
               Parent root1 = FXMLLoader.load(getClass().getResource("Review.fxml"));
               Scene scene1 = new Scene(root1);
               stage.setScene(scene1);
               stage.show(); 

                showAlert(Alert.AlertType.CONFIRMATION,post.getScene().getWindow(),"Thank you","we\\'ll use your rating to improve our customer support performance");
            }catch(SQLException ex){
            }   catch (IOException ex) {
                    Logger.getLogger(ReviewController.class.getName()).log(Level.SEVERE, null, ex);
                }
         }
       });

       //  System.out.println("start");
       String query="select nb1,nb2,nb3,nb4,nb5 from REVIEW_VISIT";
        try{ 
             ResultSet result;
             result=db.SelectFun(query);
             if(!result.next())
                showAlert(Alert.AlertType.ERROR,post.getScene().getWindow(),"ERROR","");
             else{
                 int total=result.getInt("nb1")+result.getInt("nb2")+result.getInt("nb3")+result.getInt("nb4")+result.getInt("nb5"); 
                 float f;
                 f=(float)result.getInt("nb1")/total;
                 pg1.setProgress(f);
                 label1.setText(result.getInt("nb1")+" voter");
                 f=(float)result.getInt("nb2")/total;
                 pg2.setProgress(f);
                 label2.setText(result.getInt("nb2")+" voter");
                 f=(float)result.getInt("nb3")/total;
                 pg3.setProgress(f);
                 label3.setText(result.getInt("nb3")+" voter");
                 f=(float)result.getInt("nb4")/total;
                 pg4.setProgress(f);
                 label4.setText(result.getInt("nb4")+" voter");
                 f=(float)result.getInt("nb5")/total;
                 pg5.setProgress(f);
                 label5.setText(result.getInt("nb5")+" voter");
                 

             }
        }catch(SQLException ex){
         }
    }    
    
}
