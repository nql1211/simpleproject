package com.ifisolution.simpleproject.service;

import com.ifisolution.simpleproject.entity.Customers;
import com.ifisolution.simpleproject.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customers> getAllCustomers(){
        return customerRepository.findAll();
    }

    public void addCustomer(Customers customers){
        Boolean existPhone = customerRepository.selectExitsPhone(customers.getPhone());
        if (existPhone){
            throw new IllegalStateException("Phone is exist");
        }
        customerRepository.save(customers);
    }

    public void deleteCustomer(Long id){
        customerRepository.deleteById(id);
    }

    @Transactional
    public void updateCustomer(Long id, String name, String phone){
        Optional<Customers> customers = customerRepository.findById(id);

        if (customers.isPresent()){
            if (name != null && name.length() > 0 && !Objects.equals(name, customers.get().getName())){
                customers.get().setName(name);
            }

            if (phone != null && phone.length() > 0 && !Objects.equals(customers.get().getPhone(), phone)){
                customers.get().setPhone(phone);
            }
        }
    }
}
