package com.example.shortify;

import com.example.shortify.controllers.HomeController;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class HomeControllerTest {

    private static HomeController homeController;

    @BeforeAll
    public static void setUp(){
        homeController = new HomeController();
    }

    @Test //this one succeeds
    public void frontPageIsLoaded() //This test checks if the front page was loaded
    {
        String pageName = homeController.loadHomepage();
        assertEquals("index",pageName);
    }

    @Test
    public void noPageGetsLoaded() //this one fails
    {
        String pageName = homeController.loadHomepage();
        assertEquals("home",pageName);
    }


}
