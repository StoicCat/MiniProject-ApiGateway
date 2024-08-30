package com.miniProject.OlShop.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthCheckController {

    @GetMapping
    public String checkHealth() {
        // Anda bisa menambahkan logika tambahan di sini untuk memeriksa kesehatan aplikasi, seperti koneksi database, dsb.
        return "OK";
    }
}
