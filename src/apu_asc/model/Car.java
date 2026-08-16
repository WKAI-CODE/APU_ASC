/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apu_asc.model;

public class Car {
    private String carID;
    private String customerID;
    private String registrationNumber;
    private String brand;
    private String model;
    private int year;
    private String colour;
    
    public Car(
            String carID,
            String customerID,
            String registrationNumber,
            String brand,
            String model,
            int year,
             String colour) {
        
        this.carID = carID;
        this.customerID = customerID;
        this.registrationNumber = registrationNumber;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.colour = colour;
    }

    public String getCarID() {
        return carID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getColour() {
        return colour;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
    
}
