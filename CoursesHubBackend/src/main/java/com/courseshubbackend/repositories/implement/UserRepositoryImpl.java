package com.courseshubbackend.repositories.implement;

import com.courseshubbackend.pojos.User;
import com.courseshubbackend.repositories.UserRepository;
import com.courseshubbackend.utils.DatePredicateBuilderUtil;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    LocalSessionFactoryBean localSessionFactoryBean;

    @Override
    public User getUserByUsername(String username) {
        Session session = this.localSessionFactoryBean.getObject().getCurrentSession();
        Query query = session.createNamedQuery("User.findByUsername", User.class);
        query.setParameter("username", username);

        return (User) query.getSingleResult();
    }

    @Override
    public int countUsers(Map<String, String> params) {
        Session session = this.localSessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root root = query.from(User.class);
        query.select(builder.count(root));

        if(params != null){
            List<Predicate> predicates = new ArrayList<>();
            if(params.containsKey("userRole")){
                predicates.add(builder.equal(root.get("userRole"), params.get("userRole")));
            }

            DatePredicateBuilderUtil.addDatePredicates(builder, root, params, predicates);

            query.where(predicates.toArray(Predicate[] :: new));
        }
        return session.createQuery(query).getSingleResult().intValue();
    }
}
