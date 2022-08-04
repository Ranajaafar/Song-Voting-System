/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connectivity;
import java.io.*;
import java.sql.*;
import java.util.*;

public class DataBase {
   private static DataBase db=null;
   private ResultSet resultat;
   private Statement instruction ;
   private DataBase(){}
   public static DataBase create(){
     if(db==null){
         String dbName="voting";
         String username="phpUser";
         String password="USER_1235";
        
         String dbUrl="jdbc:mysql://localhost:3306/"+dbName;
         
	   try{
	     db=new DataBase();
	     Class.forName("com.mysql.cj.jdbc.Driver");
             Connection conn= DriverManager.getConnection(dbUrl,username,password);
             System.out.println("Connection established successfully !");
 	     db.instruction = conn.createStatement();
          }
    	catch (Exception ex) {
	           System.out.println("Echec de chargement du driver" );
                   ex.printStackTrace();
	          // return null;
	  	}
    }
	return db;
   }

   public ResultSet SelectFun(String Query) throws SQLException{
      resultat = instruction.executeQuery(Query);	
       return resultat;
   }

   public void InsertFun(String Query) throws SQLException{
     instruction.executeUpdate(Query);
   }
/**
 *
 * @author me
 */    
}
