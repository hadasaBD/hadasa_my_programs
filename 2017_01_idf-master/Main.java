package com.company;

import java.io.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main {

    //public static final String HOST = "10.0.11.49";


    public static void main(String[] args) {

        /*System.out.println("please enter company name:");
        String companyName = getInputFromUser();

        try(Connection conn = DB.getConnection()){
            PreparedStatement statement = conn.prepareStatement("SELECT CUSTOMER_ID,COMPANY_NAME,CONTACT_NAME FROM CUSTOMERS WHERE COMPANY_NAME = ?");
            statement.setString(1, companyName);
            try(ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()){
                    String customerId = resultSet.getString(1);
                    //String companyName = resultSet.getString(2);
                    String contactName = resultSet.getString(3);
                    System.out.println(customerId + " " + companyName + " " + contactName);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
*/

        /*try(Sapir sapir = new Sapir()){

        } catch (IOException e) {
            e.printStackTrace();
        }*/
        customerWithHighestOrderSum2();
    }

    public static void customerWithHighestOrderSum1(){
        try(Connection conn = DB.getConnection()){
            PreparedStatement statementOrderDetails = conn.prepareStatement("SELECT ORDER_ID,UNIT_PRICE,QUANTITY,DISCOUNT FROM ORDER_DETAILS ORDER BY ORDER_ID");
            int maxOrderId = -1;
            double maxTotal = -1.0;
            try(ResultSet resultSet = statementOrderDetails.executeQuery()) {
                int currentOrderId = -1;
                double totalPerOrder = 0;
                while(resultSet.next()){
                    int orderId = resultSet.getInt(1);
                    double unitPrice = resultSet.getDouble(2);
                    int quantity = resultSet.getInt(3);
                    double discount = resultSet.getDouble(4);
                    double total = unitPrice * quantity * (1.0 - discount);
                    if(orderId != currentOrderId){
                        if(totalPerOrder > maxTotal){
                            maxTotal = totalPerOrder;
                            maxOrderId = currentOrderId;
                        }
                        currentOrderId = orderId;
                        totalPerOrder = 0;
                    }
                    totalPerOrder += total;
                }



            }
            PreparedStatement statementOrders = conn.prepareStatement("SELECT ORDER_ID, CUSTOMER_ID FROM ORDERS");
            int maxCustomerId = -1;
            try(ResultSet resultSet = statementOrders.executeQuery()){
                while(resultSet.next()){
                    int orderId = resultSet.getInt(1);
                    if(orderId == maxOrderId){
                        maxCustomerId = resultSet.getInt(2);
                        break;
                    }
                }
            }
            PreparedStatement statementCustomers = conn.prepareStatement("SELECT CUSTOMER_ID,COMPANY_NAME FROM CUSTOMERS");
            String customerName = "";
            try(ResultSet resultSet = statementCustomers.executeQuery()){
                while(resultSet.next()){
                    int customerId = resultSet.getInt(1);
                    if(customerId == maxCustomerId){
                        customerName = resultSet.getString(2);
                        break;
                    }
                }
            }
            System.out.println(customerName+ " " + maxTotal);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static class Order{
        int customerId;
        double total;
        public Order(int customerId) {
            this.customerId = customerId;
            total = 0;
        }
    }

    static class Customer{
        int customerId;
        String customerName;
        double total;

        public Customer(String customerName, int customerId) {
            this.customerName = customerName;
            this.customerId = customerId;
        }
    }

    public static void customerWithHighestOrderSum2(){
        try(Connection conn = DB.getConnection()) {
            PreparedStatement statementOrders = conn.prepareStatement("SELECT ORDER_ID, CUSTOMER_ID FROM ORDERS");
            Map<Integer, Order> orders = new HashMap<>();
            try(ResultSet resultSet = statementOrders.executeQuery()){
                while(resultSet.next()){
                    orders.put(resultSet.getInt(1), new Order(resultSet.getInt(2)));
                }
            }


            PreparedStatement statementOrderDetails = conn.prepareStatement("SELECT ORDER_ID,UNIT_PRICE,QUANTITY,DISCOUNT FROM ORDER_DETAILS");
            try(ResultSet resultSet = statementOrderDetails.executeQuery()) {
                while(resultSet.next()){
                    int orderId = resultSet.getInt(1);
                    double unitPrice = resultSet.getDouble(2);
                    int quantity = resultSet.getInt(3);
                    double discount = resultSet.getDouble(4);
                    double total = unitPrice * quantity * (1.0 - discount);
                    Order order = orders.get(orderId);
                    order.total += total;
                }
            }

            PreparedStatement statementCustomers = conn.prepareStatement("SELECT CUSTOMER_ID,COMPANY_NAME FROM CUSTOMERS");
            Map<Integer, Customer> customers = new HashMap<>();
            try(ResultSet resultSet = statementCustomers.executeQuery()){
                while(resultSet.next()){
                    int customerId = resultSet.getInt(1);
                    customers.put(customerId, new Customer(resultSet.getString(2), customerId));
                }
            }
            Set<Integer> orderIds = orders.keySet();

            for(Integer orderId : orderIds){
                Order o = orders.get(orderId);
                customers.get(o.customerId).total += o.total;
            }
            Set<Integer> customerIds = customers.keySet();
            double max = -1;
            Customer maxCustomer = null;
            for(Integer customerId : customerIds){
                Customer c = customers.get(customerId);
                if(c.total > max){
                    maxCustomer = c;
                    max = c.total;
                }
            }
            System.out.println(maxCustomer.customerName + " " + maxCustomer.total);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static String getInputFromUser(){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        try {
            line = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }
}
/*
class Sapir implements Closeable{

    @Override
    public void close() throws IOException {
        System.out.println("in close of Sapir");
    }
}*/
