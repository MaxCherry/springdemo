package de.agile.springdemo.domain.service;

import com.google.common.collect.Lists;
import de.agile.springdemo.config.PreloadContactPersonConfig;
import de.agile.springdemo.domain.vo.ContactPersonVO;
import de.agile.springdemo.domain.vo.CustomerVO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Value("${my.test.name}")
    private String sampleProperty;

    private PreloadContactPersonConfig contactPersonConfig;
    private ModelMapper modelMapper;

    public CustomerService(PreloadContactPersonConfig contactPersonConfig, ModelMapper modelMapper) {
        this.contactPersonConfig = contactPersonConfig;
        this.modelMapper = modelMapper;
    }

    public List<CustomerVO> findAllCustomers() {
        CustomerVO customer = new CustomerVO();
        customer.setCustomerNo(sampleProperty);
        customer.setVbkz("vbkz");
        customer.setContactPeople(Lists.newArrayList(modelMapper.map(contactPersonConfig.getContactPeople().get(0), ContactPersonVO.class)));
        return Lists.newArrayList(customer);
    }


}
