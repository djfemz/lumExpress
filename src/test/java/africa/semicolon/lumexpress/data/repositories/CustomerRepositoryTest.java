package africa.semicolon.lumexpress.data.repositories;

import africa.semicolon.lumexpress.data.models.Cart;
import africa.semicolon.lumexpress.data.models.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp(){
        customerRepository.deleteAll();
    }

    @Test
    void findByEmailTest() {
        var customer  = Customer.builder()
                .cart(new Cart())
                .build();
        customer.setEmail("test@email.com");
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setPassword("password");
        var savedCustomer =
                customerRepository.save(customer);
        assertThat(customerRepository
                .findByEmail(savedCustomer.getEmail()))
                .isNotNull();
    }
}