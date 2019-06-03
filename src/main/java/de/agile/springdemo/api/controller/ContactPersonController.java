package de.agile.springdemo.api.controller;

import de.agile.springdemo.domain.entity.ContactPerson;
import de.agile.springdemo.domain.service.ContactPersonService;
import de.agile.springdemo.domain.vo.ContactPersonVO;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created on 28.05.19
 */

@RestController
@ExposesResourceFor(ContactPerson.class)
@RequestMapping("api/contactpersons")
public class ContactPersonController {

    private ContactPersonService contactPersonService;

    public ContactPersonController(ContactPersonService contactPersonService, EntityLinks entityLinks) {
        this.contactPersonService = contactPersonService;
    }

    @GetMapping
    public List<ContactPersonVO> listPersons() {
        return contactPersonService.findAllContactPersons();
    }

    @GetMapping("/{contactPersonId}")
    public ResponseEntity<ContactPersonVO> getPersonById(@PathVariable Long contactPersonId) {
        return ResponseEntity.of(contactPersonService.findById(contactPersonId));
    }

    @DeleteMapping("/{contactPersonId}")
    public ResponseEntity deleteById(@PathVariable Long contactPersonId) {
        contactPersonService.deleteById(contactPersonId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<ContactPersonVO> createContactPerson(@Valid @RequestBody ContactPersonVO contactPerson) {
        return ResponseEntity.ok(contactPersonService.insert(contactPerson));
    }

    @PutMapping("/{contactPersonId}")
    public ResponseEntity<ContactPersonVO> updateContactPerson(@Valid @RequestBody ContactPersonVO contactPerson, @PathVariable Long contactPersonId) {
        contactPerson.setId(contactPersonId);
        contactPersonService.update(contactPerson);
        return ResponseEntity.noContent().build();
    }



}
