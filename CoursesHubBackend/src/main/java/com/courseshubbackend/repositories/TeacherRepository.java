package com.courseshubbackend.repositories;

import com.courseshubbackend.pojos.Teacher;

import java.util.List;
import java.util.Map;

public interface TeacherRepository {
    List<Teacher> getTeachers(Map<String, String> params);
    Teacher addOrUpdateTeacher(Teacher teacher);
    boolean deleteTeacherById(int teacherId);
}
