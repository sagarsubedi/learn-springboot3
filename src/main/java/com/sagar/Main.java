package com.sagar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
// SpringBootApplication is alias for
// @ComponentScan [and we can also say @ComponentScan(basePackages="com.sagar") ]
// @EnableAutoConfiguration
// @Configuration - if we have any bean inside that we want to instantiate
@RestController
// this is part of spring web MVC and is an alias for @Controller and @ResponseBody
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping("/")
//    abbvr for @RequestMapping and method=RequestMethod.GET property
    public GreetResponse greet() {
        return new GreetResponse("hello", List.of("JavaScript", "Java"), new Person("Sagar", 22, 100));
    }

    // record GreetResponse(String greet){}
    // record is same as having GreetResponse: final greet, getter, hash, equals (everything is final so no setter)

    // each param is key for its own json object
    record Person(String name, int age, double cashOnHand){}

    // every param is a key for the json object
    record GreetResponse(
            String greet,
            List<String> favProgrammingLanguages,
            Person person
    ){}
}
