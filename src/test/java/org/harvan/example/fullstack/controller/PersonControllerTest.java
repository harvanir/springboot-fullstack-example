package org.harvan.example.fullstack.controller;

import org.harvan.example.fullstack.Main;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (22 Jul 2018)
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Main.class)
public class PersonControllerTest {
    private TestRestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(TestRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Test
    public void ping() {
        String pinged = restTemplate.getForEntity("/person/ping", String.class).getBody();

        assertNotNull(pinged);
        assertEquals("pinged...", pinged);
    }
}