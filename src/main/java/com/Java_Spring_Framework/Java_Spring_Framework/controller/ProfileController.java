package com.Java_Spring_Framework.Java_Spring_Framework.controller;

import com.Java_Spring_Framework.Java_Spring_Framework.dto.ProfileDTO;
import com.Java_Spring_Framework.Java_Spring_Framework.entity.ProfileEntity;
import com.Java_Spring_Framework.Java_Spring_Framework.service.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/profile")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getProfileByUserId(@PathVariable String userId){
        ProfileDTO profile = profileService.getProfileByUserId(userId);
        if (profile == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Invalid User ID"));
        }
        return ResponseEntity.ok(profile);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProfile(@PathVariable String userId, @RequestBody ProfileDTO profileData){
        ProfileDTO profile= profileService.createProfile(userId, profileData);
        if (profile == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Invalid User ID"));
        return ResponseEntity.ok(profile);
    }

}
