package com.example.shortify.controllers;

import com.example.shortify.models.ShortURL;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "localhost:8080/shortenurl")
@RestController
public class URLShortenerRestController {

    private Map<String, ShortURL> shortURLMap = new HashMap<>();

    @CrossOrigin
    @RequestMapping(value = "/shortenurl",method = RequestMethod.POST)
    public ResponseEntity<Object> getShortUrl(@RequestBody ShortURL shortURL){
        String randomchar = getRandomChars();
        setShortUrl(randomchar,shortURL);
        return new ResponseEntity<Object>(shortURL, HttpStatus.OK);
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
        String randomstr="";
        String charset="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for(int i=0;i<5;i++)
        {
            randomstr += charset.charAt((int)Math.floor(Math.random()*charset.length()));
        }
        return randomstr;
    }
}
