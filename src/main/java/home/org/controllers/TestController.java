package home.org.controllers;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@Component

public class TestController {
    @RequestMapping(value="/test", method = RequestMethod.GET)
    public String pingEndpoint(){
        return "Hi, user!";
    }
}
