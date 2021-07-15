package com.ifisolution.simpleproject.repository;

import com.ifisolution.simpleproject.entity.Customers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckWhenCustomerExistPhone(){
        //Given
        String phone = "123";
        Customers customers = new Customers("A", phone, "HN");
        underTest.save(customers);

        //when
        boolean expected = underTest.selectExitsPhone(phone);
        //then
        assertThat(expected).isTrue();
    }
    
    @Test
    void findCustomerByPhone(){
        //Given
        String phone = "123";
        Customers customers = new Customers("A", phone, "HN");
        underTest.save(customers);
        
        //when
        

        String name = underTest.findNameByPhone(phone);
        assertThat(name).isEqualTo(customers.getName());
    }
}
