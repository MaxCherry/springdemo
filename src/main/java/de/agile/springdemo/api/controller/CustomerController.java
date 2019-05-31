package de.agile.springdemo.api.controller;

import de.agile.springdemo.domain.service.CustomerService;
import de.agile.springdemo.domain.vo.CustomerVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/customers")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    public List<CustomerVO> getCustomers() {
        return customerService.findAllCustomers();
    }

}
