package de.agile.springdemo.domain.repository;


import de.agile.springdemo.domain.entity.ContactPerson;
import org.springframework.data.repository.CrudRepository;

public interface ContactPersonRepository extends CrudRepository<ContactPerson, Long> {

    public ContactPerson findContactPersonByFirstNameAndLastName(String firstName, String lastName);


}
