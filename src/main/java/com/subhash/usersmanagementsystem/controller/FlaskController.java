package com.subhash.usersmanagementsystem.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@RestController
@RequestMapping("/flask")
public class FlaskController {

    private final String FLASK_URL = "http://127.0.0.1:5000/add"; // Flask API URL

    @PostMapping("/add")
    public Map<String, Object> addNumbers(@RequestBody Map<String, Integer> numbers) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(FLASK_URL, numbers, Map.class);
    }
}
