package de.agile.springdemo.domain.repository;

import de.agile.springdemo.SpringDemoApplication;
import de.agile.springdemo.domain.entity.ContactPerson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringDemoApplication.class})
class ContactPersonRepositoryTest {

    @Autowired
    private ContactPersonRepository repository;

    @Test
    void testFindById() {
        Optional<ContactPerson> optionalContactPerson = repository.findById(1l);
        assertTrue(optionalContactPerson.isPresent());
        assertEquals(optionalContactPerson.get().getId(), 1l);
        assertEquals(optionalContactPerson.get().getFirstName(), "Atze");
    }

    @Test
    void testFindByFirstNameLastName() {
        ContactPerson contactPerson = repository.findContactPersonByFirstNameAndLastName("Atze", "Peng");
        assertEquals(contactPerson.getId(), 1l);
        assertEquals(contactPerson.getFirstName(), "Atze");
    }
}