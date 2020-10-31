package home.org.controllers;

import home.org.model.CalculatorModel;
import home.org.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@EnableAutoConfiguration
@Component
@RequestMapping("/calculator")
public class CalculatorController {
    @Autowired
    private CalculatorService calculatorService;

    CalculatorModel calculatorModel;

    String emptyOrNotAnInteger = "Incorrect input value: empty or not an integer number";

    ///calculator/power/
    @RequestMapping(value="/power", method = RequestMethod.POST)
    //calculator/power/base=2&exp=3
    public CalculatorModel pow(@RequestParam(value="base") String base,
                               @RequestParam(value="exp") String exp){
        List<String> input = new ArrayList<>();
        try {
            double n = Double.parseDouble(base);
            double e = Double.parseDouble(exp);
        }
        catch (NumberFormatException ex){
            input.add(base);
            input.add(exp);
            calculatorModel = new CalculatorModel("power error",
                    input.toString(),
                    "Base or exponent should be numeric values");
            calculatorService.addAction(calculatorModel);
            return calculatorModel;
        }
        input.add(base);
        input.add(exp);
        String result = String.valueOf(Math.pow(Double.valueOf(base), Double.valueOf(exp)));

        calculatorModel = new CalculatorModel("power",
                input.toString(),
                result);
        calculatorService.addAction(calculatorModel);
        return calculatorModel;

    }

    //calculator/sqrt/25
    @RequestMapping(value="/sqrt", method = RequestMethod.POST)
    public CalculatorModel sqrt(@RequestParam(value="value") String value){
        String input = "";
        double parsedValue;
        try{
            parsedValue = Double.valueOf(value);
        }
        catch (NumberFormatException nfe){
            input = value;
            calculatorModel = new CalculatorModel("sqrt error",
                    input,
                    "Incorrect input value: empty or not a number");
            calculatorService.addAction(calculatorModel);
            return calculatorModel;
        }
        if (parsedValue<0){
            input = value;
            calculatorModel = new CalculatorModel("sqrt error",
                    input,
                    "Incorrect input value: should be greater than 0");
            calculatorService.addAction(calculatorModel);
            return calculatorModel;
        }
        input = value;
        String output = String.valueOf(Math.sqrt(Double.valueOf(value)));
        calculatorModel = new CalculatorModel("sqrt",
                input,
                output);
        calculatorService.addAction(calculatorModel);
        return calculatorModel;
    }

    //calculator/sqrt/25
    @RequestMapping(value="/sqrt/{value:.+}", method = RequestMethod.POST)
    public CalculatorModel sqrtWithParam(@PathVariable(value="value")
                                                 String value){
        String input = "";
        double parsedValue;
        try{
            parsedValue = Double.valueOf(value);
        }
        catch (NumberFormatException nfe){
            input = value;
            calculatorModel = new CalculatorModel("sqrt error",
                    input,
                    "Incorrect input value: empty or not a number");
            calculatorService.addAction(calculatorModel);
            return calculatorModel;
        }
        if (parsedValue<0){
            input = value;
            calculatorModel = new CalculatorModel("sqrt error",
                    input,
                    "Incorrect input value: should be greater than 0");
            calculatorService.addAction(calculatorModel);
            return calculatorModel;
        }
        input = value;
        String output = String.valueOf(Math.sqrt(Double.valueOf(value)));
        calculatorModel = new CalculatorModel("sqrt",
                input,
                output);
        calculatorService.addAction(calculatorModel);
        return calculatorModel;
    }

    @RequestMapping(value="/fibonacci/{value:.+}", method = RequestMethod.POST)
    public CalculatorModel fib (@PathVariable(value="value") String fib){
        String input;
        List<String> result = new ArrayList<>();
        int parsedValue;
        try{
            parsedValue = Integer.valueOf(fib);
        }
        catch (NumberFormatException nfe){
            input = fib;
            calculatorModel = new CalculatorModel("fibonacci error",
                    input,
                    emptyOrNotAnInteger);
            calculatorService.addAction(calculatorModel);
            return calculatorModel;
        }
        if (parsedValue < 1) {
            input = fib;
            calculatorModel = new CalculatorModel("fibonacci error",
                    input,
                    "Incorrect input value: less or equal 0");
            calculatorService.addAction(calculatorModel);
            return calculatorModel;

        }
        input = fib;
        int fibValue = 0;
        int value1 = 0;
        int value2 = 1;
        result.add("0");
        result.add("1");
        for (int i=2;i<Integer.valueOf(fib);i++){
            fibValue = value1 + value2;
            value1 = value2;
            value2 = fibValue;
            result.add(String.valueOf(fibValue));
        }
        calculatorModel = new CalculatorModel("fibonacci",
                input,
                result.toString());
        calculatorService.addAction(calculatorModel);
        return calculatorModel;
    }

