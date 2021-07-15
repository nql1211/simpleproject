package com.ifisolution.simpleproject.repository;

import com.ifisolution.simpleproject.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Long> {
    @Query("" +
            "SELECT CASE WHEN COUNT(c) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM Customers c " +
            "WHERE c.phone = ?1"
    )
    Boolean selectExitsPhone(String phone);

    @Query("select c.name from Customers c where c.phone = ?1")
    String findNameByPhone(String phone);
}
