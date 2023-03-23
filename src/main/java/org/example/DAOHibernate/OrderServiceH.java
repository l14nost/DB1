package org.example.DAOHibernate;

import org.example.Class.Order;
import org.example.Class.User;
import org.example.Config.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class OrderServiceH {
    private SessionFactory sessionFactory;
    public OrderServiceH() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }


//    public void create(User user){
//        Session session = sessionFactory.openSession();
//        ShoppingCartDao shoppingCartDao = new ShoppingCartDao();
//        ProductDao productDao = new ProductDao();
//        StringBuilder sb = new StringBuilder();
//        int sum=0;
//
//        for(int i = 0 ; i<shoppingCartDao.getAllProduct(user).size();i++){
//            for(int j = 0; j<productDao.get().size();j++) {
//                if (shoppingCartDao.getAllProduct(user).get(i).getName().equals(productDao.get().get(j).getName())) {
//                    sb.append(productDao.get().get(i).getName()).append(",");
//                    sum += productDao.get().get(i).getPrice();
//                }
//            }
//        }
//        sb.deleteCharAt(sb.length()-1);
//        String res =  sb.toString();
//        Order order = Order.builder().idUser(user.getId()).order(res).sum(sum).build();
//        session.save(order);
//        session.beginTransaction().commit();
//        session.close();
//    }
    public void createAnnotation(User user){

        Session session =sessionFactory.openSession();
        ShoppingCartDao shoppingCartDao = new ShoppingCartDao();
        ProductDao productDao = new ProductDao();
        StringBuilder sb = new StringBuilder();
        int sum = 0;
        user = session.get(User.class,user.getId());
        Hibernate.initialize(user.getOrders());
        for(int i = 0 ; i<shoppingCartDao.getAllProduct(user).size();i++){
            for(int j = 0; j<productDao.get().size();j++) {
                if (shoppingCartDao.getAllProduct(user).get(i).getName().equals(productDao.get().get(j).getName())) {
                    sb.append(productDao.get().get(i).getName()).append(",");
                    sum += productDao.get().get(i).getPrice();
                }
            }
        }

        sb.deleteCharAt(sb.length()-1);
        String res =  sb.toString();
        Order order = Order.builder().idUser(user.getId()).order(res).sum(sum).build();
        user.addOrder(order);
        session.save(user);
        session.save(order);
        session.beginTransaction().commit();
        session.close();
    }
//    public void addOrder(User user,Order order){
//        Session session = sessionFactory.openSession();
//        user = session.get(User.class,user.getId());
//        Hibernate.initialize(user.getOrders());
//
//        user.getOrders().add(order);
//        session.save(user);
//        session.save(product);
//        session.beginTransaction().commit();
//        session.close();
//    }
}
