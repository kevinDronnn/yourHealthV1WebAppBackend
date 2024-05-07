package com.example.yourhealthv1.controller;

import com.example.yourhealthv1.entity.AuthenticationResponse;
import com.example.yourhealthv1.entity.Users;
import com.example.yourhealthv1.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class AuthenticationController {
    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody Users request
    ) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody Users request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @GetMapping("/getCurrentParticipant")
    public ResponseEntity<Map<String, String>> getParticipant() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            Map<String, String> response = new HashMap<>();
            response.put("username", authentication.getName());
            response.put("authority", authentication.getAuthorities()
                    .toString()
                    .substring(1,authentication.getAuthorities().toString().length()-1));
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error", "user not authorized"));
    }

    @GetMapping("/gettingEmail/username/{username}")
    public ResponseEntity<String> findEmail(@PathVariable String username) {
        if (username == null || username.length() == 0) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body("Here`s no email by this username");
        }
        return ResponseEntity.ok(authService.findEmailOfUser(username));
    }

    @PostMapping("/updatePass")
    public void updatePass(@RequestParam(name = "newPassword") String newPassword,
                           @RequestParam(name = "oldPassword") String oldPassword,
                           @RequestParam(name = "email") String email,
                           @RequestParam(name = "name") String name) {
        authService.updatePassword(newPassword, oldPassword, email, name);
    }

    @PostMapping("/fullyLogout")
    public void logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            SecurityContextHolder.clearContext();
            System.out.println("Logged out successfully");
        } else {
            System.out.println("User not authenticated");
        }
    }

}
