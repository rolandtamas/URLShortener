package com.example.shortify;

import com.example.shortify.controllers.URLShortenerRestController;
import com.example.shortify.models.ShortURL;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.net.URL;
import java.net.http.HttpClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class URLShortenerRestControllerTest {


    @Autowired
    private TestRestTemplate testRestTemplate;
    private WebClient webClient = new WebClient();


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
