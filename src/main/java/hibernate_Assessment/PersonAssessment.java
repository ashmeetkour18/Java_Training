package hibernate_Assessment;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Assessment_person")
public class PersonAssessment {
    @Id
    private int id;
    private String name;

    @Override
    public String toString() {
        return "PersonAssessment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public PersonAssessment(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public PersonAssessment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}