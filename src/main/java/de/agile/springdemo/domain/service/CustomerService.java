package de.agile.springdemo.domain.service;

import de.agile.springdemo.domain.vo.CustomerVO;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CustomerService {

    public List<CustomerVO> findAllCustomers() {
        return Collections.emptyList();
    }


}
