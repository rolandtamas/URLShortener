package com.example.shortify;

import com.example.shortify.controllers.URLShortenerRestController;
import com.example.shortify.models.ShortURL;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class URLShortenerRestControllerTest {
    private static URLShortenerRestController restController;

    @BeforeAll
    public static void setUp(){
        restController = new URLShortenerRestController();
    }
    @Test
    public void whenGetShortURLGetsCalled_thenReturnNotNull(){
        ShortURL shortURL = new ShortURL();
        shortURL.setFull_url("https://www.google.com/");
        assertNotNull(restController.getShortUrl(shortURL));
    }

}
