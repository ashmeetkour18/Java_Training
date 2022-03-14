package day19.Questions;

import day12.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Question5 {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Question5.class, args);
    }
}

@RestController
class Controller {
    @Autowired
    ProcessData processData;
}

