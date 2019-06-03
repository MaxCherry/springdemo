package de.agile.springdemo.domain.service;

import com.google.common.collect.Lists;
import de.agile.springdemo.api.mapper.CustomerMapper;
import de.agile.springdemo.api.mapper.LocationMapper;
import de.agile.springdemo.domain.entity.Customer;
import de.agile.springdemo.domain.repository.CustomerRepository;
import de.agile.springdemo.domain.repository.LocationRepository;
import de.agile.springdemo.domain.vo.CustomerVO;
import de.agile.springdemo.domain.vo.LocationVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;
    private LocationRepository locationRepository;
    private CustomerMapper customerMapper;
    private LocationMapper locationMapper;

    public CustomerService(CustomerRepository customerRepository, LocationRepository locationRepository, CustomerMapper customerMapper, LocationMapper locationMapper) {
        this.customerRepository = customerRepository;
        this.locationRepository = locationRepository;
        this.customerMapper = customerMapper;
        this.locationMapper = locationMapper;
    }

    public List<CustomerVO> findAllCustomers() {
        List<CustomerVO> result = Lists.newArrayList();

        customerRepository.findAll().forEach(customer -> result.add(customerMapper.customerToCustomerVO(customer)));
        return result;
    }

    public List<CustomerVO> findCustomersWithVbkzLike(String vbkz) {
        List<CustomerVO> result = Lists.newArrayList();
        customerRepository.findByVbkzLike(vbkz + "%").forEach(customer -> result.add(customerMapper.customerToCustomerVO(customer)));
        return result;
    }


    public Optional<CustomerVO> findCustomerCustomerNo(String customerNo) {
        return customerRepository.findById(customerNo).map(customer -> customerMapper.customerToCustomerVO(customer));
    }

    public List<LocationVO> findLocationsOfCustomer(String customerNo) {
        List<LocationVO> locations = Lists.newArrayList();
        Optional<Customer> customer = customerRepository.findById(customerNo);
        customer.ifPresent(customerVO -> customerVO.getLocations()
                .forEach(location -> locations.add(locationMapper.locationToLocationVO(location))));
        return locations;

    }
}
