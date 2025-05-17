package com.courseshubbackend.repositories.implement;

import com.courseshubbackend.pojos.Teacher;
import com.courseshubbackend.pojos.course.Course;
import com.courseshubbackend.pojos.course.CourseStatusEnum;
import com.courseshubbackend.repositories.TeacherRepository;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@Transactional
public class TeacherRepositoryImpl implements TeacherRepository {

    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    @Override
    public List<Teacher> getTeachers(Map<String, String> params) {
        Session session = this.localSessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Teacher> query = builder.createQuery(Teacher.class);
        Root<Teacher> root = query.from(Teacher.class);

        List<Predicate> predicates = new ArrayList<>();


        if(params != null){

            if(params.containsKey("teacherId")
                    && params.get("teacherId") != null
                    && !params.get("teacherId").isEmpty()
                    && !params.get("teacherId").isBlank()
                    && !params.get("teacherId").equals("null")){
                Predicate predicate = builder.equal(root.get("id"), Integer.parseInt(params.get("teacherId")));
                predicates.add(predicate);
            }

        }

        query.select(root).distinct(true).where(predicates.toArray(Predicate[] :: new));

        List<Teacher> teachers =  session.createQuery(query).getResultList();
        teachers.forEach(t -> {
            t.setCourseSet(
                    t.getCourseSet().stream()
                            .filter(c -> c.getCourseStatus() == CourseStatusEnum.ACTIVE
                                    || c.getCourseStatus() == CourseStatusEnum.INACTIVE)
                            .collect(Collectors.toSet())
            );
        });
        return teachers;
    }

    @Override
    public Teacher addOrUpdateTeacher(Teacher teacher) {
        Session session = this.localSessionFactoryBean.getObject().getCurrentSession();
        if(teacher.getId() == null){
            session.persist(teacher);
        }
        else{
            session.merge(teacher);
        }
        return teacher;
    }

    @Override
    public boolean deleteTeacherById(int teacherId) {
        try{
            Session session = this.localSessionFactoryBean.getObject().getCurrentSession();
            Teacher teacher = session.get(Teacher.class, teacherId);

            for(Course course : teacher.getCourseSet()){
                course.setTeacher(null);
                course.setCourseStatus(CourseStatusEnum.INACTIVE);
                session.update(course);
            }

            session.remove(teacher);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