    @RequestMapping(value="/factorial/{value:.+}", method = RequestMethod.POST)
    public CalculatorModel factorial(@PathVariable(value="value")String factorial){
        String input = "";
        String result = "";
        try {
            double i = Double.valueOf(factorial);
        }
        catch (NumberFormatException nfe) {
            input = factorial;
            calculatorModel = new CalculatorModel("factorial error",
                    input,
                    emptyOrNotAnInteger);
            calculatorService.addAction(calculatorModel);
            return calculatorModel;
        }
        if (Double.valueOf(factorial)> 0 && Double.valueOf(factorial)<171){
            input=factorial;
            double factorialNumber = Double.valueOf(factorial);
            double fValue = 1;
            for(int i=1; i<=factorialNumber; i++) {
                fValue *= i;
                result = String.valueOf(fValue);
            }
            calculatorModel = new CalculatorModel("factorial",
                    input,
                    result);
            calculatorService.addAction(calculatorModel);
            return calculatorModel;
        }
        input=factorial;
        result = "Value is outside of acceptable range: from 1 to 170";
        calculatorModel = new CalculatorModel("factorial error",
                input,
                result);
        calculatorService.addAction(calculatorModel);
        return calculatorModel;
    }

    @RequestMapping(value="/arithmetic", method = RequestMethod.POST)
    public CalculatorModel arithmetic (@RequestParam (value="action")String action,
                                       @RequestParam (value="params")String param){
        List<String>input = new ArrayList<>();
        String result="";
        //Array or param values
        String[] array = param.split(",");
        String parseErrorText = "Error: some value is not numeric";

        if (param.isEmpty()){
            calculatorModel = new CalculatorModel("addition error",
                    "0",
                    "Parameter can't be empty");
            calculatorService.addAction(calculatorModel);
            return calculatorModel;
        }
        switch(action){
            case "addition":
                double count=0;
                double parsedValue;
                for (String anArray: array){
                    try{
                        parsedValue = Double.valueOf(anArray);
                    }
                    catch (NumberFormatException nfe){
                        calculatorModel = new CalculatorModel("addition error",
                                anArray,
                                parseErrorText);
                        calculatorService.addAction(calculatorModel);
                        return calculatorModel;
                    }
                    input.add(anArray);
                    //the same as count = count + parsedValue;
                    count += parsedValue;
                    result = String.valueOf(count);

                }
                calculatorModel = new CalculatorModel("addition",
                        input.toString(),
                        result);
                calculatorService.addAction(calculatorModel);
                return calculatorModel;

            case "subtraction":
                float value = 0;
                double parsedSub;
                try {
                    parsedSub = Double.valueOf(array[0]);
                }
                catch(NumberFormatException nfe){
                    calculatorModel = new CalculatorModel("division error", array[0], parseErrorText);
                    calculatorService.addAction(calculatorModel);
                    return calculatorModel;
                }
                input.add(array[0]);
                for (int i=1; i<array.length; i++){
                    try {
                        value = Float.valueOf(array[i]);

                    } catch (NumberFormatException nfe) {
                        calculatorModel = new CalculatorModel("division error", array[i], parseErrorText);
                        calculatorService.addAction(calculatorModel);
                        return calculatorModel;
                    }
                    input.add(array[i]);
                    parsedSub -= value;
                }
                result = String.valueOf(parsedSub);
                calculatorModel = new CalculatorModel("subtraction", input.toString(), result);
                calculatorService.addAction(calculatorModel);
                return calculatorModel;

            case "multiplication":
                float mcount = 1;
                double toParse;
                for (String anArray : array) {
                    try {
                        toParse = Double.valueOf(anArray);
                    } catch (NumberFormatException nfe) {
                        calculatorModel = new CalculatorModel("multiplication error", anArray, parseErrorText);
                        calculatorService.addAction(calculatorModel);
                        return calculatorModel;
                    }
                    input.add(anArray);
                    mcount *= toParse;
                }
                result = String.valueOf(mcount);
                calculatorModel = new CalculatorModel("multiplication", input.toString(), result);
                calculatorService.addAction(calculatorModel);
                return calculatorModel;

            case "division":
                float currentValueToParse;
                float dvalue;
                try {
                    dvalue = Float.valueOf(array[0]);
                }
                catch(NumberFormatException nfe){
                    calculatorModel = new CalculatorModel("division error", array[0], parseErrorText);
                    calculatorService.addAction(calculatorModel);
                    return calculatorModel;
                }
                input.add(String.valueOf(dvalue));
                for (int i=1; i<array.length; i++){
                    try {
                        currentValueToParse = Float.valueOf(array[i]);

                    } catch (NumberFormatException nfe) {
                        calculatorModel = new CalculatorModel("division error", array[i], parseErrorText);
                        calculatorService.addAction(calculatorModel);
                        return calculatorModel;
                    }
                    if (i>=1 && Float.valueOf(array[i])==0){
                        result = "Division by zero is prohibited!";
                        calculatorModel = new CalculatorModel("division error", array[i], result);
                        calculatorService.addAction(calculatorModel);
                        return calculatorModel;
                    }
                    input.add(array[i]);
                    dvalue /= currentValueToParse;
                }
                result = String.valueOf(dvalue);
                calculatorModel = new CalculatorModel("division", input.toString(), result);
                calculatorService.addAction(calculatorModel);
                return calculatorModel;

            default:
                result = "Incorrect action was requested. Please use one of the following actions: addition, subtraction, division or multiplication";
                calculatorModel = new CalculatorModel("action error",
                        input.toString(),
                        result);
                calculatorService.addAction(calculatorModel);
                return calculatorModel;
        }
    }

}
