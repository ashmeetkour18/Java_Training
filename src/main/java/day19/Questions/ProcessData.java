package day19.Questions;

import org.springframework.stereotype.Component;

@Component
public class ProcessData implements Statement {

    @Override
    public void Read() {
        System.out.println("Process Data");
    }
}
