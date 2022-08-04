
package voting;

import animatefx.animation.FadeInDownBig;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;




public class Main extends Application {
    
 
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("SplashScreen.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
        
        new FadeInDownBig(root).play();
        
    }
    

    
    public static void main(String[] args) {
        launch(args);

    }
    
}
