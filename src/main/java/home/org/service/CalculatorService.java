package home.org.service;

import home.org.model.CalculatorModel;
import home.org.repository.CalculatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CalculatorService {
    @Autowired
    private CalculatorRepository calculatorRepository;

    public void addAction(CalculatorModel calculatorModel){
        calculatorRepository.save(calculatorModel);
    }

    public List<CalculatorModel> getAllRecords(){
        List<CalculatorModel> records = new ArrayList<>();
        calculatorRepository.findAll().forEach(records::add);
        return records;
    }

    public Optional<CalculatorModel> getRecordById(int id) {
        return calculatorRepository.findById(id);
    }

    public void deleteRecordById(int id){
        calculatorRepository.deleteById(id);
    }

    public void updateRecordById(int id, CalculatorModel calculatorModel){
        calculatorRepository.save(calculatorModel);
    }

    public List<CalculatorModel>findRecordByFunction(String action){
        List<CalculatorModel>records = new ArrayList<>();
        calculatorRepository.findByFunction(action).forEach(records::add);
        return records;
    }
}
