
package voting;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



public class WelcomePageController implements Initializable {
    @FXML
    private Button Admin_Button;

    @FXML
    private void goToLoginPage(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
   @FXML
    private void exit(ActionEvent event) {
        System.exit(0); }
    
  
     @FXML
    private void goToLoginAdminPage(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Admin_sign-in.fxml"));
        
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }    
    
}
