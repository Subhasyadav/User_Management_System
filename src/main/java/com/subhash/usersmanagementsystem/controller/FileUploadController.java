package com.subhash.usersmanagementsystem.controller;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class FileUploadController {

    private static final String FLASK_API_URL = "http://127.0.0.1:5000/predict"; // Flask Server URL

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(FLASK_API_URL);

            // Send the file to Flask
            post.setEntity(
                    MultipartEntityBuilder.create()
                            .addBinaryBody("file", file.getInputStream(), ContentType.DEFAULT_BINARY, file.getOriginalFilename())
                            .build()
            );

            // Execute the request and get the response
            try (CloseableHttpResponse response = client.execute(post)) {
                String responseBody = EntityUtils.toString(response.getEntity());
                return ResponseEntity.ok(responseBody); // Return Flask's response
            }

        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error while processing image: " + e.getMessage());
        }
    }
}

