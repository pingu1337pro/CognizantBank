package bank.persistence;

import bank.core.Customer;
import org.h2.engine.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCQueries {

    public List<Customer> createObjects() throws SQLException {
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
}