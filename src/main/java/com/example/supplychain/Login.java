package com.example.supplychain;

import java.sql.Connection;
import java.sql.ResultSet;

public class Login{

    public boolean login(String mail,String pass){
        String query=String.format("select * from customer where email='%s' and password='%s'", mail,pass);
        try{
            DatabaseConnection db=new DatabaseConnection();
            ResultSet rs=db.getQueryTable(query);
            while(rs!=null && rs.next()){
                return true;
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        Login l=new Login();
        System.out.println(l.login("chan@gmail.com","chan123"));

    }

}
