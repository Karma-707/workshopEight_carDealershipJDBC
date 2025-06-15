package com.ps.dao;

import com.ps.core.UserInterface;
import com.ps.core.Vehicle;
import com.ps.core.contract.LeaseContract;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LeaseContractDAO {
    private DataSource dataSource;

    //constructors
    public LeaseContractDAO() {
    }

    public LeaseContractDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    //CRUD methods
    public List<LeaseContract> getAll() {
        List<LeaseContract> leaseContracts = new ArrayList<>();

        String query = "SELECT * FROM leasecontracts;";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
        )
        {
            if(resultSet.next()) {
                do {
                    LeaseContract leaseContract = leaseContractParser(resultSet);
                    leaseContracts.add(leaseContract);
                } while(resultSet.next());
            }
            else {
                System.out.println("📭 No lease contract found");
            }

        } catch (SQLException e) {
            UserInterface.writeErrorsToLogsFile(e);
        }
        return leaseContracts;
    }

    //get by leaseContractId
    public LeaseContract getById(int id) {
        String query = "SELECT * FROM leasecontracts WHERE LeaseContractID = ?;";

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
                    return leaseContractParser(resultSet);
                }
                else {
                    System.out.println("📭 No lease contract found");
                }
            }
        } catch (SQLException e) {
            UserInterface.writeErrorsToLogsFile(e);
        }

        return null;
    }

    public void create(LeaseContract leaseContract) {
        String query = "INSERT INTO leasecontracts (LeaseContractID, Vin, LeaseName, CustomerEmail, LeaseStart, LeaseEnd, ExpectedEndingValue, LeaseFee, MonthlyPayment) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
//        String query = "INSERT INTO LeaseContracts (LeaseContractID, Vin, LeaseName, CustomerEmail, LeaseEnd) VALUES (?, ?, ?, ?, ?);";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        )
        {
            preparedStatement.setInt(1, leaseContract.getLeaseContractId());
            preparedStatement.setString(2, leaseContract.getVehicleChosen().getVin());
            preparedStatement.setString(3, leaseContract.getCustomerName());
            preparedStatement.setString(4, leaseContract.getCustomerEmail());
            preparedStatement.setDate(5, Date.valueOf(leaseContract.getLeaseStart()));
            preparedStatement.setDate(6, Date.valueOf(leaseContract.getLeaseEnd()));
            preparedStatement.setDouble(7, leaseContract.getExpectedEndingValue());
            preparedStatement.setDouble(8, leaseContract.getLeaseFee());
            preparedStatement.setDouble(9, leaseContract.getMonthlyPayment());

            int rows = preparedStatement.executeUpdate();

            if(rows == 1) {
                System.out.println("✅ Lease contract successfully created!");
            }
            else {
                System.out.println("🚫 Lease contract creation failed!");
            }

        } catch (SQLException e) {
            UserInterface.writeErrorsToLogsFile(e);
        }
    }

    public void update(int id, LeaseContract leaseContract) {
//        String query = "UPDATE leasecontracts SET  Vin = ?, LeaseName= ?, CustomerEmail= ?, LeaseStart= ?, LeaseEnd = ?, WHERE LeaseContractID = ?;";
        String query = "UPDATE leasecontracts SET Vin = ?, LeaseName = ?, CustomerEmail = ?, LeaseStart = ?, LeaseEnd = ?, ExpectedEndingValue = ?, LeaseFee = ?, MonthlyPayment = ? WHERE LeaseContractID = ?;";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        )
        {
            preparedStatement.setString(1, leaseContract.getVehicleChosen().getVin());
            preparedStatement.setString(2, leaseContract.getCustomerName());
            preparedStatement.setString(3, leaseContract.getCustomerEmail());
            preparedStatement.setDate(4, Date.valueOf(leaseContract.getLeaseStart()));
            preparedStatement.setDate(5, Date.valueOf(leaseContract.getLeaseEnd()));
            preparedStatement.setDouble(6, leaseContract.getExpectedEndingValue());
            preparedStatement.setDouble(7, leaseContract.getLeaseFee());
            preparedStatement.setDouble(8, leaseContract.getMonthlyPayment());
            preparedStatement.setInt(9, id);

            int rows = preparedStatement.executeUpdate();

            if (rows == 1) {
                System.out.println("✅ Lease contract successfully updated!");
            }
            else {
                System.out.println("🚫 Lease contract update failed!");
            }
        } catch (SQLException e) {
            UserInterface.writeErrorsToLogsFile(e);
        }
    }

    public void delete(int id) {
        String query = "DELETE FROM leasecontracts WHERE LeaseContractID = ?;";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        )
        {
            preparedStatement.setInt(1, id);

            int rows = preparedStatement.executeUpdate();

            if(rows == 1) {
                System.out.println("✅ Lease contract successfully deleted!");
            }
            else {
                System.out.println("🚫 Lease contract deletion failed!");
            }

        } catch (SQLException e) {
            UserInterface.writeErrorsToLogsFile(e);
        }

    }

    private LeaseContract leaseContractParser(ResultSet resultSet) throws SQLException {
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


        int leaseContractId = resultSet.getInt("LeaseContractID");
        String leaseContractVin = resultSet.getString("Vin");
        String leaseName = resultSet.getString("LeaseName");
        String customerEmail = resultSet.getString("CustomerEmail");
        LocalDate leaseStart = resultSet.getDate("LeaseStart").toLocalDate();
        LocalDate leaseEnd = resultSet.getDate("LeaseEnd").toLocalDate();
        double expectedEndingValue = resultSet.getDouble("ExpectedEndingValue");
        double leaseFee = resultSet.getDouble("LeaseFee");
        double monthlyPayment = resultSet.getDouble("MonthlyPayment");

        return new LeaseContract(leaseContractId, leaseName, customerEmail, vehicle,
                leaseStart, leaseEnd, expectedEndingValue, leaseFee, monthlyPayment);
    }

}
