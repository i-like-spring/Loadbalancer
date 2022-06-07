package team9.loadBalancer.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Reservation {

    private String name;
    private String dateOfBirth;
    private String phoneNumber;
    private String seatNumber;

    @Override
    public String toString() {
        return "Reservation{" +
                "name='" + name + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", seatNumber='" + seatNumber + '\'' +
                '}';
    }
}
