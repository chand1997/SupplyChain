package com.example.supplychain;

public class Order {
    public static boolean placeOrder(String customerEmail,Product product){
        DatabaseConnection databaseConnection=new DatabaseConnection();
        String query=String.format("insert into orders (customer_id,product_id) " +
                "values ((select customerId from customer where email='%s'),%s)",customerEmail,product.getId());
        int rowAffected=0;
        try{
            rowAffected=databaseConnection.executeUpdate(query);

        }catch(Exception e){
            e.printStackTrace();
        }
        return rowAffected!=0;
    }
}
