package com.courseshubbackend.repositories.implement;

import com.courseshubbackend.pojos.course.Course;
import com.courseshubbackend.pojos.course.CourseStatusEnum;
import com.courseshubbackend.repositories.CourseRepository;
import com.courseshubbackend.utils.DatePredicateBuilderUtil;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CourseRepositoryImpl implements CourseRepository {

    @Autowired
    LocalSessionFactoryBean localSessionFactoryBean;

    @Override
    public int countCourses(Map<String, String> params) {
        Session session = this.localSessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root root = query.from(Course.class);
        query.select(builder.count(root));
        if(params != null){
            List<Predicate> predicates = new ArrayList<>();
            DatePredicateBuilderUtil.addDatePredicates(builder, root, params, predicates);
            query.where(predicates.toArray(Predicate[] :: new));
        }

        return session.createQuery(query).getSingleResult().intValue();
    }

    @Override
    public List<Course> getCourses(Map<String, String> params) {
        Session session = this.localSessionFactoryBean.getObject().getCurrentSession();

        if (params != null && params.containsKey("courseId")) {
            int courseId = Integer.parseInt(params.get("courseId"));

            String queryCourseWithChapters = "SELECT DISTINCT c FROM Course c " +
                    "LEFT JOIN FETCH c.chapterList ch " +
                    "WHERE c.id = :courseId " +
                    "AND c.courseStatus IN (:statuses)";

            List<Course> courses = session.createQuery(queryCourseWithChapters, Course.class)
                    .setParameter("courseId", courseId)
                    .setParameter("statuses",
                            Arrays.asList(CourseStatusEnum.ACTIVE, CourseStatusEnum.INACTIVE))
                    .getResultList();

            if (!courses.isEmpty()) {
                Course course = courses.get(0);

                course.getChapterList().forEach(ch -> {
                    ch.getLectureList().size();
                    ch.getTestList().size();
                    ch.getResourceList().size();
                });
            }

            return courses;
        }

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Course> query = builder.createQuery(Course.class);
        Root<Course> root = query.from(Course.class);
        query.select(root);
        List<Predicate> predicates = new ArrayList<>();

        Predicate statusPredicate = root.get("courseStatus").in(CourseStatusEnum.ACTIVE.getValue(), CourseStatusEnum.INACTIVE.getValue());
        predicates.add(statusPredicate);

        if (params != null) {

            DatePredicateBuilderUtil.addDatePredicates(builder, root, params, predicates);

            if (params.containsKey("keyword")) {
                predicates.add(builder.like(
                        builder.lower(root.get("name")),
                        "%" + params.get("keyword").toLowerCase() + "%"
                ));
            }
        }
        query.where(predicates.toArray(Predicate[] :: new));

        return session.createQuery(query).getResultList();
    }


    @Override
    public boolean deleteCourseById(int courseId) {
        try{
            Session session = this.localSessionFactoryBean.getObject().getCurrentSession();
            Course course = session.get(Course.class, courseId);
            if (course != null) {
                course.getChapterList().clear();

                course.setCourseStatus(CourseStatusEnum.DELETED);

                session.update(course);
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Course addOrUpdateCourse(Course course) {
        Session session = this.localSessionFactoryBean.getObject().getCurrentSession();
        if(course.getId() == null){
            session.persist(course);
        }
        else{
            session.merge(course);
        }
        return course;
    }
}
