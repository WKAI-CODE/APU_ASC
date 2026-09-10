package apu_asc.model;

public class CounterStaff extends Staff {

    public CounterStaff(
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
                "CounterStaff",
                age,
                identityNumber,
                email,
                address
        );
    }
}