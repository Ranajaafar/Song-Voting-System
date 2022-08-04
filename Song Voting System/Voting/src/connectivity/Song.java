/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connectivity;

import java.sql.SQLException;
import observer.Observer;


public class Song implements Observer{
    Integer id;
    String name,genre_name,type_name,Path;
    public Song(Integer id,String name,String genre_name,String type_name,String Path){
       this.name=name;
       this.id=id;
       this.genre_name=genre_name;
       this.type_name=type_name;
       this.Path=Path;
     }
    public Integer getId(){
         return id;
    }
   public String getName(){
         return name;
    }
   public String getGenre_name(){
         return genre_name;
    }
   public String getType_name(){
         return type_name;
    }
   public String getPath(){
         return Path;
    }
   public void setName(String name){
         this.name=name;
    }
   
   public void setId(Integer name){
         this.id=id;
    }
    public void setGenre_name(String name){
         this.genre_name=name;
    }
    public void settype_name(String name){
         this.type_name=name;
    }

   public void setPath(String name){
         this.Path=name;
    }


    public void update(String message){
       DataBase db=DataBase.create();
       String sql="start transaction;";
      try{
        db.InsertFun(sql);
        sql="Delete from vote where id_song="+id+";";
        db.InsertFun(sql);
        sql="Delete from song where id="+id+";";
        db.InsertFun(sql);
        sql="commit;";
        db.InsertFun(sql);
      }catch(SQLException e){}
   }


}
