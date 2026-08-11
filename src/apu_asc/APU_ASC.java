/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package apu_asc;
import apu_asc.model.User;

/**
 *
 * @author User
 */
public class APU_ASC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("APU Automotive Service Centre");
        
        User user1 = new User(
                "C001",
                "aisha01",
                "pass123",
                "Aisha",
                "0123456789",
                "CUSTOMER"
        );
    
    System.out.println("Customer name: " + user1.getName());
    }
    
}
