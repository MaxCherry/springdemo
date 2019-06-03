package de.agile.springdemo.api.mapper;

import de.agile.springdemo.domain.entity.Customer;
import de.agile.springdemo.domain.vo.CustomerVO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    Customer customerVOToCustomer(CustomerVO customerVO);

    CustomerVO customerToCustomerVO(Customer customer);

}
