package com.mealmate.backend.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.mealmate.backend.dto.ImageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ImageUploadService {

    private final Cloudinary cloudinary;

    public ImageInfo uploadImage(MultipartFile file, String folder) throws IOException {
        Map resultMap = cloudinary.uploader()
                .upload(file.getBytes(), ObjectUtils.asMap("folder", folder));
        return new ImageInfo(
                resultMap.get("public_id").toString(),
                resultMap.get("secure_url").toString(),
                resultMap.get("format").toString()
        );
    }

    public String generateImageUrl(String publicId) {
        return cloudinary.url().generate(publicId);
    }

    public String generateTransformedImageUrl(String publicId, int width, int height) {
        return cloudinary
                .url()
                .transformation(
                        new Transformation()
                                .width(width)
                                .height(height)
                                .crop("fill")
                )
                .generate(publicId);
    }
}
