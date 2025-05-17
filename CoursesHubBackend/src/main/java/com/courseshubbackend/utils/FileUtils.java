package com.courseshubbackend.utils;

import org.springframework.web.multipart.MultipartFile;
import java.util.Arrays;
import java.util.List;

public class FileUtils {
    public static String getResourceTypeForCloudinaryUpload(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return "auto";
        }

        String contentType = file.getContentType();
        boolean isVideoMime = contentType != null && contentType.toLowerCase().startsWith("video/");

        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename != null && originalFilename.contains(".")
                ? originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase()
                : "";

        List<String> videoExtensions = Arrays.asList(
                "mp4", "avi", "mov", "wmv", "flv", "mkv", "webm", "mpeg", "mpg"
        );

        if (isVideoMime || videoExtensions.contains(extension)) {
            return "video";
        }

        return "auto";
    }
}