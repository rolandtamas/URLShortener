package com.example.shortify;

import com.example.shortify.models.ShortURL;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class URLShortenerRestControllerTest {


    @Autowired
    private TestRestTemplate testRestTemplate;


    @DisplayName("make a post request to create a short url from a given URL")
    @Test
    public void sendPostRequestToCreateAShortURL() throws Exception{

        ShortURL example = new ShortURL();
        example.setFull_url("http://www.google.com");
        HttpEntity<ShortURL> httpEntity = new HttpEntity<>(example);
        ResponseEntity<ShortURL> responseEntity = this.testRestTemplate.postForEntity("/shortenurl",httpEntity,ShortURL.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        assertNotNull(responseEntity.getBody());

        ResponseEntity<String> response = testRestTemplate.getForEntity(responseEntity.getBody().getShort_url(),String.class);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());

    }


    @Test
    public void sendGetRequestToRedirectFromFullURL() throws Exception{
        ShortURL example = new ShortURL();
        example.setFull_url("http://www.youtube.com");

        HttpEntity<ShortURL> httpEntity = new HttpEntity<>(example);
        ResponseEntity<ShortURL> responseEntity = this.testRestTemplate.postForEntity("/shortenurl",httpEntity,ShortURL.class);

        assertNotNull(responseEntity.getBody());

        assertEquals(example.getFull_url(),responseEntity.getBody().getFull_url());

    }



}
