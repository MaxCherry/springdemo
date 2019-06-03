package de.agile.springdemo.domain.service;

import com.google.common.collect.Lists;
import de.agile.springdemo.api.mapper.ContactPersonMapper;
import de.agile.springdemo.domain.entity.ContactPerson;
import de.agile.springdemo.domain.entity.Customer;
import de.agile.springdemo.domain.repository.ContactPersonRepository;
import de.agile.springdemo.domain.repository.CustomerRepository;
import de.agile.springdemo.domain.vo.ContactPersonVO;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ContactPersonService {

    private ContactPersonMapper contactPersonMapper;
    private CustomerRepository customerRepository;
    private ContactPersonRepository contactPersonRepository;


    public ContactPersonService(ContactPersonMapper contactPersonMapper, CustomerRepository customerRepository, ContactPersonRepository contactPersonRepository) {
        this.contactPersonMapper = contactPersonMapper;

        this.customerRepository = customerRepository;
        this.contactPersonRepository = contactPersonRepository;
    }


    public List<ContactPersonVO> findAllContactPersons(String customerNo) {
        List<ContactPersonVO> result = Lists.newArrayList();
        customerRepository.findById(customerNo)
                .ifPresent(customer -> customer.getContactPeople()
                        .forEach(contactPerson -> result.add(contactPersonMapper.contactPersonToContactPersonVO(contactPerson))));
        return result;
    }

    public Optional<ContactPersonVO> findById(String customerNo, Long contactPersonId) {
        return customerRepository.findContactPersonByCustomerNoAndContactPersonId(customerNo, contactPersonId)
                .map(contactPerson -> contactPersonMapper.contactPersonToContactPersonVO(contactPerson));
    }

    public void deleteById(String customerNo, Long contactPersonId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerNo);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.getContactPeople().removeIf(contactPerson -> contactPerson.getId().equals(contactPersonId));
            customerRepository.save(customer);
            contactPersonRepository.deleteById(contactPersonId);
        }
    }

    public ContactPersonVO insert(String customerNo, ContactPersonVO contactPersonVO) {
        Optional<Customer> customer = customerRepository.findById(customerNo);
        if (customer.isPresent()) {
            ContactPerson contactPerson = contactPersonMapper.contactPersonVOToContactPerson(contactPersonVO);
            contactPerson = contactPersonRepository.save(contactPerson);
            Customer customerEntity = customer.get();
            customerEntity.getContactPeople().add(contactPerson);
            customerRepository.save(customerEntity);
            return contactPersonMapper.contactPersonToContactPersonVO(contactPerson);
        }
        throw new EntityNotFoundException("Customer with " + customerNo + " was not found");
    }

    //@Transactional
    public void update(ContactPersonVO contactPerson) {
        contactPersonRepository.save(contactPersonMapper.contactPersonVOToContactPerson(contactPerson));
        //List<Object> objects = Collections.emptyList();
        //objects.get(5);
    }


}
