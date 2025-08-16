package com.assessment;

import com.assessment.components.DynamicNotificationManager;
import com.assessment.components.NotficationManager;
import com.assessment.dao.h2.BookDao;
import com.assessment.dao.h2.CustomerDao;
import com.assessment.dao.h2.OrderDao;
import com.assessment.dao.mysql.DepartmentDao;
import com.assessment.dao.mysql.EmployeeDao;
import com.assessment.entity.h2.Book;
import com.assessment.entity.h2.Customer;
import com.assessment.entity.h2.Order;

import com.assessment.entity.mysql.Department;
import com.assessment.entity.mysql.Employee;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

@SpringBootApplication
public class AssessmentApplication {

    public static void main(String[] args) {

        SpringApplication.run(AssessmentApplication.class, args);
    }

    /**
     * Initialize Customer and Order tables with sample data
     *
     * @param customerDao
     * @param orderDao
     * @return
     */
    @Bean
    @Transactional("h2TransactionManager")
    public CommandLineRunner loadDatabase(CustomerDao customerDao,
                                          OrderDao orderDao,
                                          BookDao bookDao) {
        return runner -> {
            List<Customer> customerList = new ArrayList<>();
            IntStream.range(1, 11).forEach(
                    i -> {
                        Customer customer = new Customer(
                                "customer" + i,
                                "customer" + i + "@gmail.com"
                        );
                        customer.setCustomerAddress("Address " + i);

                        Order order1 = new Order(BigDecimal.valueOf(200 * i),
                                "Product-X" + i, LocalDate.now(), true,
                                customer);
                        Order order2 = new Order(BigDecimal.valueOf(100 * i),
                                "Product-Y" + i, LocalDate.now(), true,
                                customer);
                        customer.setOrderList(List.of(order1, order2));

                        customerList.add(customer);

                    }
            );
            customerDao.saveAll(customerList);

            CopyOnWriteArrayList<Book>
                    books = new CopyOnWriteArrayList<>();

            IntStream.range(1, 11).forEach(
                    i ->
                    {
                        Book book = new Book();
                        book.setAuthor("Author " + i);
                        book.setTitle("Title " + i);
                        book.setIsbn("ISBN " + i);
                        books.add(book);
                    }

            );
            bookDao.saveAll(books);

        };
    }


    @Bean
    @Transactional("mySQLTransactionManager")
    public CommandLineRunner loadMySQLDatabase(DepartmentDao departmentDao) {
        return runner -> {
            if ((long) departmentDao.findAll().size() > 0) {
                return;
            }


            Department engineering = new Department("Engineering");


            engineering.addEmployee(new Employee("MD ASHIK ALI KHAN", BigDecimal.valueOf(200000)),
                    new Employee("RAFIQ", BigDecimal.valueOf(100000)),
                    new Employee("KARIM", BigDecimal.valueOf(50000)));


            Department hr = new Department("HR");

            hr.addEmployee(new Employee("AKHTERUZZAMAN", BigDecimal.valueOf(100000)),
                    new Employee("KAMAL", BigDecimal.valueOf(50000)),
                    new Employee("JAMAL", BigDecimal.valueOf(25000)));

            departmentDao.saveAll(
                    List.of(engineering, hr)
            );
        };
    }

    @Bean
    public CommandLineRunner notify(NotficationManager notficationManager) {
        return (args) -> {
            notficationManager.notifyUser("Notify user");
        };
    }

    @Bean
    public CommandLineRunner notifyByType(DynamicNotificationManager notificationManager) {
        return args -> {
            notificationManager.notifyUser("walletService", "wallet message");
            notificationManager.notifyUser("smsService", "SMS message");
            notificationManager.notifyUser("emailService", "Email message");
        };
    }

}
