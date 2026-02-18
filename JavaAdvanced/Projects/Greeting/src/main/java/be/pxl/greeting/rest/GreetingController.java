package be.pxl.greeting.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    @GetMapping
    public String sayHello() {
        return "Hello World";
    }

    @GetMapping("/hello")
    public String sayHelloAgain() {
        return "Hello Again";
    }
}
