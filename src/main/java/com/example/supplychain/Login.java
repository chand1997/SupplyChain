package com.example.supplychain;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.ResultSet;

public class Login{
//    this method gives hashcode to the input string
    private byte[] getSHA(String input){
        try{
            MessageDigest messageDigest=MessageDigest.getInstance("SHA-256");
            return messageDigest.digest(input.getBytes(StandardCharsets.UTF_8));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }
//    this converts hashcode to Hex_string (string containing NUMBERS 0-9 and CHARS  a-f).
    private String getEncryptedPassword(String password) {
        try {
            BigInteger hashCodeNumber = new BigInteger(1, getSHA(password));
            StringBuilder hexString = new StringBuilder(hashCodeNumber.toString(16));
            return hexString.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean customerLogin(String mail, String pass){
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

//    public static void main(String[] args) {
//        Login l=new Login();
//        System.out.println(l.customerLogin("chan@gmail.com","chan123"));
//
//    }
//public static void main(String[] args) {
//    Login l=new Login();
//    System.out.println(l.getEncryptedPassword("juhvjjkhkjjjhv"));
//
//}

}
