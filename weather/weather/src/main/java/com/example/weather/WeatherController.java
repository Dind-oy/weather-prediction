package com.example.weather;

import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.util.*;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @PostMapping("/predict")
    public Map<String, String> predictWeather(@RequestBody WeatherInput input) {
        Map<String, String> response = new HashMap<>();

        try {
            // Path ke file Python dan script Python
            String pythonFilePath = "C:\\PythonProject\\weather-predict\\weather_predict_cmd.py";

            // Menjalankan proses Python
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "python", pythonFilePath,
                    String.valueOf(input.getPrecipitation()),
                    String.valueOf(input.getTempMax()),
                    String.valueOf(input.getTempMin()),
                    String.valueOf(input.getWind())
            );

            // Set directory untuk menjalankan Python script
            processBuilder.directory(new File("C:\\PythonProject\\weather-predict"));
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();

            // Membaca output dari script Python
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String result = reader.readLine();
                process.waitFor();  // Tunggu proses Python selesai
                response.put("prediction", result);
                System.out.println("Python output: " + result);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Gunakan logging untuk produksi
            response.put("error", "Terjadi kesalahan: " + e.getMessage());
        }

        return response;
    }
}