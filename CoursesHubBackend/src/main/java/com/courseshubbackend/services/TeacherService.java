package com.courseshubbackend.services;

import com.courseshubbackend.pojos.Teacher;

import java.util.List;
import java.util.Map;

public interface TeacherService {
    List<Teacher> getTeachers(Map<String, String> params);
    Teacher addOrUpdateTeacher(Teacher teacher);
    void deleteTeacherById(int teacherId);
}
