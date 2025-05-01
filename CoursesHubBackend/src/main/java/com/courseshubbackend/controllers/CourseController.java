package com.courseshubbackend.controllers;

import com.courseshubbackend.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/courses")
    public String coursesView(Model model, @RequestParam(required = false) Map<String, String> params) {
        model.addAttribute("courses", this.courseService.getCourses(params));
        return "courses/courses";
    }
}
