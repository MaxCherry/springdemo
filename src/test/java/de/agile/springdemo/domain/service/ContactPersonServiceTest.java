package de.agile.springdemo.domain.service;

import de.agile.springdemo.api.mapper.ContactPersonMapper;
import de.agile.springdemo.config.PreloadContactPersonConfig;
import de.agile.springdemo.domain.entity.ContactPerson;
import de.agile.springdemo.domain.entity.Salutation;
import de.agile.springdemo.domain.vo.ContactPersonVO;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContactPersonServiceTest {

    private ContactPersonService contactPersonService;

    @Mock
    private PreloadContactPersonConfig preloadContactPersonConfig;

    @BeforeEach
    void setUp() {
        List<ContactPerson> contactPeople = new ArrayList<>();

        ContactPerson contactPerson = new ContactPerson();
        contactPerson.setId(1l);
        contactPerson.setFirstName("Atze");
        contactPerson.setLastName("Peng");
        contactPerson.setPhoneNumber("+49111");
        contactPerson.setSalutation(Salutation.MR);
        contactPeople.add(contactPerson);
        contactPerson = new ContactPerson();
        contactPerson.setId(2l);
        contactPerson.setFirstName("Inge");
        contactPerson.setLastName("Knall");
        contactPerson.setPhoneNumber("+49112");
        contactPerson.setSalutation(Salutation.MRS);
        contactPeople.add(contactPerson);
        when(preloadContactPersonConfig.getUsers()).thenReturn(contactPeople);
        contactPersonService = new ContactPersonService(Mappers.getMapper(ContactPersonMapper.class), preloadContactPersonConfig);
    }

    @Test
    void testFindAll() {
        List<ContactPersonVO> allContactPersons = contactPersonService.findAllContactPersons();
        assertThat(allContactPersons.size(), is(2));
        assertThat(allContactPersons.get(0).getId(), is(1l));
        assertThat(allContactPersons.get(1).getId(), is(2l));
        assertThat(allContactPersons.get(0).getFirstName(), is("Atze"));
        assertThat(allContactPersons.get(0).getLastName(), is("Peng"));
    }
}