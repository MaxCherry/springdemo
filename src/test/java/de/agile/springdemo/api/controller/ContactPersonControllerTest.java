package de.agile.springdemo.api.controller;

import de.agile.springdemo.domain.vo.ContactPersonVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ContactPersonControllerTest {

    @Autowired
    private ContactPersonController controller;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    private String basePath;

    @BeforeEach
    void setUp() {
        basePath = "http://localhost:" + port;
    }

    @Test
    public void contexLoads() throws Exception {
        assertNotNull(controller);
    }

    @Test
    void testGetContactPerson() {
        ResponseEntity<ContactPersonVO> responseEntity = restTemplate.getForEntity(basePath + "/api/contactpersons/1", ContactPersonVO.class);
        assertNotNull(responseEntity);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals("Peter", responseEntity.getBody().getFirstName());
    }
}