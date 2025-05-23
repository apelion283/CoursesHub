package com.courseshubbackend.services.implement;

import com.cloudinary.Cloudinary;
import com.courseshubbackend.pojos.Chapter;
import com.courseshubbackend.pojos.course.Course;
import com.courseshubbackend.pojos.Lecture;
import com.courseshubbackend.pojos.Resource;
import com.courseshubbackend.pojos.course.CourseStatusEnum;
import com.courseshubbackend.repositories.CourseRepository;
import com.courseshubbackend.services.CourseService;
import com.courseshubbackend.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.hibernate.Session;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.courseshubbackend.utils.CloudinaryUtil.deleteFile;
import static com.courseshubbackend.utils.CloudinaryUtil.uploadFile;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public int countCourses(Map<String, String> params) {
        return this.courseRepository.countCourses(params);
    }

    @Override
    public List<Course> getCourses(Map<String, String> params) {
        return this.courseRepository.getCourses(params);
    }

    @Override
    public Course addOrUpdateCourse(Course course) throws IOException {
        boolean isNewCourse = (course.getId() == null);
        Course existingCourse = null;
        if (!isNewCourse) {
            Map<String, String> params = new HashMap<>();
            params.put("courseId", String.valueOf(course.getId()));
            existingCourse = courseRepository.getCourses(params).get(0);
        }

        if (course.getFile() != null && !course.getFile().isEmpty()) {
            try {
                String resourceTypeToUpload = FileUtils.getResourceTypeForCloudinaryUpload(course.getFile());
                String newImageUrl = uploadFile(cloudinary, course.getFile().getBytes(), resourceTypeToUpload);
                if (existingCourse != null && existingCourse.getImageUrl() != null) {
                    deleteFile(cloudinary, existingCourse.getImageUrl());
                }
                course.setImageUrl(newImageUrl);
            } catch (IOException ex) {
                Logger.getLogger(CourseServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if(isNewCourse){
            for (Chapter chapter : course.getChapterList()) {
                for (Lecture lecture : chapter.getLectureList()) {
                    if (lecture.getVideoFile() != null && !lecture.getVideoFile().isEmpty()) {
                        String videoUrl = uploadFile(cloudinary, lecture.getVideoFile().getBytes(),
                                FileUtils.getResourceTypeForCloudinaryUpload(lecture.getVideoFile()));
                        lecture.setVideoUrl(videoUrl);
                    }
                    if (lecture.getDocumentFile() != null && !lecture.getDocumentFile().isEmpty()) {
                        String documentUrl = uploadFile(cloudinary, lecture.getDocumentFile().getBytes(),
                                FileUtils.getResourceTypeForCloudinaryUpload(lecture.getDocumentFile()));
                        lecture.setDocumentUrl(documentUrl);
                    }
                }

                for (Resource resource : chapter.getResourceList()) {
                    if (resource.getResourceFile() != null && !resource.getResourceFile().isEmpty()) {
                        String resourceUrl = uploadFile(cloudinary, resource.getResourceFile().getBytes(),
                                FileUtils.getResourceTypeForCloudinaryUpload(resource.getResourceFile()));
                        resource.setUrl(resourceUrl);
                    }
                }
            }
        }
        else{
            Set<Integer> existingChapterIds = existingCourse.getChapterList().stream()
                    .map(Chapter::getId)
                    .collect(Collectors.toSet());

            Set<Integer> newChapterIds = course.getChapterList().stream()
                    .map(Chapter::getId)
                    .collect(Collectors.toSet());

            Set<Integer> deletedChapterIds = new HashSet<>(existingChapterIds);
            deletedChapterIds.removeAll(newChapterIds);

            for (Chapter chapter : course.getChapterList()) {

                for (Lecture lecture : chapter.getLectureList()) {
                    Lecture existingLecture = existingCourse.getChapterList().stream()
                            .flatMap(ch -> ch.getLectureList().stream())
                            .filter(l -> l.getId().equals(lecture.getId()))
                            .findFirst().orElse(null);

                    if (existingLecture == null) {
                        // Xử lý upload video
                        if (lecture.getVideoFile() != null && !lecture.getVideoFile().isEmpty()) {
                            String resourceTypeToUpload = FileUtils.getResourceTypeForCloudinaryUpload(lecture.getVideoFile());
                            String videoUrl = uploadFile(cloudinary, lecture.getVideoFile().getBytes(), resourceTypeToUpload);
                            lecture.setVideoUrl(videoUrl);
                        }

                        if (lecture.getDocumentFile() != null && !lecture.getDocumentFile().isEmpty()) {
                            String resourceTypeToUpload = FileUtils.getResourceTypeForCloudinaryUpload(lecture.getDocumentFile());
                            String documentUrl = uploadFile(cloudinary, lecture.getDocumentFile().getBytes(), resourceTypeToUpload);
                            lecture.setDocumentUrl(documentUrl);
                        }
                    } else {
                        if (lecture.getVideoFile() != null && !lecture.getVideoFile().isEmpty()) {
                            String resourceTypeToUpload = FileUtils.getResourceTypeForCloudinaryUpload(lecture.getVideoFile());
                            String videoUrl = uploadFile(cloudinary, lecture.getVideoFile().getBytes(), resourceTypeToUpload);
                            if (!videoUrl.equals(existingLecture.getVideoUrl())) {
                                if(existingLecture.getVideoUrl() != null){
                                    deleteFile(cloudinary, existingLecture.getVideoUrl());
                                }
                                lecture.setVideoUrl(videoUrl);
                            }
                        }
                        if (lecture.getDocumentFile() != null && !lecture.getDocumentFile().isEmpty()) {
                            String resourceTypeToUpload = FileUtils.getResourceTypeForCloudinaryUpload(lecture.getDocumentFile());
                            String documentUrl = uploadFile(cloudinary, lecture.getDocumentFile().getBytes(), resourceTypeToUpload);
                            if (!documentUrl.equals(existingLecture.getDocumentUrl())) {
                                if(existingLecture.getDocumentUrl() != null){
                                    deleteFile(cloudinary, existingLecture.getDocumentUrl());
                                }
                                lecture.setDocumentUrl(documentUrl);
                            }
                        }
                    }
                }

                for (Resource resource : chapter.getResourceList()) {
                    Resource existingResource = existingCourse.getChapterList().stream()
                            .flatMap(ch -> ch.getResourceList().stream())
                            .filter(r -> r.getId().equals(resource.getId()))
                            .findFirst().orElse(null);

                    if (existingResource == null) {
                        if (resource.getResourceFile() != null && !resource.getResourceFile().isEmpty()) {
                            String resourceTypeToUpload = FileUtils.getResourceTypeForCloudinaryUpload(resource.getResourceFile());
                            String resourceUrl = uploadFile(cloudinary, resource.getResourceFile().getBytes(), resourceTypeToUpload);
                            resource.setUrl(resourceUrl);
                        }
                    } else {
                        if (resource.getResourceFile() != null && !resource.getResourceFile().isEmpty()) {
                            String resourceTypeToUpload = FileUtils.getResourceTypeForCloudinaryUpload(resource.getResourceFile());
                            String resourceUrl = uploadFile(cloudinary, resource.getResourceFile().getBytes(), resourceTypeToUpload);
                            if (!resourceUrl.equals(existingResource.getUrl())) {
                                if(existingResource.getUrl() != null){
                                    deleteFile(cloudinary, existingResource.getUrl());
                                }
                                resource.setUrl(resourceUrl);
                            }
                        }
                    }
                }
            }

            for (Integer deletedChapterId : deletedChapterIds) {
                Chapter deletedChapter = existingCourse.getChapterList().stream()
                        .filter(chapter -> chapter.getId().equals(deletedChapterId))
                        .findFirst().orElse(null);

                if (deletedChapter != null) {
                    for (Lecture deletedLecture : deletedChapter.getLectureList()) {
                        if (deletedLecture.getVideoUrl() != null) {
                            deleteFile(cloudinary, deletedLecture.getVideoUrl());
                        }
                        if (deletedLecture.getDocumentUrl() != null) {
                            deleteFile(cloudinary, deletedLecture.getDocumentUrl());
                        }
                        for (Resource deletedResource : deletedChapter.getResourceList()) {
                            if (deletedResource.getUrl() != null) {
                                deleteFile(cloudinary, deletedResource.getUrl());
                            }
                        }
                    }
                }
            }
        }

        if(course.getTeacher() == null){
            course.setCourseStatus(CourseStatusEnum.INACTIVE);
        }
        else{
            course.setCourseStatus(CourseStatusEnum.ACTIVE);
        }

        return this.courseRepository.addOrUpdateCourse(course);
    }




    @Override
    @Transactional
    public void deleteCourseById(int courseId) throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("courseId", String.valueOf(courseId));
        List<Course> courses = courseRepository.getCourses(params);

        if (!courses.isEmpty()) {
            Course course = courses.get(0);

            if(course.getChapterList() != null && !course.getChapterList().isEmpty() && course.getId() != null){
                for (Chapter chapter : course.getChapterList()) {
                    for (Lecture lecture : chapter.getLectureList()) {
                        if (lecture.getVideoUrl() != null) {
                            deleteFile(cloudinary, lecture.getVideoUrl());
                        }
                        if (lecture.getDocumentUrl() != null) {
                            deleteFile(cloudinary, lecture.getDocumentUrl());
                        }
                    }

                    for (Resource resource : chapter.getResourceList()) {
                        if (resource.getUrl() != null) {
                            deleteFile(cloudinary, resource.getUrl());
                        }
                    }
                }
            }

            this.courseRepository.deleteCourseById(courseId);
        }

    }


}
