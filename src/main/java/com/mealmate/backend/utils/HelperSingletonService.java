package com.mealmate.backend.utils;

import com.mealmate.backend.service.ImageUploadService;

public class HelperSingletonService {

    private static ImageUploadService imageUploadService;

    // could have used ordinary getter, but wanted it to be static
    public static ImageUploadService getImageUploadService() {
        return imageUploadService;
    }

    public static void setImageUploadService(ImageUploadService imageUploadService) {
        HelperSingletonService.imageUploadService = imageUploadService;
    }
}
