package com.courseshubbackend.utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CloudinaryUtil {

    public static String extractImageIdFromUrl(String url, String directory) {
        if (url == null || url.isEmpty()) {
            return null;
        }

        String regex = "/upload/v\\d+/(.+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);

        if (matcher.find()) {
            String imagePath = matcher.group(1);

            if (directory != null && !directory.isEmpty()) {
                String regexDirectory = "^" + Pattern.quote(directory) + "/(.+)";
                Pattern directoryPattern = Pattern.compile(regexDirectory);
                Matcher directoryMatcher = directoryPattern.matcher(imagePath);

                if (directoryMatcher.find()) {
                    String fileName = directoryMatcher.group(1);
                    return removeFileExtension(fileName);
                }
            } else {
                return removeFileExtension(imagePath);
            }
        }

        return null;
    }

    private static String removeFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex != -1) ? fileName.substring(0, dotIndex) : fileName;
    }

    public static String uploadFile(Cloudinary cloudinary, byte[] fileBytes, String resourceType) throws IOException {
        Map<String, String> uploadParams = ObjectUtils.asMap("resource_type", resourceType);
        uploadParams.put("access_mode", "public");
        Map uploadRes = cloudinary.uploader().upload(fileBytes, uploadParams);
        if (uploadRes != null && uploadRes.containsKey("url") && uploadRes.get("url") != null) {
            return uploadRes.get("url").toString();
        } else {
            throw new RuntimeException("Upload to Cloudinary failed");
        }
    }

    public static void deleteFile(Cloudinary cloudinary, String fileUrl) throws IOException {
        String imageId = CloudinaryUtil.extractImageIdFromUrl(fileUrl, "");
        Map destroyRes = cloudinary.uploader().destroy(imageId, ObjectUtils.emptyMap());
        if (destroyRes == null || !"ok".equals(destroyRes.get("result"))) {
            throw new RuntimeException("Delete from Cloudinary failed");
        }
    }
}
