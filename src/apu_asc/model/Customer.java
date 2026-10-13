package apu_asc.model;

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