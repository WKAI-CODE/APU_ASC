package apu_asc.model;

public class Technician extends Staff {

    public Technician(
            String userID,
            String username,
            String password,
            String name,
            String phoneNumber,
            int age,
            String identityNumber,
            String email,
            String address) {

        super(
                userID,
                username,
                password,
                name,
                phoneNumber,
                "Technician",
                age,
                identityNumber,
                email,
                address
        );
    }
}