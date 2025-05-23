package com.courseshubbackend.services.implement;

import com.cloudinary.Cloudinary;
import com.courseshubbackend.pojos.Teacher;
import com.courseshubbackend.repositories.TeacherRepository;
import com.courseshubbackend.services.TeacherService;
import com.courseshubbackend.utils.CloudinaryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    Cloudinary cloudinary;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<Teacher> getTeachers(Map<String, String> params) {
        return this.teacherRepository.getTeachers(params);
    }

    @Override
    public Teacher addOrUpdateTeacher(Teacher teacher) {
        Teacher existingTeacher = null;
        String oldAvatarUrl = null;

        if (teacher.getId() != null) {
            existingTeacher = this.teacherRepository.getTeachers(Map.of("teacherId", String.valueOf(teacher.getId()))).get(0);
        }

        if (existingTeacher != null) {
            teacher.setId(existingTeacher.getId());
            if (teacher.getUser().getPassword() == null || teacher.getUser().getPassword().isEmpty()) {
                teacher.getUser().setPassword(existingTeacher.getUser().getPassword());
            }
            teacher.getUser().setUserRole(existingTeacher.getUser().getUserRole());
            oldAvatarUrl = existingTeacher.getAvatarUrl() != null ? existingTeacher.getAvatarUrl() : "" ;

            if(teacher.getUser().getPassword() != null && !teacher.getUser().getPassword().isEmpty() && !teacher.getUser().getPassword().isBlank() && !teacher.getUser().getPassword().equals(existingTeacher.getUser().getPassword())){
                teacher.getUser().setPassword(bCryptPasswordEncoder.encode(teacher.getUser().getPassword()));
            }
        } else {
            teacher.getUser().setPassword(bCryptPasswordEncoder.encode(teacher.getUser().getPassword()));
            teacher.getUser().setUserRole("ROLE_TEACHER");
        }

        if (teacher.getAvatarFile() != null && !teacher.getAvatarFile().isEmpty()) {
            try {
                String uploadedUrl = CloudinaryUtil.uploadFile(cloudinary, teacher.getAvatarFile().getBytes(), "image");
                teacher.setAvatarUrl(uploadedUrl);

                if (oldAvatarUrl != null && !oldAvatarUrl.isEmpty()) {
                    CloudinaryUtil.deleteFile(cloudinary, oldAvatarUrl);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            if (existingTeacher != null) {
                teacher.setAvatarUrl(existingTeacher.getAvatarUrl());
            }
        }

        return this.teacherRepository.addOrUpdateTeacher(teacher);
    }

    @Override
    public void deleteTeacherById(int teacherId) {
        this.teacherRepository.deleteTeacherById(teacherId);
    }

}
