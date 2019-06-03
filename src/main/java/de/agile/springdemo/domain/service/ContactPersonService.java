package de.agile.springdemo.domain.service;

import com.google.common.collect.Lists;
import de.agile.springdemo.api.mapper.ContactPersonMapper;
import de.agile.springdemo.domain.entity.ContactPerson;
import de.agile.springdemo.domain.repository.ContactPersonRepository;
import de.agile.springdemo.domain.vo.ContactPersonVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactPersonService {

    private ContactPersonMapper contactPersonMapper;
    private ContactPersonRepository contactPersonRepository;

    public ContactPersonService(ContactPersonMapper contactPersonMapper, ContactPersonRepository contactPersonRepository) {
        this.contactPersonMapper = contactPersonMapper;
        this.contactPersonRepository = contactPersonRepository;
    }


    public List<ContactPersonVO> findAllContactPersons() {
        List<ContactPersonVO> result = Lists.newArrayList();
        contactPersonRepository.findAll().forEach(contactPerson -> result.add(contactPersonMapper.contactPersonToContactPersonVO(contactPerson)));
        return result;
    }

    public Optional<ContactPersonVO> findById(Long contactPersonId) {
        return contactPersonRepository.findById(contactPersonId).map(contactPerson -> contactPersonMapper.contactPersonToContactPersonVO(contactPerson));
    }

    public void deleteById(Long contactPersonId) {
        contactPersonRepository.deleteById(contactPersonId);
    }

    public ContactPersonVO insert(ContactPersonVO contactPersonVO) {
        ContactPerson contactPerson = contactPersonMapper.contactPersonVOToContactPerson(contactPersonVO);
        contactPerson = contactPersonRepository.save(contactPerson);
        return contactPersonMapper.contactPersonToContactPersonVO(contactPerson);
    }

    //@Transactional
    public void update(ContactPersonVO contactPerson) {
        contactPersonRepository.save(contactPersonMapper.contactPersonVOToContactPerson(contactPerson));
        //List<Object> objects = Collections.emptyList();
        //objects.get(5);
    }


}
