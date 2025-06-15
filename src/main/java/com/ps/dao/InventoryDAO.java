package com.ps.dao;

import com.ps.core.Vehicle;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAO {
    private DataSource dataSource;

    //constructors
    public InventoryDAO() {
    }

    public InventoryDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    //CRUD methods

    //get all VINs for a dealership
    public List<String> getVinByDealership(int dealershipId) {
        List<String> vins = new ArrayList<>();

        String query = "SELECT * FROM inventory WHERE DealershipID = ?;";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        )
        {
            preparedStatement.setInt(1, dealershipId);

            try (
                    ResultSet resultSet = preparedStatement.executeQuery();
            )
            {
                if(resultSet.next()) {
                    do {
                        vins.add(resultSet.getString("Vin"));
                    } while(resultSet.next());
                    return vins;
//                    return vehicleParser(resultSet);
                }
                else {
                    System.out.println("No vehicle found");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    //add vehicle to inventory
    public void create(int dealershipId, String vin) {
        String query = "INSERT INTO inventory(DealershipID, Vin) VALUES (?, ?);";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        )
        {
            preparedStatement.setInt(1, dealershipId);
            preparedStatement.setString(2, vin);

            int rows = preparedStatement.executeUpdate();

            if(rows == 1) {
                System.out.println("Inventory successfully created!");
            }
            else {
                System.out.println("Inventory creation failed!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //delete vehicle from inventory
    public void delete(int dealershipId, String vin) {
        String query = "DELETE FROM inventory WHERE DealershipID = ? AND Vin = ?;";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        )
        {
            preparedStatement.setInt(1, dealershipId);
            preparedStatement.setString(2, vin);

            int rows = preparedStatement.executeUpdate();

            if(rows == 1) {
                System.out.println("Inventory successfully deleted!");
            }
            else {
                System.out.println("Inventory deletion failed!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private Vehicle vehicleParser(ResultSet resultSet) throws SQLException {
        String vehicleId = resultSet.getString("Vin");
        int year = resultSet.getInt("Year");
        String make = resultSet.getString("Make");
        String model = resultSet.getString("Model");
        String vehicleType = resultSet.getString("VehicleType");
        String color = resultSet.getString("Color");
        int odometer = resultSet.getInt("Odometer");
        double price = resultSet.getDouble("Price");
        boolean isSold = resultSet.getBoolean("Sold");

        return new Vehicle(vehicleId, year, make, model, vehicleType, color, odometer, price, isSold);
    }


}
