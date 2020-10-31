package home.org.repository;

import home.org.model.CalculatorModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CalculatorRepository extends CrudRepository<CalculatorModel, Integer>{
        List<CalculatorModel> findByFunction(String function);
}
