package com.ifisolution.simpleproject.service;

import com.ifisolution.simpleproject.entity.Customers;
import com.ifisolution.simpleproject.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith({MockitoExtension.class})
public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;
    private CustomerService underTest;


    @BeforeEach
    void setUp() {
        underTest = new CustomerService(customerRepository);
    }


    @Test
    void canGetCustomers() {
        //when
        underTest.getAllCustomers();

        //then
        verify(customerRepository).findAll();
    }

    @Test
    void canAddCustomer() {
        //given
        Customers customers = new Customers("L", "012", "th");

        //when
        underTest.addCustomer(customers);

        //then
        ArgumentCaptor<Customers> argumentCaptor = ArgumentCaptor.forClass(Customers.class);
        verify(customerRepository).save(argumentCaptor.capture());

        Customers captureCus = argumentCaptor.getValue();
        assertThat(captureCus).isEqualTo(customers);
    }

    @Test
    void canDeleteCustomer() {
        Long id = 1L;
        underTest.deleteCustomer(id);

        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(customerRepository).deleteById(argumentCaptor.capture());

        Long captureId = argumentCaptor.getValue();
        assertThat(captureId).isEqualTo(id);
    }

    @Test

    void canUpdateCustomer() {
        Customers customers = new Customers(1L, "B", "123", "C");
        underTest.addCustomer(customers);

        underTest.updateCustomer(1L, "D", "111");

        ArgumentCaptor<Customers> argumentCaptor = ArgumentCaptor.forClass(Customers.class);
        verify(customerRepository).save(argumentCaptor.capture());

        Customers captureCus = argumentCaptor.getValue();
        assertThat(captureCus).isEqualTo(customers);
    }
}
