package com.example.demo.controller;

import com.example.demo.service.placeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
public class placeController {

    @Autowired
    private com.example.demo.service.placeService placeService;

    @GetMapping("/api/v1/places")
    public String fetchAllPlace() {
        try {
            String places = placeService.getEventsPlainJSON();
            return places;
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
