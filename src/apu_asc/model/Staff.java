/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apu_asc.model;

/**
 *
 * @author User
 */
public class Staff extends User {
    
    private int age;
    private String identityNumber;
    private String email;
    private String address;
    
    public Staff(
            String userID,
            String username,
            String password,
            String name,
            String phoneNumber,
            String role,
            int age,
            String identityNumber,
            String email,
            String address) {
        
        super(userID, username, password, name, phoneNumber, role);
        
        this.age = age;
        this.identityNumber = identityNumber;
        this.email = email;
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}
