package day14;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/*
(i) XML
(ii) @Configuration , @Bean
(iii) @Component
 */
@SpringBootApplication
public class MainDriver {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MainDriver.class, args);
        Address address = context.getBean(Address.class);
        System.out.println(address.getHouseNo());
        Person person = context.getBean(Person.class);
        System.out.println(person.getName());
        System.out.println(person.getAddress().getHouseNo());


    }
}