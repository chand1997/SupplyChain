package com.example.supplychain;

public class SignUp {
    public boolean addCustomer(String email,String password,String firstName,String mobile ){
        DatabaseConnection databaseConnection=new DatabaseConnection();
        String query=String.format("insert into customer (email,password,first_name,mobile) values ('%s','%s','%s','%s')",
                email,password,firstName,mobile);
        int rowsAffected=0;
        try{
            rowsAffected=databaseConnection.executeUpdate(query);

        }catch(Exception e){
            e.printStackTrace();
        }
        return rowsAffected!=0;

    }

}
