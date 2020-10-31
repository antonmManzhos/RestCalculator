package home.org.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Table(name="Calculations")
@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class CalculatorModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    @Column (name="actions")
    private String function;
    @NonNull
    @Column (name="inputValues")
    private String input;
    @NonNull
    @Column (name="outputValues")
    private String output;

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
