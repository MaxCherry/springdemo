package de.agile.springdemo.api.controller;

import de.agile.springdemo.api.models.CustomerModel;
import de.agile.springdemo.api.models.CustomerModelAssembler;
import de.agile.springdemo.domain.service.CustomerService;
import de.agile.springdemo.domain.vo.CustomerVO;
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
@ExposesResourceFor(CustomerVO.class)
@RequestMapping(path = "/hal/customers")
public class HalCustomerController {

    private CustomerService customerService;
    private CustomerModelAssembler assembler;

    public HalCustomerController(CustomerService customerService, CustomerModelAssembler assembler) {
        this.customerService = customerService;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<CustomerModel> findAll() {
        List<CustomerVO> allCustomers = customerService.findAllCustomers();
        CollectionModel<CustomerModel> customerModels = assembler.toCollectionModel(allCustomers);
        return customerModels;
    }

    @GetMapping("/{customerNo}")
    public ResponseEntity<?> findOne(@PathVariable String customerNo) {
        Optional<CustomerVO> customer = customerService.findCustomerCustomerNo(customerNo);
        ResponseEntity response = customer.map(customerEntity -> ResponseEntity.ok(assembler.toModel(customerEntity))).orElse(ResponseEntity.notFound().build());
        return response;

    }
}
