package org.example.DAOHibernate;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.Class.UserDetails;
import org.example.Config.HibernateUtil;
import org.example.DAO.DAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDetailsDao implements DAO<UserDetails> {
    private  SessionFactory sessionFactory;
    public UserDetailsDao() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public void create(UserDetails userDetails) {
        Session session =sessionFactory.openSession();
        session.save(userDetails);
        session.beginTransaction().commit();
        session.close();

    }

    @Override
    public List<UserDetails> get() {
        Session session =sessionFactory.openSession();
        session.get(UserDetails.class,1);
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<UserDetails> userRoot = criteriaQuery.from(UserDetails.class);
        criteriaQuery.select(userRoot);
        Query query =session.createQuery(criteriaQuery);
        List<UserDetails> list = query.getResultList();
        session.close();

        return list;
    }

    @Override
    public UserDetails getById(int id) {
        UserDetails userDetails;
        Session session =sessionFactory.openSession();
        userDetails = session.get(UserDetails.class,id);
        session.beginTransaction().commit();
        session.close();

        return userDetails;
    }

    @Override
    public void delete(int id) {
        Session session =sessionFactory.openSession();
        session.delete(UserDetails.builder().idUser(id).build());
        session.beginTransaction().commit();
        session.close();

    }

    @Override
    public void update(UserDetails userDetails) {
        Session session =sessionFactory.openSession();
        session.update(userDetails);
        session.beginTransaction().commit();
        session.close();

    }
}
