package de.agile.springdemo.api.controller;

import com.fasterxml.jackson.annotation.JsonView;
import de.agile.springdemo.api.mapper.Views;
import de.agile.springdemo.domain.service.CustomerService;
import de.agile.springdemo.domain.vo.CustomerVO;
import de.agile.springdemo.domain.vo.LocationVO;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customers")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerVO> getCustomers(@RequestParam(required = false) String vbkz) {
        if (StringUtils.isEmpty(vbkz)) {
            return customerService.findAllCustomers();
        } else {
            return customerService.findCustomersWithVbkzLike(vbkz);
        }
    }

    public List<CustomerVO> getCustomersWithVbkz() {
        return customerService.findAllCustomers();
    }

    @GetMapping("/{customerNo}")
    //@JsonView(Views.REDUCED.class)
    public ResponseEntity<CustomerVO> getCustomer(@PathVariable String customerNo) {
        return ResponseEntity.of(customerService.findCustomerCustomerNo(customerNo));
    }

    @GetMapping(value = "/{customerNo}", headers = {"payload=full"})
    @JsonView({Views.ALL.class})
    public ResponseEntity<CustomerVO> getCustomerFullRendering(@PathVariable String customerNo) {
        return ResponseEntity.of(customerService.findCustomerCustomerNo(customerNo));
    }

    @GetMapping(value = "/{customerNo}/locations")
    public List<LocationVO> getLocationsOfCustomer(@PathVariable String customerNo) {
        return customerService.findLocationsOfCustomer(customerNo);
    }

}
