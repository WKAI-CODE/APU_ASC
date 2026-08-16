/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apu_asc.model;

/**
 *
 * @author User
 */
public class Customer extends User {
    
    public Customer(
            String userID,
            String username,
            String password,
            String name,
            String phoneNumber) {
        
        super(userID, username, password, name, phoneNumber, "CUSTOMER");
    }
    
}
