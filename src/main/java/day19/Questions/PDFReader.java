package day19.Questions;

import org.springframework.stereotype.Component;

@Component
public class PDFReader implements Statement {
    @Override
    public void Read() {
        System.out.println("Pdf reader");
    }
}
