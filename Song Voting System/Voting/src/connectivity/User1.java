/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connectivity;

import observer.Observer;

/**
 *
 * @author me
 */
public class User1 implements Observer{
  Integer id;
  String name;
  String pass;
  String email;
  private static User1 user=null;
  private User1(int id,String name,String Pass,String email){
    this.id=id;
    this.name=name;
    this.pass=Pass;
    this.email=email;
   }
   public static User1 getInstance(int id,String name,String Pass,String email){
        if(user==null){
           user=new User1(id,name,Pass,email);
        }
        return user;
   }

    /**
     *
     * @param message
     */
    public  void update(String message){
       user=null;
       System.out.println("logout");
   }
    public Integer getId(){
          return id;
    }
    public String getName() {
        return name;
    }
    public String getPass() {
        return pass;
    }
    public String getEmail() {
        return email;
    }


}
