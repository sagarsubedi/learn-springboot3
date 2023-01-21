package com.sagar;

import org.springframework.data.jpa.repository.JpaRepository;

// this is repository class that helps talk to the db and perform operations
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
