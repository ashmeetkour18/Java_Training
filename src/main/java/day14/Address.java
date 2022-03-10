package day14;

import org.springframework.stereotype.Component;

@Component
public class Address {
    private final String houseNo;

    public Address() {
        houseNo = "123";
    }

    public String getHouseNo() {
        return houseNo;
    }
}
