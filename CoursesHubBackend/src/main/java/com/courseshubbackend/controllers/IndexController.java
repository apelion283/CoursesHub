/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.courseshubbackend.controllers;

import com.courseshubbackend.services.CourseService;
import com.courseshubbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Apelion283
 */
@Controller
public class IndexController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(Model model){
        Calendar calendar = Calendar.getInstance();
        int thisMonth = calendar.get(Calendar.MONTH) + 1; // 0-based → +1
        int thisYear = calendar.get(Calendar.YEAR);

        calendar.add(Calendar.MONTH, -1);
        int lastMonth = calendar.get(Calendar.MONTH) + 1;
        int lastYear = calendar.get(Calendar.YEAR);

        // Count Students number
        Map<String, String> studentParams = Map.of("userRole", "ROLE_STUDENT");
        int numberOfStudents = this.userService.countUsers(studentParams);

        Map<String, String> studentThisMonthParams = new HashMap<>();
        studentThisMonthParams.put("userRole", "ROLE_STUDENT");
        studentThisMonthParams.put("createdMonth", String.valueOf(thisMonth));
        studentThisMonthParams.put("createdYear", String.valueOf(thisYear));
        int numberOfNewStudentsThisMonth = this.userService.countUsers(studentThisMonthParams);

        Map<String, String> studentLastMonthParams = new HashMap<>();
        studentLastMonthParams.put("userRole", "ROLE_STUDENT");
        studentLastMonthParams.put("createdMonth", String.valueOf(lastMonth));
        studentLastMonthParams.put("createdYear", String.valueOf(lastYear));
        int numberOfNewStudentsLastMonth = this.userService.countUsers(studentLastMonthParams);

        // Count Teachers number
        Map<String, String> teacherParams = Map.of("userRole", "ROLE_TEACHER");
        int numberOfTeachers = this.userService.countUsers(teacherParams);

        Map<String, String> teacherThisMonthParams = new HashMap<>();
        teacherThisMonthParams.put("userRole", "ROLE_TEACHER");
        teacherThisMonthParams.put("createdMonth", String.valueOf(thisMonth));
        teacherThisMonthParams.put("createdYear", String.valueOf(thisYear));
        int numberOfNewTeachersThisMonth = this.userService.countUsers(teacherThisMonthParams);

        Map<String, String> teacherLastMonthParams = new HashMap<>();
        teacherLastMonthParams.put("userRole", "ROLE_TEACHER");
        teacherLastMonthParams.put("createdMonth", String.valueOf(lastMonth));
        teacherLastMonthParams.put("createdYear", String.valueOf(lastYear));
        int numberOfNewTeachersLastMonth = this.userService.countUsers(teacherLastMonthParams);

        // Count Courses number
        int numberOfCourses = this.courseService.countCourses(null);

        Map<String, String> courseThisMonthParams = new HashMap<>();
        courseThisMonthParams.put("createdMonth", String.valueOf(thisMonth));
        courseThisMonthParams.put("createdYear", String.valueOf(thisYear));
        int numberOfNewCoursesThisMonth = this.courseService.countCourses(courseThisMonthParams);

        Map<String, String> courseLastMonthParams = new HashMap<>();
        courseLastMonthParams.put("createdMonth", String.valueOf(lastMonth));
        courseLastMonthParams.put("createdYear", String.valueOf(lastYear));
        int numberOfNewCoursesLastMonth = this.courseService.countCourses(courseLastMonthParams);

        model.addAttribute("numberOfStudents", numberOfStudents);
        model.addAttribute("numberOfNewStudentsThisMonth", numberOfNewStudentsThisMonth);
        model.addAttribute("numberOfNewStudentsLastMonth", numberOfNewStudentsLastMonth);
        model.addAttribute("numberOfTeachers", numberOfTeachers);
        model.addAttribute("numberOfNewTeachersThisMonth", numberOfNewTeachersThisMonth);
        model.addAttribute("numberOfNewTeachersLastMonth", numberOfNewTeachersLastMonth);
        model.addAttribute("numberOfCourses", numberOfCourses);
        model.addAttribute("numberOfNewCoursesThisMonth", numberOfNewCoursesThisMonth);
        model.addAttribute("numberOfNewCoursesLastMonth", numberOfNewCoursesLastMonth);

        return "index";
    }
 
}
