package com.courseshubbackend.repositories;

import com.courseshubbackend.pojos.Course;

import java.util.List;
import java.util.Map;

public interface CourseRepository {
    int countCourses(Map<String, String> params);
    List<Course> getCourses(Map<String, String> params);
    Course addOrUpdateCourse(Course course);
}
