package com.ps.dao;

import com.ps.core.Dealership;
import com.ps.core.Vehicle;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehiclesDAO {
    private DataSource dataSource;

    //constructors
    public VehiclesDAO() {
    }

    public VehiclesDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    //filter by price
    public List<Vehicle> getVehiclesByPrice(double min, double max) {
        List<Vehicle> filteredVehicles = new ArrayList<>();

        String query = "SELECT * FROM vehicles WHERE Price BETWEEN ? AND ?;";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setDouble(1, min);
            preparedStatement.setDouble(2, max);

            try (
                    ResultSet resultSet = preparedStatement.executeQuery()
                )
            {
                while (resultSet.next()) {
                    filteredVehicles.add(vehicleParser(resultSet));
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return filteredVehicles;
    }

    //CRUD methods
    public List<Vehicle> getAll() {
        List<Vehicle> vehicles = new ArrayList<>();

        String query = "SELECT * FROM vehicles;";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
            )
        {
            if(resultSet.next()) {
                do {
                    Vehicle vehicle = vehicleParser(resultSet);
                    vehicles.add(vehicle);
                } while(resultSet.next());
            }
            else {
                System.out.println("No vehicles found  ");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vehicles;
    }

    public Vehicle getByVin(String vin) {
        String query = "SELECT * FROM vehicles WHERE Vin = ?;";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        )
        {
            preparedStatement.setString(1, vin);

            try (
                    ResultSet resultSet = preparedStatement.executeQuery();
                )
            {
                if(resultSet.next()) {
                    return vehicleParser(resultSet);
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

    public void create(Vehicle vehicle) {
        String query = "INSERT INTO vehicles(Year, Make, Model, VehicleType, Color, Odometer, Price, Sold) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        )
        {
            preparedStatement.setInt(1, vehicle.getYear());
            preparedStatement.setString(2, vehicle.getMake());
            preparedStatement.setString(3, vehicle.getModel());
            preparedStatement.setString(4, vehicle.getVehicleType());
            preparedStatement.setString(5, vehicle.getColor());
            preparedStatement.setInt(6, vehicle.getOdometer());
            preparedStatement.setDouble(7, vehicle.getPrice());
            preparedStatement.setBoolean(8, vehicle.isSold());

            int rows = preparedStatement.executeUpdate();

            if(rows == 1) {
                System.out.println("Vehicle successfully created!");
            }
            else {
                System.out.println("Vehicle creation failed!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(String vin, Vehicle vehicle) {
        String query = "UPDATE vehicles SET Year = ?, Make = ?, Model = ?, VehicleType = ?, Color = ?, Odometer = ?, Price = ?, Sold = ? WHERE Vin = ?;";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        )
        {
            preparedStatement.setInt(1, vehicle.getYear());
            preparedStatement.setString(2, vehicle.getMake());
            preparedStatement.setString(3, vehicle.getModel());
            preparedStatement.setString(4, vehicle.getVehicleType());
            preparedStatement.setString(5, vehicle.getColor());
            preparedStatement.setInt(6, vehicle.getOdometer());
            preparedStatement.setDouble(7, vehicle.getPrice());
            preparedStatement.setBoolean(8, vehicle.isSold());
            preparedStatement.setString(8, vehicle.getVin());

            int rows = preparedStatement.executeUpdate();

            if (rows == 1) {
                System.out.println("Vehicle successfully updated!");
            }
            else {
                System.out.println("Vehicle update failed!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(String vin) {
        String query = "DELETE FROM vehicles WHERE Vin = ?;";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        )
        {
            preparedStatement.setString(1, vin);

            int rows = preparedStatement.executeUpdate();

            if(rows == 1) {
                System.out.println("Vehicle successfully deleted!");
            }
            else {
                System.out.println("Vehicle deletion failed!");
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
