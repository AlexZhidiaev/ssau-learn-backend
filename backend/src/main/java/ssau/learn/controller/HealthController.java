package ssau.learn.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/health")
public class HealthController {
    @GetMapping("/public")
    public String publicAccess() {
        return "public";
    }

    @GetMapping("/registered")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String registeredAccess() {
        return "registered";
    }

    @GetMapping("/private")
    @PreAuthorize("hasRole('ADMIN')")
    public String privateAccess() {
        return "private";
    }





}