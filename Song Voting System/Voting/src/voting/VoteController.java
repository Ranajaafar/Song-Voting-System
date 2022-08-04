/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package voting;


import connectivity.DataBase;
import connectivity.User1;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import observer.Observer;
import observer.Subject;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class VoteController implements Initializable ,Subject{
  //  Preferences p;
    /**
     * Initializes the controller class.
     */
    connectivity.DataBase db; 
    User1 user;
    @FXML
    private FontAwesomeIcon close;

    @FXML
    private VBox type;
  
    @FXML
    private VBox song;

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
   private void Review(ActionEvent e) throws IOException{
        Stage stage=( Stage)close.getScene().getWindow();
         Parent root1 = FXMLLoader.load(getClass().getResource("Review.fxml"));                 
         Scene scene1 = new Scene(root1);
         stage.setScene(scene1);
         stage.show(); 

   }


    @FXML
    void exit(ActionEvent event) {
        System.exit(0); 
    }
    private List<Observer> observer=new ArrayList<Observer>();

    public void subscribe(Observer o){
         observer.add(o);
    }
     public void unsubscribe(Observer o){
         observer.remove(o);
    }
    public void notifyObserver(){
       for(Observer o : observer){
            o.update("");
       }
    }
@FXML
   private void Result(ActionEvent e) throws IOException{
        Stage stage=( Stage)close.getScene().getWindow();
         Parent root1 = FXMLLoader.load(getClass().getResource("Result.fxml"));
                  
         Scene scene1 = new Scene(root1);
         stage.setScene(scene1);
         stage.show(); 
   }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        user=User1.getInstance(1, "", "", "");
        subscribe(user);
        db=DataBase.create(); 
        String sql="select name from type";
        try{
           ResultSet re=db.SelectFun(sql);
            while(re.next()){
                  Button b=new Button();
                  b.setText(re.getString("name"));
                  b.setPrefWidth(170.0);
                  b.setCursor(Cursor.HAND);
                  String s=re.getString("name");
                  b.setBackground(new Background(new BackgroundFill(Color.rgb(100,32,72),CornerRadii.EMPTY,Insets.EMPTY)));
                  b.setTextFill(Color.WHITE);
                  b.setOnMouseClicked(e->{
                         song.getChildren().clear();

                         String sql1="select distinct genre_name from song where type_name=\'"+s+"\' order by id";
                         ResultSet result;
                      try {
                          result = db.SelectFun(sql1);
                          ArrayList<String> arry=new ArrayList<String>();
                          ArrayList<HBox>  hb=new ArrayList<HBox>();
                          ArrayList<Button> button=new ArrayList<Button>();
                          ScrollPane scroll=new ScrollPane();

                          while(result.next()){
                               HBox h=new HBox();
                               h.setAlignment(Pos.CENTER);
                               h.setSpacing(12);
                               h.setPrefWidth(474);
                             
                               scroll=new ScrollPane();
                               scroll.setContent(h);
                               scroll.setPrefHeight(150);
                               Insets in=new Insets(12,0,0,0);
                               VBox.setMargin(scroll,in);
                               in=new Insets(0,3,0,3);
                               scroll.setPadding(in);
      
                               Pane p=new Pane();
                               p.setPrefWidth(476);
                               p.setPrefHeight(45);                              
                               p.setBackground(new Background(new BackgroundFill(Color.rgb( 84, 16, 56),CornerRadii.EMPTY,Insets.EMPTY)));
                                 //            VBox.setMargin(p,in);

                               Label l=new Label();
                               l.setText("Vote For Your Favorite "+s+" "+result.getString("genre_name")+" Song");
                               in=new Insets(10,0,0,0);
                               l.setFont(Font.font("verdana", FontPosture.REGULAR, 18));

                               l.setPadding(in);
                               String s1=result.getString("genre_name");
                               arry.add(s1);
                               hb.add(h);

                               Button b1=new Button();
                               button.add(b1);
                               b1.setText("vote");
                               b1.setLayoutX(205);
                               b1.setLayoutY(10);
                               b1.setCursor(Cursor.HAND);
        
                               p.getChildren().add(b1);

                               song.getChildren().addAll(l);
                               song .getChildren().add(scroll);
                               song.getChildren().add(p);

                         }
                        for(int i=0;i<arry.size();i++){
                            ToggleGroup g=new ToggleGroup(); 
                            try{
                               String sqll1="select id,name,Path from song where genre_name=\'"+arry.get(i)+"\' and type_name=\'"+s+"\'"; 
                             //  DataBase db1=DataBase.create();

                               ResultSet result1=db.SelectFun(sqll1);
                                while(result1.next()){
                                  VBox vb=new VBox();
                                  vb.setAlignment(Pos.CENTER);
                                  vb.setSpacing(12);
                                  javafx.scene.image.Image image = new javafx.scene.image.Image(getClass().getResourceAsStream("images/"+result1.getString("Path")));
                                  ImageView imageview = new ImageView();
                                  imageview.setImage(image);
                                  imageview.setFitWidth(150);
                                  imageview.setFitHeight(100);
                                  vb.getChildren().add(imageview);
                                  RadioButton radio=new RadioButton(); 
                                  radio.setText(result1.getString("name"));
                                  radio.setToggleGroup(g);
                                  radio.setId(result1.getString("id"));
                                  vb.getChildren().add(radio);

                                  (hb.get(i)).getChildren().add(vb);
                                 // radio.setAccessibleHelp(sql1);
                                 // radio.setR
                               }
                                ObservableList<Toggle> rff= g.getToggles();
                                sqll1="select s.id from song s,vote v where genre_name=\'"+arry.get(i)+"\' and type_name=\'"+s+"\' and v.id_user=\'"+user.getId()+"\' and s.id=v.id_song"; 
                                result1=db.SelectFun(sqll1);
                                Button b1=button.get(i);

                                if(result1.next()){
                                          for(int j=0;j<rff.size();j++){
                                             RadioButton ra=(RadioButton) rff.get(j);
                                            // System.out.println(result1.getString("id"));
                                             if(ra.getId().equals(result1.getString("id"))){
                                                 ra.setSelected(true);
                                                 ra.setUnderline(true);
                                             }else
                                                 ra.setDisable(true);
                                         }
                                         b1.setDisable(true);
                                }else{
                                 b1.setOnMouseClicked(e1->{
                                   
                                    RadioButton radio=null;
                                    radio=(RadioButton)g.getSelectedToggle();
                                    if(radio!=null){
            
                                       String query="insert into vote values("+user.getId()+","+radio.getId()+");";
                                      try{
                                       db.InsertFun(query);
                                     }catch(SQLException exce){
                                            Logger.getLogger(VoteController.class.getName()).log(Level.SEVERE, null, exce);

                                       }
                                     b1.setOnMouseClicked(e2->{});
                                    for(int j=0;j<rff.size();j++){
                                             RadioButton ra=(RadioButton) rff.get(j);
                                            // System.out.println(result1.getString("id"));
                                             if(ra.getId().equals(radio.getId())){
                                                 ra.setUnderline(true);
                                             }else
                                                 ra.setDisable(true);


                                    }
                                    b1.setDisable(true);
 
                                    }
                               
                               }); 

                                }
                           }catch(SQLException sqlfff){
                             }   
                      }

                 } catch (SQLException ex) {
                          Logger.getLogger(VoteController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                                           
                  
                                             //   song.getChildren().clear();
                                           
                  
                                             //   song.getChildren().clear();
             });
             type.getChildren().addAll(b);

           }
       }catch(SQLException e){

        }

    }
}
