package de.agile.springdemo.domain.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import de.agile.springdemo.api.mapper.ContactPersonMapper;
import de.agile.springdemo.config.PreloadContactPersonConfig;
import de.agile.springdemo.domain.entity.ContactPerson;
import de.agile.springdemo.domain.entity.Salutation;
import de.agile.springdemo.domain.vo.ContactPersonVO;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created on 28.05.1
 */

@Service
@Slf4j
public class ContactPersonService {

    private List<ContactPerson> people;
    private Random random = new Random();
    private ContactPersonMapper contactPersonMapper;

    public ContactPersonService(ContactPersonMapper contactPersonMapper, PreloadContactPersonConfig preloadContactPersonConfig) {
        this.contactPersonMapper = contactPersonMapper;
        this.people = preloadContactPersonConfig.getUsers();
        log.info("Initialized {} users from file", people.size());
    }

    public List<ContactPersonVO> findAllContactPersons() {

       return Ordering.from(Comparator.comparingLong(ContactPersonVO::getId))
               .sortedCopy(people
               .stream()
               .map(contactPerson -> contactPersonMapper.contactPersonToContactPersonVO(contactPerson))
               .collect(Collectors.toList()));
    }

    public Optional<ContactPersonVO> findById(Long contactPersonId) {
        return findAllContactPersons().stream().filter(contactPerson -> contactPerson.getId().equals(contactPersonId)).findFirst();
    }

    public void deleteById(Long contactPersonId) {
        findAllContactPersons().removeIf(contactPerson -> contactPerson.getId().equals(contactPersonId));
    }

    public ContactPersonVO insert(ContactPersonVO contactPersonVO) {
        ContactPerson contactPerson = contactPersonMapper.contactPersonVOToContactPerson(contactPersonVO);
        contactPerson.setId(random.nextLong());
        people.add(contactPerson);
        return contactPersonMapper.contactPersonToContactPersonVO(contactPerson);
    }

    public void update(ContactPersonVO contactPerson) {
       deleteById(contactPerson.getId());
       insert(contactPerson);
    }


}
