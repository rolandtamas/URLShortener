package com.example.shortify;

import com.example.shortify.models.ShortURL;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class URLShortenerRestControllerTest {


    @Autowired
    private TestRestTemplate testRestTemplate;


    @Test
    public void sendPostRequestToCreateAShortURL() throws Exception{

        ShortURL example = new ShortURL();
        example.setFull_url("http://www.google.com");
        HttpEntity<ShortURL> httpEntity = new HttpEntity<>(example);
        ResponseEntity<ShortURL> responseEntity = this.testRestTemplate.postForEntity("/shortenurl",httpEntity,ShortURL.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        assertNotNull(responseEntity.getBody());

    }

    @Test
    public void sendGetRequestToRedirectFromFullURL() throws Exception{

    }

}
