package de.agile.springdemo.domain.repository;

import de.agile.springdemo.domain.entity.Customer;
import de.agile.springdemo.domain.entity.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LocationRepository extends CrudRepository<Location, Long> {

    @Query("select l from Location l where l.customer.customerNo = :customerNo and id = :locationId")
    Optional<Location> findByCustomerAndId(String customerNo, Long locationId);

    Iterable<Location> findByCustomer(Customer customer);

}
