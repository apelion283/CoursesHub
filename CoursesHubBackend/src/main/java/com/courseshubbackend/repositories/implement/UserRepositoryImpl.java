package com.courseshubbackend.repositories.implement;

import com.courseshubbackend.pojos.User;
import com.courseshubbackend.repositories.UserRepository;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
}
