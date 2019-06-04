package de.agile.springdemo.domain.service;

import de.agile.springdemo.domain.vo.ContactPersonVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
public class ContactPersonServiceSpringTest {

    @Autowired
    private ContactPersonService contactPersonService;

    @Test
    void testFindAll() {
        List<ContactPersonVO> allContactPersons = contactPersonService.findAllContactPersons();
        assertThat(allContactPersons.size(), is(3));
        assertThat(allContactPersons.get(0).getId(), is(1l));
        assertThat(allContactPersons.get(1).getId(), is(2l));
        assertThat(allContactPersons.get(0).getFirstName(), is("Peter"));
        assertThat(allContactPersons.get(0).getLastName(), is("Griffin"));
    }

}
