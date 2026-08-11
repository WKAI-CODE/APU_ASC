/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apu_asc.model;

/**
 *
 * @author User
 */
public class User {
    
    private String userID;
    private String username;
    private String password;
    private String name;
    private String phoneNumber;
    private String role;

    public User(String userID, String username, String password, String name, String phoneNumber, String role) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }
    
    public String getName() {
        return name;
    }
}
