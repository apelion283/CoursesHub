package com.courseshubbackend.controllers;

import com.courseshubbackend.pojos.Teacher;
import com.courseshubbackend.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@ControllerAdvice
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @ModelAttribute
    public void commonResponse(org.springframework.ui.Model model){
        model.addAttribute("teachers", this.teacherService.getTeachers(null));
    }

    @GetMapping
    public String teachersView() {
        return "teachers/teachers";
    }

    @RequestMapping("/add")
    public String addTeacherView(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "teachers/teacher-detail";
    }

    @RequestMapping("/{teacherId}")
    public String teacherDetailView(Model model, @PathVariable(value = "teacherId") int teacherId) {
        Map<String, String> params = new HashMap<>();
        params.put("teacherId", String.valueOf(teacherId));
        Teacher teacher = this.teacherService.getTeachers(params).get(0);
        if (teacher == null) {}
        model.addAttribute("teacher", this.teacherService.getTeachers(params).get(0));
        return "teachers/teacher-detail";
    }

    @PostMapping("/add")
    public String addTeacher(@ModelAttribute("teacher") Teacher teacher, Model model) {
        try {
            if (teacher.getId() == null) {
                this.teacherService.addOrUpdateTeacher(teacher);
                return "redirect:/teachers";
            } else {
                model.addAttribute("error", "Teacher already exists");
                return "teachers/teacher-detail";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Error saving teacher: " + e.getMessage());
            return "teachers/teacher-detail";
        }
    }


    @PostMapping("/update")
    public String updateTeacher(@ModelAttribute("teacher") Teacher teacher, Model model) {
        try {
            if (teacher.getId() != null) {
                this.teacherService.addOrUpdateTeacher(teacher);
                return "redirect:/teachers";
            } else {
                model.addAttribute("error", "Teacher does not exist");
                return "teachers/teacher-detail";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Error updating teacher: " + e.getMessage());
            return "teachers/teacher-detail";
        }
    }

}
