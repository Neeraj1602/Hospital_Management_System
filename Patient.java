package HospitalManagementSystem;

import java.sql.*;
import java.util.Scanner;

public class Patient {
    private Connection connection;
    private Scanner scanner;

    public Patient(Connection connection, Scanner scanner){
        this.connection = connection;
        this.scanner = scanner;
    }

    // Updated: Add patient with diagnosis
    public void addPatient(){
        System.out.print("Enter Patient Name: ");
        String name = scanner.next();
        System.out.print("Enter Patient Age: ");
        int age = scanner.nextInt();
        System.out.print("Enter Patient Gender: ");
        String gender = scanner.next();
        System.out.print("Enter Diagnosis: ");
        String diagnosis = scanner.next(); // New input for diagnosis

        try{
            String query = "INSERT INTO patients(name, age, gender, diagnosis) VALUES(?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, gender);
            preparedStatement.setString(4, diagnosis); // Storing diagnosis
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows>0){
                System.out.println("Patient Added Successfully!!");
            }else{
                System.out.println("Failed to add Patient!!");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // Updated: View patients with diagnosis
    public void viewPatientsWithDiagnosis(){
        String query = "SELECT id, name, age, gender, diagnosis FROM patients";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("\nPatients:");
            System.out.println("+------------+--------------------+----------+------------+----------------------+");
            System.out.println("| Patient Id | Name               | Age      | Gender     | Diagnosis            |");
            System.out.println("+------------+--------------------+----------+------------+----------------------+");
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                String diagnosis = resultSet.getString("diagnosis"); // Fetch diagnosis

                System.out.printf("| %-10s | %-18s | %-8s | %-10s | %-20s |\n", id, name, age, gender, diagnosis);
                System.out.println("+------------+--------------------+----------+------------+----------------------+");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean getPatientById(int id){
        String query = "SELECT * FROM patients WHERE id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}