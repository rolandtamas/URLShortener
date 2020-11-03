package com.example.shortify;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//I used an api called HTMLUnit to test things such as the title of the page

@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final WebClient webClient = new WebClient();


    @DisplayName("Test to check if header text is present in page")
    @Test //tests if we have a welcome message contained in the homepage
    public void shouldReturnHeaderMessage() throws Exception{
        this.mockMvc.perform(get("/"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("Welcome to Shortify")));
    }

    @DisplayName("Test to check if page title is ok, using HtmlUnit")
    @Test
    public void whenOpeningPage_thenTitleIsOK() throws Exception{
        HtmlPage frontpage = webClient.getPage("http://localhost:8080");
        assertEquals("Shortify - Shorten URLs and save time",frontpage.getTitleText());
    }

    @DisplayName("Test to check if 2 input fields are present")
    @Test
    public void whenOpeningPage_twoInputFieldsExist() throws Exception {
        HtmlPage frontpage = webClient.getPage("http://localhost:8080");
        String fullURLXpath="/html/body/div/form/div[1]/input";
        String shortURLXpath = "/html/body/div/form/div[2]/input";

        HtmlInput fullURLInput = (HtmlInput) frontpage.getByXPath(fullURLXpath).get(0);
        HtmlInput shortURLInput = (HtmlInput) frontpage.getByXPath(shortURLXpath).get(0);

        assertNotNull(fullURLInput);
        assertNotNull(shortURLInput);

    }

}
