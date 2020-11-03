package com.example.shortify.controllers;

import com.example.shortify.models.ShortURL;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class URLShortenerRestController {

    private Map<String, ShortURL> shortURLMap = new HashMap<>();

    @RequestMapping(value = "/shortenurl",method = RequestMethod.POST) //here the API gets called
    public ResponseEntity<ShortURL> getShortUrl(@RequestBody ShortURL shortURL){
        String randomchar = getRandomChars();
        setShortUrl(randomchar,shortURL);
        return new ResponseEntity<>(shortURL, HttpStatus.OK);
    }

    private void setShortUrl(String randomchar, ShortURL shortURL) {
        shortURL.setShort_url("http://localhost:8080/s/"+randomchar);
        shortURLMap.put(randomchar,shortURL);
    }

    @RequestMapping(value = "/s/{randomstring}",method = RequestMethod.GET)
    public void getFullURL(HttpServletResponse response, @PathVariable("randomstring") String randomstring) throws IOException {
        response.sendRedirect(shortURLMap.get(randomstring).getFull_url());
    }

    private String getRandomChars(){
        StringBuilder randomstr = new StringBuilder();
        String charset="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for(int i=0;i<5;i++)
        {
            randomstr.append(charset.charAt((int) Math.floor(Math.random() * charset.length())));
        }
        return randomstr.toString();
    }
}
