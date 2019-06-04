package de.agile.springdemo.domain.service;

import com.google.common.collect.Lists;
import de.agile.springdemo.domain.entity.ContactPerson;
import de.agile.springdemo.domain.entity.Customer;
import de.agile.springdemo.domain.repository.ContactPersonRepository;
import de.agile.springdemo.domain.repository.CustomerRepository;
import de.agile.springdemo.domain.vo.ContactPersonVO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ContactPersonService {

    private ModelMapper modelMapper;
    private CustomerRepository customerRepository;
    private ContactPersonRepository contactPersonRepository;


    public ContactPersonService(ModelMapper modelMapper, CustomerRepository customerRepository, ContactPersonRepository contactPersonRepository) {
        this.modelMapper = modelMapper;

        this.customerRepository = customerRepository;
        this.contactPersonRepository = contactPersonRepository;
    }


    public List<ContactPersonVO> findAllContactPersons(String customerNo) {
        List<ContactPersonVO> result = Lists.newArrayList();
        customerRepository.findById(customerNo)
                .ifPresent(customer -> customer.getContactPeople()
                        .forEach(contactPerson -> result.add(modelMapper.map(contactPerson, ContactPersonVO.class))));
        return result;
    }

    public Optional<ContactPersonVO> findById(String customerNo, Long contactPersonId) {
        return customerRepository.findContactPersonByCustomerNoAndContactPersonId(customerNo, contactPersonId)
                .map(contactPerson -> modelMapper.map(contactPerson, ContactPersonVO.class));
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
            ContactPerson contactPerson = modelMapper.map(contactPersonVO, ContactPerson.class);
            contactPerson = contactPersonRepository.save(contactPerson);
            Customer customerEntity = customer.get();
            customerEntity.getContactPeople().add(contactPerson);
            customerRepository.save(customerEntity);
            return modelMapper.map(contactPerson, ContactPersonVO.class);
        }
        throw new EntityNotFoundException("Customer with " + customerNo + " was not found");
    }

    //@Transactional
    public void update(ContactPersonVO contactPerson) {
        contactPersonRepository.save(modelMapper.map(contactPerson, ContactPerson.class));
        //List<Object> objects = Collections.emptyList();
        //objects.get(5);
    }


}
