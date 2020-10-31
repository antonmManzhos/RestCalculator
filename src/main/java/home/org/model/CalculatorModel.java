package home.org.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Table(name="Calculations")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class CalculatorModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name="actions")
    private String function;

    @Column (name="inputValues")
    private String input;

    @Column (name="outputValues")
    private String output;

    public CalculatorModel(String function, String input, String output) {
        this.function = function;
        this.input = input;
        this.output = output;
    }

    @Override
    public String toString() {
        return "CalculatorModel{" +
                "id=" + id +
                ", function" + function + '\'' +
                ", input='" + input + '\'' +
                ", output='" + output + '\'' +
                '}';
    }
}
