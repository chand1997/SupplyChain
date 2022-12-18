package com.example.supplychain;
import java.sql.*;

public class DatabaseConnection{
    private static final String user="root";
    private static final String password="Prabas999*";
    private static final String url="jdbc:mysql://localhost:3306/supply_chain";

    public Statement getStatement(){
        Statement statement=null;
        Connection conn;
        try{
            conn=DriverManager.getConnection(url,user,password);
            statement= conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statement;
    }

    public ResultSet getQueryTable(String query){
        Statement statement=getStatement();
        try{
            return statement.executeQuery(query);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        DatabaseConnection databaseConnection=new DatabaseConnection();
        ResultSet rs=databaseConnection.getQueryTable("select email from customer");
        try{
            while(rs.next()){
                System.out.println(rs.getString("email"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
