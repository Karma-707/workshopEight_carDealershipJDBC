package com.ps.dao;

import com.ps.core.Dealership;
import com.ps.core.UserInterface;
import com.ps.core.Vehicle;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DealershipDAO {
    private DataSource dataSource;

    //constructors
    public DealershipDAO() {
    }

    public DealershipDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    //CRUD methods
    public List<Dealership> getAll() {
        List<Dealership> dealerships = new ArrayList<>();

        String query = "SELECT * FROM dealerships;";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
            )
        {
            if(resultSet.next()) {
                do {
                    Dealership dealership = dealershipParser(resultSet);
                    dealerships.add(dealership);
                } while(resultSet.next());
            }
            else {
                System.out.println("🏢 No dealerships found");
            }

        } catch (SQLException e) {
            UserInterface.writeErrorsToLogsFile(e);
        }
        return dealerships;
    }

    public Dealership getById(int id) {
        String query = "SELECT * FROM dealerships WHERE DealershipID = ?;";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
            )
        {
            preparedStatement.setInt(1, id);

            try (
                    ResultSet resultSet = preparedStatement.executeQuery();
                )
            {
                if(resultSet.next()) {
                    return dealershipParser(resultSet);
                }
                else {
                    System.out.println("🏢 No dealerships found");
                }
            }
        } catch (SQLException e) {
            UserInterface.writeErrorsToLogsFile(e);
        }

        return null;
    }

    public void create(Dealership dealership) {
        String query = "INSERT INTO dealerships(Name, Address, Phone) VALUES (?, ?, ?);";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
            )
        {
            preparedStatement.setString(1, dealership.getName());
            preparedStatement.setString(2, dealership.getAddress());
            preparedStatement.setString(3, dealership.getPhone());

            int rows = preparedStatement.executeUpdate();

            if(rows == 1) {
                System.out.println("✅ Dealership successfully created!");
            }
            else {
                System.out.println("🚫 Dealership creation failed!");
            }

        } catch (SQLException e) {
            UserInterface.writeErrorsToLogsFile(e);
        }
    }

    public void update(int id, Dealership dealership) {
        String query = "UPDATE dealerships SET Name = ?, Address = ?, Phone = ? WHERE DealershipID = ?;";

        /* for only for certain values changed not all of it
        if(!dealership.getName().isEmpty() || dealership.getName() == null) {
            query += " name = ?";

        }

        query += "WHERE DealershipID = ?;";
        */

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
            )
        {
            preparedStatement.setString(1, dealership.getName());
            preparedStatement.setString(2, dealership.getAddress());
            preparedStatement.setString(3, dealership.getPhone());
            preparedStatement.setInt(4, id);

            int rows = preparedStatement.executeUpdate();

            if (rows == 1) {
                System.out.println("✅ Dealership successfully updated!");
            }
            else {
                System.out.println("🚫 Dealership update failed!");
            }
        } catch (SQLException e) {
            UserInterface.writeErrorsToLogsFile(e);
        }
    }

    public void delete(int id) {
        String query = "DELETE FROM dealerships WHERE DealershipID = ?;";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
            )
        {
            preparedStatement.setInt(1, id);

            int rows = preparedStatement.executeUpdate();

            if(rows == 1) {
                System.out.println("✅ Dealership successfully deleted!");
            }
            else {
                System.out.println("🚫 Dealership deletion failed!");
            }

        } catch (SQLException e) {
            UserInterface.writeErrorsToLogsFile(e);
        }

    }

    private Dealership dealershipParser(ResultSet resultSet) throws SQLException {
        int dealershipId = resultSet.getInt("DealershipID");
        String name = resultSet.getString("Name");
        String address = resultSet.getString("Address");
        String phone = resultSet.getString("Phone");

        return new Dealership(dealershipId, name, address, phone);
    }

}
