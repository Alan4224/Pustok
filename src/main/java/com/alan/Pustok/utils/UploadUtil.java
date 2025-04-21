package com.alan.Pustok.utils;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import io.github.cdimascio.dotenv.Dotenv;

public class UploadUtil {
    public static <T> String getImageLink(MultipartFile file, Class<T> type) {
        Dotenv dotenv = Dotenv.load();
        Cloudinary cloudinary = new Cloudinary(dotenv.get("CLOUDINARY_URL"));
        if (file.isEmpty()) {
            return dotenv.get("DEFAULT_IMAGE_" + type.getSimpleName());
        } else {
            try {
                Map uploadResult = cloudinary.uploader().upload(file.getBytes(), Map.of());
                return (String) uploadResult.get("secure_url");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return "#";
    }

    public static <T> void deleteImageLink(String url, Class<T> type) {
        Dotenv dotenv = Dotenv.load();
        String defaultLink = dotenv.get("DEFAULT_IMAGE_" + type.getSimpleName());
        if (url.length() > 1 && !url.equals(defaultLink)) {
            String[] part = url.split("/");
            String fileName = part[part.length - 1];
            String publicID = fileName.substring(0, fileName.lastIndexOf("."));
            Cloudinary cloudinary = new Cloudinary(dotenv.get("CLOUDINARY_URL"));
            try {
                cloudinary.uploader().destroy(publicID, ObjectUtils.emptyMap());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
