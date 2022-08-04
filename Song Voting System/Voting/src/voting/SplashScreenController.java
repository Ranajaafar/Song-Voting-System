package voting;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



public class SplashScreenController implements Initializable {
    
    @FXML
     private Pane splash_pane;


    @Override
    public void initialize(URL url, ResourceBundle rb) {     
     
        new splashScreen().start(); 
      
    }
    
    class splashScreen extends Thread {
 
        @Override
        public void run(){
            
            try {

                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                Logger.getLogger(SplashScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
          
        Platform.runLater(new Runnable(){
            
            public void run(){
              
            Parent root1 = null;
            try {
                root1 = FXMLLoader.load(getClass().getResource("WelcomePage.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(SplashScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Scene scene = new Scene(root1);
            Stage stage = new Stage();
            
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
            
            
            
            splash_pane.getScene().getWindow().hide();
            }
            });
     
    }

  };
    

}
