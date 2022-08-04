/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package voting;

import connectivity.DataBase;
import connectivity.User1;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import observer.Observer;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import observer.Subject;


public class ResultController implements Initializable,Subject {
    DataBase voting;
    @FXML
    private BarChart<String, Number> barchart;
    @FXML
    private NumberAxis nbid;
    @FXML
    private CategoryAxis categoryid;
    @FXML
    private PieChart piechart;
    
   
    @FXML
    private BarChart<String, Number> barchart1;
    @FXML
    private NumberAxis vid;
    @FXML
    private CategoryAxis gebreid;
    @FXML
    private PieChart piechart1;
    @FXML
    private Button logoutBtn;
    @FXML
    private FontAwesomeIcon close;

    /**
     * Initializes the controller class.
     */
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                  User1 user=User1.getInstance(1, "", "", "");
                  subscribe(user);
                         //
                         voting=DataBase.create();
 
                     
                   
                     /*ObservableList<XYChart.Series<String, Number>> barchartseries = FXCollections.observableArrayList();*/
                     XYChart.Series<String,Number> series= new XYChart.Series<>();
                     series.setName("Votes");
                     
                     ObservableList<PieChart.Data> piechartData=FXCollections.observableArrayList();
                     
                     piechart.setData(piechartData);
                    
                     String query2="SELECT count(*)as total FROM vote";
                     ResultSet result2;
                     try{
                     result2 = voting.SelectFun(query2);
                     int total = 0;
                     while (result2.next()){
                         
                         total=result2.getInt("total");}
                     
                     
                     String query1="SELECT count(id_song)as count,S.type_name FROM `vote` V ,`song` S, `type` T WHERE V.id_song =S.id AND S.type_name=T.name GROUP BY S.type_name ";
                     ResultSet result1;
                     try {
                         result1 = voting.SelectFun(query1);
                         double pourcentage=0.0;
                         
                         
                         while(result1.next()){
                             pourcentage=((result1.getInt("count"))*100)/total;
                             
                             series.getData().add(new XYChart.Data<>(result1.getString("type_name"),pourcentage));
                             piechartData.add(new PieChart.Data(result1.getString("type_name"),pourcentage));
                             
                         }}
                     catch (SQLException ex) {
                         Logger.getLogger(ResultController.class.getName()).log(Level.SEVERE, null, ex);}
                  
                        barchart.getData().add(series);
                         
                     /* ObservableList<XYChart.Series<String, Number>> barchartseries1 = FXCollections.observableArrayList();*/
                    XYChart.Series<String,Number> series1= new XYChart.Series<>();
                     series1.setName("Votes");
                     
                     ObservableList<PieChart.Data> piechartData1=FXCollections.observableArrayList();
                     
                     piechart1.setData(piechartData1);
                      
                      String query4="SELECT count(id_song)as count2,S.genre_name FROM `vote` V ,`song` S, `genre` G WHERE V.id_song =S.id AND S.genre_name=G.name GROUP BY S.genre_name ";
                     ResultSet result4;
                     try {
                         result4 = voting.SelectFun(query4);
                         double pourcentage1=0.0;
                         
                         
                         while(result4.next()){
                             pourcentage1=((result4.getInt("count2"))*100)/total;
                             
                             series1.getData().add(new XYChart.Data<>(result4.getString("genre_name"),pourcentage1));
                             piechartData1.add(new PieChart.Data(result4.getString("genre_name"),pourcentage1));
                             
                         }}
                      
                     catch (SQLException ex) {
                         Logger.getLogger(ResultController.class.getName()).log(Level.SEVERE, null, ex);}
                     barchart1.getData().add(series1);}
                  catch (SQLException ex) {
                Logger.getLogger(ResultController.class.getName()).log(Level.SEVERE, null, ex);}
               
                         
            
                     
                     
}
                  
                    /* */

    @FXML
    private void onLogoutClick(ActionEvent event) throws IOException {
          Stage stage=( Stage)close.getScene().getWindow();
         Parent root1 = FXMLLoader.load(getClass().getResource("login.fxml"));
                  
         Scene scene1 = new Scene(root1);
         stage.setScene(scene1);
         stage.show(); 
         notifyObserver();
    }
    
    @FXML
    private void exit(ActionEvent event) {
        System.exit(0); }
    
    @FXML
   private void Vote(ActionEvent e) throws IOException{
        Stage stage=( Stage)close.getScene().getWindow();
         Parent root1 = FXMLLoader.load(getClass().getResource("Vote.fxml"));
                  
         Scene scene1 = new Scene(root1);
         stage.setScene(scene1);
         stage.show(); 
   }
   
     @FXML
   private void Review(ActionEvent e) throws IOException{
        Stage stage=( Stage)close.getScene().getWindow();
         Parent root1 = FXMLLoader.load(getClass().getResource("Review.fxml"));                 
         Scene scene1 = new Scene(root1);
         stage.setScene(scene1);
         stage.show(); 

   }


    
               
        
                
}      
                 
           
 
                
                        




  
  
    
