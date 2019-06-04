package de.agile.springdemo.api.controller;

import de.agile.springdemo.domain.service.ContactPersonService;
import de.agile.springdemo.domain.vo.ContactPersonVO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created on 28.05.19
 */

@RestController
@RequestMapping("api/customers/{customerNo}/contactPeople")
public class ContactPersonController {

    private ContactPersonService contactPersonService;

    public ContactPersonController(ContactPersonService contactPersonService) {
        this.contactPersonService = contactPersonService;
    }

    @GetMapping
    public List<ContactPersonVO> listPersons(@PathVariable String customerNo) {
        return contactPersonService.findAllContactPersons(customerNo);
    }

    @PreAuthorize("hasRole('APIUSER_READWRITE')")
    @GetMapping("/{contactPersonId}")
    public ResponseEntity<ContactPersonVO> getPersonById(@PathVariable String customerNo, @PathVariable Long contactPersonId) {
        return ResponseEntity.of(contactPersonService.findById(customerNo, contactPersonId));
    }

    @PreAuthorize("hasRole('APIUSER_READWRITE')")
    @DeleteMapping("/{contactPersonId}")
    public ResponseEntity deleteById(@PathVariable String customerNo, @PathVariable Long contactPersonId) {
        contactPersonService.deleteById(customerNo, contactPersonId);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('APIUSER_READWRITE')")
    @PostMapping
    public ResponseEntity<ContactPersonVO> createContactPerson(@PathVariable String customerNo, @Valid @RequestBody ContactPersonVO contactPerson) {
        return ResponseEntity.ok(contactPersonService.insert(customerNo, contactPerson));
    }

    @PreAuthorize("hasRole('APIUSER_READWRITE')")
    @PutMapping("/{contactPersonId}")
    public ResponseEntity<ContactPersonVO> updateContactPerson(@PathVariable String customerNo, @PathVariable Long contactPersonId,
                                                               @Valid @RequestBody ContactPersonVO contactPerson) {
        contactPerson.setId(contactPersonId);
        contactPersonService.update(contactPerson);
        return ResponseEntity.noContent().build();
    }


}
