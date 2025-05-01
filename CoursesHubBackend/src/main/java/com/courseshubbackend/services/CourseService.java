package com.courseshubbackend.services;

import com.courseshubbackend.pojos.Course;

import java.util.List;
import java.util.Map;

public interface CourseService {
    int countCourses(Map<String, String> params);
    List<Course> getCourses(Map<String, String> params);
}
