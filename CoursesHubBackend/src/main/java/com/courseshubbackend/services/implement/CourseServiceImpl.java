package com.courseshubbackend.services.implement;

import com.courseshubbackend.pojos.Course;
import com.courseshubbackend.repositories.CourseRepository;
import com.courseshubbackend.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public int countCourses(Map<String, String> params) {
        return this.courseRepository.countCourses(params);
    }

    @Override
    public List<Course> getCourses(Map<String, String> params) {
        return this.courseRepository.getCourses(params);
    }
}
