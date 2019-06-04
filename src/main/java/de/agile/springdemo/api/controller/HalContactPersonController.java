package de.agile.springdemo.api.controller;

import de.agile.springdemo.api.models.ContactPersonModel;
import de.agile.springdemo.api.models.ContactPersonModelAssembler;
import de.agile.springdemo.domain.service.ContactPersonService;
import de.agile.springdemo.domain.vo.ContactPersonVO;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@ExposesResourceFor(ContactPersonVO.class)
@RequestMapping("/hal/customers/{customerNo}/contactPeople")
public class HalContactPersonController {

    private ContactPersonService contactPersonService;
    private ModelMapper modelMapper;

    public HalContactPersonController(ContactPersonService contactPersonService, ModelMapper modelMapper) {
        this.contactPersonService = contactPersonService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public CollectionModel<ContactPersonModel> contactPeopleOfCustomer(@PathVariable String customerNo) {
        List<ContactPersonVO> allContactPersons = contactPersonService.findAllContactPersons(customerNo);
        return new ContactPersonModelAssembler(modelMapper, customerNo).toCollectionModel(allContactPersons);

    }

    @GetMapping("/{contactPersonId}")
    public ResponseEntity<?> contactPersonOfCustomer(@PathVariable String customerNo, @PathVariable Long contactPersonId) {
        Optional<ContactPersonVO> contactPerson = contactPersonService.findById(customerNo, contactPersonId);
        ResponseEntity response = contactPerson.map(contactPersonEntity -> ResponseEntity.ok(new ContactPersonModelAssembler(modelMapper, customerNo).toModel(contactPersonEntity))).orElse(ResponseEntity.notFound().build());
        return response;


    }

}
