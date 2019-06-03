package de.agile.springdemo.domain.repository;

import de.agile.springdemo.domain.entity.ContactPerson;
import de.agile.springdemo.domain.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, String> {

    @Query("select distinct conPeople from Customer c left join c.contactPeople conPeople where c.customerNo = :customerNo and conPeople.id = :contactPersonId")
    Optional<ContactPerson> findContactPersonByCustomerNoAndContactPersonId(String customerNo, Long contactPersonId);


    Iterable<Customer> findByVbkzLike(String vbkzLike);
}
