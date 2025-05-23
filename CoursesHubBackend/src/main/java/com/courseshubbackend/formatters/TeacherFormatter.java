package com.courseshubbackend.formatters;

import com.courseshubbackend.pojos.Teacher;
import org.springframework.format.Formatter;

import java.util.Locale;

public class TeacherFormatter implements Formatter<Teacher> {
    @Override
    public Teacher parse(String teacherId, Locale locale) {
        Teacher teacher = new Teacher();
        teacher.setId(Integer.parseInt(teacherId));
        return teacher;
    }

    @Override
    public String print(Teacher teacher, Locale locale) {
        return String.valueOf(teacher.getId());
    }
}
