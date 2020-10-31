package home.org.controllers;

import home.org.model.CalculatorModel;
import home.org.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@EnableAutoConfiguration
@Component
@RequestMapping("/calculator")
public class HistoryController {
    @Autowired
    CalculatorService calculatorService;

    @RequestMapping(value="/history", method = RequestMethod.GET)
    public List<CalculatorModel> getAllRecords(){
        return calculatorService.getAllRecords();
    }

    @RequestMapping(value="/history/{id}", method = RequestMethod.GET)
    public Optional<CalculatorModel> getRecordById(@PathVariable(value="id") int id){
        return calculatorService.getRecordById(id);
    }

    @RequestMapping(value="/history/delete/{id}", method = RequestMethod.DELETE)
    public void deleteRecordByID(@PathVariable(value="id") int id){
        calculatorService.deleteRecordById(id);
    }

    @RequestMapping(value="/history/update/{id}", method = RequestMethod.PUT)
    public void updateRecordByID(@PathVariable(value="id") int id,
                                 @RequestBody CalculatorModel calculatorModel){
        calculatorService.updateRecordById(id, calculatorModel);
    }

    @RequestMapping(value="/history/actions/{action}", method = RequestMethod.GET)
    public List<CalculatorModel> getRecordByAction(@PathVariable(value="action") String action){
        return calculatorService.findRecordByFunction(action);
    }
}
