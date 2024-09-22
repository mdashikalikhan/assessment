package com.assessment;

import com.assessment.dao.CustomerDao;
import com.assessment.dao.OrderDao;
import com.assessment.entity.Customer;
import com.assessment.entity.Order;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.IntStream;

@SpringBootApplication
public class AssessmentApplication {

	public static void main(String[] args) {

		SpringApplication.run(AssessmentApplication.class, args);
	}

	/**
	 * Initialize Customer and Order tables with sample data
	 * @param customerDao
	 * @param orderDao
	 * @return
	 */
	@Bean
	@Transactional
	public CommandLineRunner loadDatabase(CustomerDao customerDao,
										  OrderDao orderDao){
		return runner->{
			List<Customer> customerList = new ArrayList<>();
			IntStream.range(1, 11).forEach(
					i-> {
						Customer customer = new Customer(
								"customer"+i,
								"customer"+i+"@gmail.com"
						);
						customer.setCustomerAddress("Address " + i);

						Order order1 = new Order(BigDecimal.valueOf(200*i),
								"Product-X"+i, LocalDate.now(), true,
								customer);
						Order order2 = new Order(BigDecimal.valueOf(100*i),
								"Product-Y"+i, LocalDate.now(), true,
								customer);
						customer.setOrderList(Arrays.asList(order1, order2));

						customerList.add(customer);

					}
			);
			customerDao.saveAll(customerList);

		};
	}

}
