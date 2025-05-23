package com.courseshubbackend.controllers;

import com.courseshubbackend.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teachers")
public class ApiTeacherController {

    @Autowired
    private TeacherService teacherService;

    @DeleteMapping("/{teacherId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTeacherById(@PathVariable("teacherId") int teacherId) {
        this.teacherService.deleteTeacherById(teacherId);
    }
}
