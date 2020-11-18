package com.example.shortify;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @DisplayName("Check to see if full URL Input exists")
    @Test
    public void whenOpeningPage_fullURLInputFieldExists() throws Exception {
        HtmlPage frontpage = webClient.getPage("http://localhost:8080");

        HtmlInput fullURLInput = frontpage.getHtmlElementById("URLInput");

        assertNotNull(fullURLInput);

    }

    @DisplayName("Check to see if shorturl input exists")
    @Test
    public void whenOpeningPage_shortURLInputFieldExists() throws Exception{
        HtmlPage frontpage = webClient.getPage("http://localhost:8080");

        HtmlInput shortURLInput = frontpage.getHtmlElementById("shortURL");

        assertNotNull(shortURLInput);

    }

    @DisplayName("Test to check if we have a button present")
    @Test
    public void whenOpeningPage_aButtonExists() throws Exception{
        HtmlPage frontpage = webClient.getPage("http://localhost:8080");

        HtmlButton button = frontpage.getHtmlElementById("sendButton");
        String buttonText = button.getTextContent();

        //check if text on button corresponds to given text
        assertNotNull(button);

        assertEquals("Click here to get short URL", buttonText);
    }

    @DisplayName("Testing to see if we can gather input from the fields")
    @Test
    public void whenSettingFullURL_textIsPresent() throws Exception{
        MockHttpServletRequestBuilder createMessage = post("/shortenurl")
                .param("full_url","http://www.google.com");

        this.mockMvc.perform(createMessage)
                .andExpect(content().string(containsString("google"))); //this should work
    }


}
