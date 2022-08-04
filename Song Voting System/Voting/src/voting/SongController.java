/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package voting;

//import tpjx1.LoginController;
import connectivity.DataBase;
import connectivity.Song;
import connectivity.User1;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.awt.Insets;
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
import java.nio.file.Files;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Cursor;
//import javafx.geometry.Insets;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javax.imageio.ImageIO;
import observer.Subject;
import observer.Observer;

/**
 *
 * @author me
 */
public class SongController implements Initializable,Subject {
    DataBase db;
    @FXML
    private ComboBox  comb;
    @FXML
    private ComboBox  comb1;
    @FXML
    private ImageView image;
    @FXML
    private Label message;
    @FXML
    private TextField name_song;
    @FXML
    private TableColumn<Song,String> name;

    @FXML
    private TableColumn<Song, Integer> id;
    @FXML
    private TableColumn<Song, String> action;
    @FXML
    private TableColumn<Song, String> genre_name;

    @FXML
    private TableColumn<Song, String> type_name;


    @FXML
    private TableColumn<Song, String> Photo;
    private String s1=null;

    @FXML
    private TableView<Song> table;
    private ObservableList <Song> Data=FXCollections.observableArrayList();
   // private ObservableList <User1> Data1=FXCollections.observableArrayList(
      //  new User1(1,"rrr","ff","hh")
  // );
    @FXML
    private FontAwesomeIcon close;
    @FXML
    private void exit(ActionEvent event) {
        System.exit(0); 
    }
 @FXML
    private void add_song(ActionEvent event) throws SQLException {
       // String s=name_song.getText().toString();
       // if(s=="")
         //System.out.println(name_song.getText());
     // return;
     if(s1==null){
        message.setVisible(true);
      }else{
       String co,co1;
        if(comb.getValue()==null)
           co=r;
        else
          co=comb.getValue().toString();
        if(comb1.getValue()==null)
           co1=r1;
        else
          co1=comb1.getValue().toString();
        System.out.println(co+" "+co1+" "+name_song.getText());
       // String' sql="INSERT INTO `song` (`name`, `genre_name`, `type_name`, `Path`) VALUES ('"+name_song.getText()+"'', '"+co+"', '"+co1+"', '"+s1+"');";
       String sql="INSERT INTO `voting`.`song` (`name`, `genre_name`, `type_name`, `Path`) VALUES ('"+name_song.getText().toString()+"', '"+co+"', '"+co1+"', '"+s1+"');";
       db.InsertFun(sql);
            Data.clear();

       show();
     }

 // System.exit(0); 
    }

     private List<Observer> ob=new ArrayList<Observer>();
     private void subscribe(Observer o){
       ob.add(o);
    }
    private void unsubscribe(Observer o){
       ob.remove(o);
    }
      @FXML
   private void onLogoutClick(ActionEvent e) throws IOException{
        Stage stage=( Stage)close.getScene().getWindow();
         Parent root1 = FXMLLoader.load(getClass().getResource("Admin_sign-in.fxml"));
                  
         Scene scene1 = new Scene(root1);
         stage.setScene(scene1);
         stage.show();
 
   }
  final FileChooser fc=new FileChooser();
  @FXML
   private void upload(ActionEvent e) throws FileNotFoundException, IOException{
      fc.setTitle("My File Chooser");
      fc.setInitialDirectory(new File(System.getProperty("user.home")));
      fc.getExtensionFilters().clear();
      fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files","*.png","*.jpg","*.jpeg"));
      File f=fc.showOpenDialog(new Stage());
      if(f!=null){
          String s=f.getName();
          s1=s;
         javafx.scene.image.Image image1 = new javafx.scene.image.Image(getClass().getResourceAsStream("images/"+s));     //  photo.setImage(image);
         image.setImage(image1);
     }
   }

    @FXML
    public void notifyObserver(){
       for(Observer o:ob)
           o.update("");
    }
    public void show(){
          String query="select * from song";
          ResultSet result;
           try {
                int i=0;
               result = db.SelectFun(query);
               while(result.next()){
                   Data.add(new Song(result.getInt("id"),result.getString("name"),result.getString("genre_name"),result.getString("type_name"),result.getString("Path")));
                 //  System.out.println(result.getString("username"));
               }

           } catch (SQLException ex) {
               Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
           }
          genre_name.setCellValueFactory(new PropertyValueFactory<Song,String>("genre_name"));
          name.setCellValueFactory(new PropertyValueFactory<Song,String>("name"));
          type_name.setCellValueFactory(new PropertyValueFactory<Song,String>("type_name"));
          id.setCellValueFactory(new PropertyValueFactory<Song,Integer>("id"));
          Photo.setCellValueFactory(new PropertyValueFactory<Song,String>("Path"));

          table.setItems(Data);
       //    Data.add(new User1(1,"hh","password","email"));
    }
    String r;
    String r1;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       ObservableList<String> list =FXCollections.observableArrayList();
        db=DataBase.create();
        String sql="select * from genre";
        try{
           ResultSet result=db.SelectFun(sql);
           while(result.next()){
               list.add(result.getString("name"));

           }
        }catch(SQLException e){}
        comb.setItems(list);
        comb.setPromptText(list.get(0));
        r=list.get(0);
        list =FXCollections.observableArrayList();
        sql="select * from type";
        try{
           ResultSet result=db.SelectFun(sql);
           while(result.next()){
               list.add(result.getString("name"));
           }
        }catch(SQLException e){}
        comb1.setItems(list);
        comb1.setPromptText(list.get(0));
        r1=list.get(0);


     //   comb.setSelectionModel(1);
                //add cell of button edit 
        Callback<TableColumn<Song, String>, TableCell<Song, String>> cellFoctory = (TableColumn<Song, String> param) -> {
            // make cell containing buttons
            final TableCell<Song, String> cell = new TableCell<Song, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIcon deleteIcon = new FontAwesomeIcon();
                        deleteIcon.setGlyphName("TRASH");
                      //  FontAwesomeIcon editIcon = new FontAwesomeIcon();
                      //  editIcon.setGlyphName("PENCIL_SQUARE");
                      //  editIcon.setGlyphName("MINUS");
                      //  editIcon.setSize("20");
                        deleteIcon.setFill(Color.rgb(250, 23, 68)); 
                      //  editIcon.setFill(Color.rgb(0, 230, 118)); 
                      //  editIcon.setCursor(Cursor.HAND);
                        deleteIcon.setCursor(Cursor.HAND);
                        deleteIcon.setSize("20");

                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                          
                             Song song = table.getSelectionModel().getSelectedItem();
                             subscribe(song);
                             notifyObserver();
                             unsubscribe(song);
                             Data.remove(song);                      

                        });
                        HBox managebtn = new HBox(deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        managebtn.setSpacing(4);
                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
       action.setCellFactory(cellFoctory);
        show();
    }    
     
}

