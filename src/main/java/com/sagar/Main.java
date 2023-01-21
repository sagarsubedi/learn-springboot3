package com.sagar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
// SpringBootApplication is alias for
// @ComponentScan [and we can also say @ComponentScan(basePackages="com.sagar") ]
// @EnableAutoConfiguration
// @Configuration - if we have any bean inside that we want to instantiate
@RestController
// this is part of spring web MVC and is an alias for @Controller and @ResponseBody
@RequestMapping("/api/v1/customers")
public class Main {

    // dependency injection
    private final CustomerRepository customerRepository;

    public Main(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping()
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }


    record NewCustomerRequest(String name, String email, Integer age){}
    @PostMapping()
    public void addCustomer(@RequestBody NewCustomerRequest request){
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());
        customerRepository.save(customer);
    }

    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id){
        customerRepository.deleteById(id);
    }

    @PutMapping("{customerId}")
    public void updateCustomer(@PathVariable("customerId") Integer id, @RequestBody NewCustomerRequest request){
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if(optionalCustomer.isPresent()){
            Customer customer = new Customer();
            customer.setId(id);
            customer.setName(request.name());
            customer.setAge(request.age());
            customer.setEmail(request.email());
            customerRepository.save(customer);
        }
    }

//    @GetMapping("/")
////    abbvr for @RequestMapping and method=RequestMethod.GET property
//    public GreetResponse greet() {
//        return new GreetResponse("hello", List.of("JavaScript", "Java"), new Person("Sagar", 22, 100));
//    }
//
//    // record GreetResponse(String greet){}
//    // record is same as having GreetResponse: final greet, getter, hash, equals (everything is final so no setter)
//
//    // each param is key for its own json object
//    record Person(String name, int age, double cashOnHand){}
//
//    // every param is a key for the json object
//    record GreetResponse(
//            String greet,
//            List<String> favProgrammingLanguages,
//            Person person
//    ){}
}
