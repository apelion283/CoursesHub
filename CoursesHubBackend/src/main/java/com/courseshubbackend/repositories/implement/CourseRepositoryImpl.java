package com.courseshubbackend.repositories.implement;

import com.courseshubbackend.pojos.Course;
import com.courseshubbackend.repositories.CourseRepository;
import com.courseshubbackend.utils.DatePredicateBuilderUtil;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Course> query = builder.createQuery(Course.class);
        Root root = query.from(Course.class);
        query.select(root);

        if(params != null){
            List<Predicate> predicates = new ArrayList<>();
            DatePredicateBuilderUtil.addDatePredicates(builder, root, params, predicates);
            if(params.containsKey("courseId")){
                predicates.add(builder.equal(root.get("id"), Integer.parseInt(params.get("courseId"))));
            }

            if(params.containsKey("keyword")){
                predicates.add(builder.like(builder.lower(root.get("name")), "%" + params.get("keyword").toLowerCase() + "%"));
            }

            query.where(predicates.toArray(Predicate[] :: new));
        }

        return session.createQuery(query).getResultList();
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
