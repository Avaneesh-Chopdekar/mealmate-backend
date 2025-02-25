package com.mealmate.backend.controller;

import com.mealmate.backend.dto.ImageInfo;
import com.mealmate.backend.entity.MenuItem;
import com.mealmate.backend.entity.Restaurant;
import com.mealmate.backend.entity.Rider;
import com.mealmate.backend.repository.MenuItemRepository;
import com.mealmate.backend.repository.RestaurantRepository;
import com.mealmate.backend.repository.RiderRepository;
import com.mealmate.backend.service.ImageUploadService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Tag(name = "Image Upload API")
@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
public class ImageUploadController {

    private final ImageUploadService imageUploadService;
    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;
    private final RiderRepository riderRepository;

    @PostMapping("/menu-item/{id}")
    public ResponseEntity<ImageInfo> uploadMenuItemImage(@PathVariable UUID id, @RequestParam("file") MultipartFile file) throws IOException {
        ImageInfo imageInfo = imageUploadService.uploadImage(file, "menu-items");
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MenuItem not found"));
        menuItem.setCloudinaryPublicId(imageInfo.publicId());
        menuItem.setImageUrl(imageInfo.secureUrl());
        menuItemRepository.save(menuItem);
        return ResponseEntity.ok(imageInfo);
    }

    @PostMapping("/restaurant/{id}")
    public ResponseEntity<ImageInfo> uploadRestaurantImage(@PathVariable UUID id, @RequestParam("file") MultipartFile file) throws IOException {
        ImageInfo imageInfo = imageUploadService.uploadImage(file, "restaurants");
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        restaurant.getImageUrls().add(imageInfo.secureUrl());
        restaurantRepository.save(restaurant);
        return ResponseEntity.ok(imageInfo);
    }

    @PostMapping("/rider/{id}")
    public ResponseEntity<?> uploadRiderImage(@PathVariable UUID id, @RequestParam("file") MultipartFile file) throws IOException {
        try {
            ImageInfo imageInfo = imageUploadService.uploadImage(file, "riders");
            Rider rider = riderRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Rider not found"));
            rider.setCloudinaryPublicId(imageInfo.publicId());
            rider.setImageUrl(imageInfo.secureUrl());
            riderRepository.save(rider);
            return ResponseEntity.ok(imageInfo);
        } catch (IOException | RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
