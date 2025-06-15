package com.ps.dao;

import com.ps.core.Vehicle;
import com.ps.core.contract.LeaseContract;
import com.ps.core.contract.SalesContract;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SalesContractDAO {
    private DataSource dataSource;

    //constructors
    public SalesContractDAO() {
    }

    public SalesContractDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    //CRUD methods
    public List<SalesContract> getAll() {
        List<SalesContract> salesContracts = new ArrayList<>();

        String query = "SELECT * FROM salescontracts;";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
        )
        {
            if(resultSet.next()) {
                do {
                    SalesContract salescontract = salesContractParser(resultSet);
                    salesContracts.add(salescontract);
                } while(resultSet.next());
            }
            else {
                System.out.println("No sales contract found  ");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return salesContracts;
    }

    //get by leaseContractId
    public SalesContract getById(int id) {
        String query = "SELECT * FROM salescontracts WHERE SalesContractID = ?;";

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
                    return salesContractParser(resultSet);
                }
                else {
                    System.out.println("No sales contract found");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public void create(SalesContract salesContract) {
        String query = "INSERT INTO salescontracts (SalesContractID, Vin, BuyerName, CustomerEmail, SalePrice, SaleDate, RecordingFee, ProcessingFee, IsFinanced) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        )
        {
            preparedStatement.setInt(1, salesContract.getSalesContractId());
            preparedStatement.setString(2, salesContract.getVehicleChosen().getVin());
            preparedStatement.setString(3, salesContract.getCustomerName());
            preparedStatement.setString(4, salesContract.getCustomerEmail());
            preparedStatement.setDouble(5, salesContract.getSalePrice());
            preparedStatement.setDate(6, Date.valueOf(salesContract.getDate()));
            preparedStatement.setDouble(7, salesContract.getRecordingFee());
            preparedStatement.setDouble(8, salesContract.getProcessingFee());
            preparedStatement.setBoolean(9, salesContract.isFinanced());

            int rows = preparedStatement.executeUpdate();

            if(rows == 1) {
                System.out.println("Sale contract successfully created!");
            }
            else {
                System.out.println("Sale contract creation failed!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(int id, SalesContract salesContract) {
        String query = "UPDATE salescontracts SET Vin = ?, BuyerName = ?, CustomerEmail = ?, SalePrice = ?, SaleDate = ?, RecordingFee = ?, ProcessingFee = ?, IsFinanced = ? WHERE SalesContractID = ?;";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        )
        {
            preparedStatement.setString(1, salesContract.getVehicleChosen().getVin());
            preparedStatement.setString(2, salesContract.getCustomerName());
            preparedStatement.setString(3, salesContract.getCustomerEmail());
            preparedStatement.setDouble(4, salesContract.getSalePrice());
            preparedStatement.setDate(5, Date.valueOf(salesContract.getDate()));
            preparedStatement.setDouble(6, salesContract.getRecordingFee());
            preparedStatement.setDouble(7, salesContract.getProcessingFee());
            preparedStatement.setBoolean(8, salesContract.isFinanced());
            preparedStatement.setInt(9, id);


            int rows = preparedStatement.executeUpdate();

            if (rows == 1) {
                System.out.println("Sale contract successfully updated!");
            }
            else {
                System.out.println("Sale contract update failed!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        String query = "DELETE FROM salescontracts WHERE SalesContractID = ?;";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        )
        {
            preparedStatement.setInt(1, id);

            int rows = preparedStatement.executeUpdate();

            if(rows == 1) {
                System.out.println("Sale contract successfully deleted!");
            }
            else {
                System.out.println("Sale contract deletion failed!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private SalesContract salesContractParser(ResultSet resultSet) throws SQLException {
        // Vehicle fields (join with Vehicles table)
        String vin = resultSet.getString("Vin");
        int year = resultSet.getInt("Year");
        String make = resultSet.getString("Make");
        String model = resultSet.getString("Model");
        String vehicleType = resultSet.getString("VehicleType");
        String color = resultSet.getString("Color");
        int odometer = resultSet.getInt("Odometer");
        double price = resultSet.getDouble("Price");
        boolean isSold = resultSet.getBoolean("Sold");

        Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price, isSold);


        int salesContractId = resultSet.getInt("SalesContractID");
        String buyerName = resultSet.getString("BuyerName");
        String customerEmail = resultSet.getString("CustomerEmail");
        double salePrice = resultSet.getDouble("SalePrice");
        LocalDate saleDate = resultSet.getDate("SaleDate").toLocalDate();
        double recordingFee = resultSet.getDouble("RecordingFee");
        double processingFee = resultSet.getDouble("ProcessingFee");
        boolean isFinanced = resultSet.getBoolean("IsFinanced");

        return new SalesContract(salesContractId, saleDate, buyerName, customerEmail,
                vehicle, salePrice, recordingFee, processingFee, isFinanced);

    }

}
