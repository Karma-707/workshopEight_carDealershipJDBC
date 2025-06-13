package com.ps;

import com.ps.core.Dealership;
import com.ps.core.UserInterface;
import com.ps.dao.DealershipDAO;
import org.apache.commons.dbcp2.BasicDataSource;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        if(args.length < 2) {
            System.out.println("Give me args!!!");
            System.exit(1);
        }

        BasicDataSource basicDataSouce = new BasicDataSource();
        basicDataSouce.setUrl("jdbc:mysql://localhost:3306/cardealershipdatabase");
        basicDataSouce.setUsername(args[0]);
        basicDataSouce.setPassword(args[1]);
//        DealershipDAO dealershipDAO = new DealershipDAO(basicDataSouce);

        UserInterface userInterface = new UserInterface(basicDataSouce);
        userInterface.display();
//
//        int mainCommand;
//
//        do {
//            System.out.println("1 --> Get All");
//            System.out.println("2 --> Get by Id");
//            System.out.println("3 --> Create dealership");
//            System.out.println("4 --> Update dealership");
//            System.out.println("5 --> Delete dealership");
//            System.out.print("Enter command: ");
//            mainCommand = scanner.nextInt();
//
//            switch (mainCommand) {
//                case 1:
//                    List<Dealership> dealerships = dealershipDAO.getAll();
//                    System.out.println(dealerships);
//                    break;
//                case 2:
//                    System.out.print("Plz enter dealership id: ");
//                    int id = scanner.nextInt();
//
//                    Dealership dealership = dealershipDAO.getById(id);
//                    System.out.println(dealership);
//                    break;
//                case 3:
//                    System.out.println("Please provide the following");
//                    System.out.print("ID: ");
//                    int dealershipId = scanner.nextInt();
//                    scanner.nextLine();
//                    System.out.print("Name: ");
//                    String dealershipName = scanner.nextLine();
//                    System.out.print("Address: ");
//                    String dealershipAddress = scanner.nextLine();
//                    System.out.println("Phone");
//                    String dealershipPhone =  scanner.nextLine();
//
//                    Dealership dealershipToCreate = new Dealership(dealershipId, dealershipName, dealershipAddress, dealershipPhone);
//
//                    dealershipDAO.create(dealershipToCreate);
//                    break;
//                case 4:
//                    System.out.print("Please provide the dealership id to update: ");
//                    int dealershipIdToUpdate = scanner.nextInt();
//                    scanner.nextLine();
//
//                    System.out.println("Please provide the information to update");
//                    System.out.print("Name: ");
//                    String dealershipNameToUpdate = scanner.nextLine();
//
//                    System.out.print("Address: ");
//                    String dealershipAddressToUpdate = scanner.nextLine();
//
//                    System.out.print("Phone");
//                    String dealershipPhoneToUpdate =  scanner.nextLine();
//
//                    Dealership dealershipToUpdate = new Dealership(dealershipIdToUpdate, dealershipNameToUpdate, dealershipAddressToUpdate, dealershipPhoneToUpdate);
//
//                    dealershipDAO.update(dealershipIdToUpdate, dealershipToUpdate);
//                    break;
//                case 5:
//                    System.out.print("Please provide the country code to delete: ");
//                    int dealershipIdToDelete = scanner.nextInt();
//
//                    dealershipDAO.delete(dealershipIdToDelete);
//                    break;
//            }
//
//
//
//
//        } while(mainCommand != 0);










    }
}