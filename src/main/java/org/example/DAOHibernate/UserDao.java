package org.example.DAOHibernate;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.Class.User;
import org.example.Config.HibernateUtil;
import org.example.DAO.DAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDao implements DAO<User> {
    private SessionFactory sessionFactory;
    public UserDao() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public void create(User user) {
        Session session =sessionFactory.openSession();
        session.save(user);
        session.beginTransaction().commit();
        session.close();
        sessionFactory.close();
    }

    @Override
    public List<User> get() {
        Session session =sessionFactory.openSession();
        session.get(User.class,1);
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery.select(userRoot);
        Query query =session.createQuery(criteriaQuery);
        List<User> list = query.getResultList();
        session.close();
        return list;
    }

    @Override
    public User getById(int id) {
        User user;
        Session session =sessionFactory.openSession();
        user = session.get(User.class,id);
        session.beginTransaction().commit();
        session.close();

        return user;
    }

    @Override
    public void delete(int id) {
        Session session =sessionFactory.openSession();
        session.delete(User.builder().id(id).build());
        session.beginTransaction().commit();
        session.close();

    }

    @Override
    public void update(User user) {
        Session session =sessionFactory.openSession();
        session.update(user);
        session.beginTransaction().commit();
        session.close();

    }
//    public void addOrders(User user){
//        Session session =sessionFactory.openSession();
//        OrderDao orderDao = new OrderDao();
//        orderDao.saveOrder(user);
//        List<Order> orders = new ArrayList<>();
//        for(int i= 0 ;i<orderDao.allUserOrder(user).size();i++){
//            orders.add(orderDao.allUserOrder(user).get(i));
//        }
//        user.setOrders(orders);
//        session.save(user);
//        session.beginTransaction().commit();
//        session.close();
//
//    }
//    public void addProducts(User user, Product product){
//        Session session = sessionFactory.openSession();
//        List<Product> products = new ArrayList<>();
//        products.add(product);
//        user.setProducts(products);
//        session.update(user);
//        session.beginTransaction().commit();
//        session.close();
//    }

}
