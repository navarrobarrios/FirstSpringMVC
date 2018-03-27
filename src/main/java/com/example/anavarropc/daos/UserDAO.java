package com.example.anavarropc.daos;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import com.example.anavarropc.beans.User;
import com.example.anavarropc.config.HibernateConfig;
import com.example.anavarropc.interfaces.IUserDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class UserDAO implements IUserDAO {

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getAllUsers() {
        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from User" );
        List list = query.list();
        List<User> users = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            users.add((User) (list.get(0)));
        }
        session.close();
        return users;
    }

    @Override
    public User getUserById(Integer id) {
        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.close();
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery(String.format("from User where username ='%s'",username) );
        List list = query.list();
        User user = null;
        if(list.size() != 0){
            user = (User) (list.get(0));
        }
        session.close();
        return user;
    }

    @Override
    public void saveUser(User user) {
        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(user);
    }

    @Override
    public void updateUser(User user) {
        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(user);
        session.close();
    }

    @Override
    public void deleteUser(User user) {
        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.delete(user);
        session.close();
    }
}
