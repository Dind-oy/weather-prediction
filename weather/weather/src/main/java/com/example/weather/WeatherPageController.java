package com.example.weather;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WeatherPageController {

    @GetMapping("/")
    public String home() {
        // Mengarahkan ke template "weather.html"
        return "weather";
    }
}
