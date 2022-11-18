package bank.persistence;

import bank.core.Customer;
import resources.PasswordHasher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    private static CustomerDAO instance = null;

    private CustomerDAO() {
    }

    public static CustomerDAO getInstance() {
        if (instance == null) {
            instance = new CustomerDAO();
        }
        return instance;
    }


    public List<Customer> getAllCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:h2:~/bank", "bank", "12345")) {
            PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM CUSTOMERS");
            ResultSet rs = selectStatement.executeQuery();
            while (rs.next()) { // will traverse through all rows
                String firstName = rs.getString("FIRSTNAME");
                String lastName = rs.getString("LASTNAME");
                String birthDate = rs.getString("BIRTHDATE");
                String email = rs.getString("EMAIL");
                String username = rs.getString("USERNAME");
                String hashedPassword = rs.getString("HASHEDPASSWORD");
                Customer customer = new Customer(firstName, lastName, birthDate, email, username, hashedPassword);
                customers.add(customer);
            }
        }
        return customers;
    }

    //Needs the hashed password to be passed in
    public Customer getCustomer(String userName, String password) throws SQLException {
        Customer selectedCustomer = null;
        try (Connection conn = DriverManager.getConnection("jdbc:h2:~/bank", "bank", "12345")) {
            PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM CUSTOMERS WHERE USERNAME = " + userName + " AND HASHEDPASSWORD = " + password);
            ResultSet rs = selectStatement.executeQuery();

            String firstName = rs.getString("FIRSTNAME");
            String lastName = rs.getString("LASTNAME");
            String birthDate = rs.getString("BIRTHDATE");
            String email = rs.getString("EMAIL");
            String username = rs.getString("USERNAME");
            String hashedPassword = rs.getString("HASHEDPASSWORD");
            selectedCustomer = new Customer(firstName, lastName, birthDate, email, username, hashedPassword);
        }
        return selectedCustomer;
    }

    public boolean writeCustomer(Customer customer) throws SQLException {
        try (Connection conn = DriverManager.getConnection("jdbc:h2:~/bank", "bank", "12345")) {
            PreparedStatement insertStatement = conn.prepareStatement("INSERT INTO CUSTOMERS (FIRSTNAME, LASTNAME, BIRTHDATE, EMAIL, USERNAME, HASHEDPASSWORD) VALUES (?, ?, ?, ?, ?, ?)");
            insertStatement.setString(1, customer.getFirstName());
            insertStatement.setString(2, customer.getLastName());
            insertStatement.setString(3, customer.getBirthDate());
            insertStatement.setString(4, customer.getEMail());
            insertStatement.setString(5, customer.getUserName());
            insertStatement.setString(6, customer.getStoredPassword());
            insertStatement.executeUpdate();
            return true;
        }

    }

    public int getCustomerID(Customer customer) throws SQLException {
        String userName = customer.getUserName();
        int customerID = 0;
        try (Connection conn = DriverManager.getConnection("jdbc:h2:~/bank", "bank", "12345")) {
            PreparedStatement selectStatement = conn.prepareStatement("SELECT CUSTOMERID FROM CUSTOMERS WHERE USERNAME = " + userName);
            ResultSet rs = selectStatement.executeQuery();
            customerID = rs.getInt("ID");
        }
        return customerID;
    }


}