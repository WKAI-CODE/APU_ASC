package apu_asc.model;

public class Manager extends Staff {

    public Manager(
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
                "Manager",
                age,
                identityNumber,
                email,
                address
        );
    }
}