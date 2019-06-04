package de.agile.springdemo.domain.service;

import com.google.common.collect.Lists;
import de.agile.springdemo.domain.entity.Customer;
import de.agile.springdemo.domain.repository.CustomerRepository;
import de.agile.springdemo.domain.repository.LocationRepository;
import de.agile.springdemo.domain.vo.CustomerVO;
import de.agile.springdemo.domain.vo.LocationVO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;
    private LocationRepository locationRepository;
    private ModelMapper modelMapper;

    public CustomerService(CustomerRepository customerRepository, LocationRepository locationRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.locationRepository = locationRepository;
        this.modelMapper = modelMapper;
    }

    public List<CustomerVO> findAllCustomers() {
        List<CustomerVO> result = Lists.newArrayList();

        customerRepository.findAll().forEach(customer -> result.add(modelMapper.map(customer, CustomerVO.class)));
        return result;
    }

    public List<CustomerVO> findCustomersWithVbkzLike(String vbkz) {
        List<CustomerVO> result = Lists.newArrayList();
        customerRepository.findByVbkzLike(vbkz + "%").forEach(customer -> result.add(modelMapper.map(customer, CustomerVO.class)));
        return result;
    }


    public Optional<CustomerVO> findCustomerCustomerNo(String customerNo) {
        return customerRepository.findById(customerNo).map(customer -> modelMapper.map(customer, CustomerVO.class));
    }

    public List<LocationVO> findLocationsOfCustomer(String customerNo) {
        List<LocationVO> locations = Lists.newArrayList();
        Optional<Customer> customer = customerRepository.findById(customerNo);
        customer.ifPresent(customerVO -> customerVO.getLocations()
                .forEach(location -> locations.add(modelMapper.map(location, LocationVO.class))));
        return locations;

    }
}
