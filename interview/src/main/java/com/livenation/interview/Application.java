package com.livenation.interview;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.livenation.interview.entity.Customer;
import com.livenation.interview.repository.CustomerRepository;
import com.livenation.interview.service.CustomerService;

@SpringBootApplication
public class Application {

	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new Customer("Jack", "Bauer"));
			repository.save(new Customer("Chloe", "O'Brian"));
			repository.save(new Customer("Kim", "Bauer"));
			repository.save(new Customer("David", "Palmer"));
			repository.save(new Customer("Michelle", "Dessler"));

			// fetch all customers
			LOGGER.info("Customers found with findAll():");
			LOGGER.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				LOGGER.info(customer.toString());
			}
			LOGGER.info("");

			// fetch an individual customer by ID
			Customer customer = repository.findOne(1L);
			LOGGER.info("Customer found with findOne(1L):");
			LOGGER.info("--------------------------------");
			LOGGER.info(customer.toString());
			LOGGER.info("");

			// fetch customers by last name
			LOGGER.info("Customer found with findByLastName('Bauer'):");
			LOGGER.info("--------------------------------------------");
			for (Customer bauer : repository.findByLastName("Bauer")) {
				LOGGER.info(bauer.toString());
			}
			LOGGER.info("");
		};
	}
	
	@Bean
	public CommandLineRunner demoService(CustomerService service) {
		LOGGER.info("////// EITHNE ///////");
		return (args) -> {
			
			for (Customer bauer : service.findByLastName("Bauer")) {
				LOGGER.info(bauer.toString());
			}
			LOGGER.info("");
		};
	}

}