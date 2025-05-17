package com.courseshubbackend.controllers;

import com.courseshubbackend.pojos.course.Course;
import com.courseshubbackend.pojos.Chapter;
import com.courseshubbackend.pojos.Lecture;
import com.courseshubbackend.pojos.Resource;
import com.courseshubbackend.pojos.course.CourseStatusEnum;
import com.courseshubbackend.services.CourseService;
import com.courseshubbackend.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/courses")
public class CourseController {
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CourseService courseService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    @Transactional
    public String coursesView(Model model, @RequestParam(required = false) Map<String, String> params) {

        List<Course> courses = this.courseService.getCourses(params);

        model.addAttribute("activatingCourses", courses.stream().filter(course ->
                course.getCourseStatus().equals(CourseStatusEnum.ACTIVE)).collect(Collectors.toList()));
        model.addAttribute("inActivatingCourses", courses.stream().filter(course ->
                course.getCourseStatus().equals(CourseStatusEnum.INACTIVE)).collect(Collectors.toList()));
        return "courses/courses";
    }

    @GetMapping("/add")
    @Transactional
    public String addCourseView(Model model){
        logger.info("Viewing add course form");
        Course course = new Course();

        model.addAttribute("course", course);
        return "courses/course-detail";
    }

    @GetMapping("/{courseId}")
    @Transactional
    public String courseDetailView(Model model, @PathVariable(value = "courseId") int courseId){
        Map<String, String> params = new HashMap<>();
        params.put("courseId", String.valueOf(courseId));
        model.addAttribute("course", this.courseService.getCourses(params).get(0));
        return "courses/course-detail";
    }

    @PostMapping("/add")
    @Transactional
    public String addCourse(@ModelAttribute("course") Course course, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", this.categoryService.getCategories());
            return "courses/course-detail";
        }

        try {
            for (var chapter : course.getChapterList()) {

                if (chapter.getLectureList() == null) {
                    chapter.setLectureList(new ArrayList<>());
                }
                if (chapter.getResourceList() == null) {
                    chapter.setResourceList(new ArrayList<>());
                }
                
                chapter.setCourse(course);
                
                for (var lecture : chapter.getLectureList()) {
                    lecture.setChapter(chapter);
                }
                
                for (var resource : chapter.getResourceList()) {
                    resource.setChapter(chapter);
                }
            }
            

            this.courseService.addOrUpdateCourse(course);
            return "redirect:/courses";
        } catch (Exception e) {
            model.addAttribute("error", "Error saving course: " + e.getMessage());
            model.addAttribute("categories", this.categoryService.getCategories());
            return "courses/course-detail";
        }
    }

    @PostMapping("/update")
    @Transactional
    public String updateCourse(@ModelAttribute("course") Course course, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", this.categoryService.getCategories());
            return "courses/course-detail";
        }

        try {
            if (course.getChapterList() != null) {
                for (Chapter chapter : course.getChapterList()) {
                    chapter.setCourse(course);

                    if (chapter.getLectureList() != null) {
                        for (Lecture lecture : chapter.getLectureList()) {
                            lecture.setChapter(chapter);
                        }
                    }

                    if (chapter.getResourceList() != null) {
                        for (Resource resource : chapter.getResourceList()) {
                            resource.setChapter(chapter);
                        }
                    }
                }
                course.setChapterList(
                        course.getChapterList().stream()
                                .filter(ch -> !(ch.getId() == null && (ch.getName() == null || ch.getName().isEmpty())))
                                .collect(Collectors.toList())
                );
            }

            this.courseService.addOrUpdateCourse(course);
            return "redirect:/courses";
            
        } catch (Exception e) {
            model.addAttribute("error", "Error updating course: " + e.getMessage());
            model.addAttribute("categories", this.categoryService.getCategories());
            return "courses/course-detail";
        }
    }
}
