package org.example.DAOHibernate;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.Class.Order;
import org.example.Class.User;
import org.example.Config.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class OrderDao implements org.example.DAO.OrderDao {
    //+log
    private SessionFactory sessionFactory;
    public OrderDao() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public void saveOrder(User user) {
        Session session = sessionFactory.openSession();

        OrderServiceH orderServiceH = new OrderServiceH();
        orderServiceH.createAnnotation(user);
        session.close();



    }

    @Override
    public List<Order> allUserOrder(User user) {
//        Session session =sessionFactory.openSession();
//        session.get(User.class,1);
//        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
//        Root<Order> userRoot = criteriaQuery.from(Order.class);
//        criteriaQuery.select(userRoot);
//        Query query =session.createQuery(criteriaQuery);
//        List<Order> list = query.getResultList();
//        List<Order> result = new ArrayList<>();
//        for(int i = 0 ; i< list.size();i++){
//            if (list.get(i).getIdUser() == user.getId()){
//                result.add(list.get(i));
//            }
//        }
//        session.close();
//
//        return result;
        Session session =sessionFactory.openSession();
        user = session.get(User.class,user.getId());
        Hibernate.initialize(user.getOrders());
        List<Order> orders = new ArrayList<>();
        for(int i = 0; i<user.getOrders().size();i++){
            orders.add(user.getOrders().get(i));
        }
        session.close();
        return orders;
    }

    @Override
    public List<Order> allOrder() {
        Session session =sessionFactory.openSession();
        session.get(Order.class,1);
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<Order> userRoot = criteriaQuery.from(Order.class);
        criteriaQuery.select(userRoot);
        Query query =session.createQuery(criteriaQuery);
        List<Order> list = query.getResultList();
        session.close();

        return list;
    }

}
