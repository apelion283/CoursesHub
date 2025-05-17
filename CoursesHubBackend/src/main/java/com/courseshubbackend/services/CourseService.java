package com.courseshubbackend.services;

import com.courseshubbackend.pojos.course.Course;
import org.hibernate.Session;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface CourseService {
    int countCourses(Map<String, String> params);
    List<Course> getCourses(Map<String, String> params);
    Course addOrUpdateCourse(Course course) throws IOException;
    void deleteCourseById(int courseId) throws IOException;
}
