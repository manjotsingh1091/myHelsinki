package com.example.demo.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.List;

@Service
public class placeService {
    private final RestTemplate restTemplate;

    public placeService (RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String getEventsPlainJSON() throws Exception{
        String urlString = "http://open-api.myhelsinki.fi/v1/places/";
        try {
            URI url = new URI(urlString);
            ResponseEntity<String> response = this.restTemplate.getForEntity(url,String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            }
            throw new Exception("HTTP statuscode not 200");
        } catch (URISyntaxException e) {
            System.out.println("URL is not valid - URSyntax Exception :" +e.getMessage());
            throw new Exception("URL is not valid");
        } catch (Exception e) {
            if(e instanceof HttpStatusCodeException){
                String responseText=((HttpStatusCodeException)e).getResponseBodyAsString();
                System.out.println("Exception :" +responseText);
                return responseText;
            } else {
                throw new Exception("URL is not valid");
            }
        }
    }
}

