package org.example.DAOHibernate;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.Class.Product;
import org.example.Config.HibernateUtil;
import org.example.DAO.DAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ProductDao implements DAO<Product> {
    private SessionFactory sessionFactory;
    public ProductDao() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public void create(Product product) {
        Session session =sessionFactory.openSession();
        session.save(product);
        session.beginTransaction().commit();
        session.close();
        sessionFactory.close();
    }

    @Override
    public List<Product> get() {
        Session session =sessionFactory.openSession();
        session.get(Product.class,1);
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<Product> product = criteriaQuery.from(Product.class);
        criteriaQuery.select(product);
        Query query =session.createQuery(criteriaQuery);
        List<Product> list = query.getResultList();
        session.close();
        return list;
    }

    @Override
    public Product getById(int id) {
        Product product;
        Session session =sessionFactory.openSession();
        product = session.get(Product.class,id);
        session.beginTransaction().commit();
        session.close();
        return product;
    }

    @Override
    public void delete(int id) {
        Session session =sessionFactory.openSession();
        session.delete(Product.builder().idProducts(id).build());
        session.beginTransaction().commit();
        session.close();

    }

    @Override
    public void update(Product product) {
        Session session =sessionFactory.openSession();
        session.update(product);
        session.beginTransaction().commit();
        session.close();

    }
}
