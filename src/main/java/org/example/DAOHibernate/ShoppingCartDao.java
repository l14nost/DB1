package org.example.DAOHibernate;

import org.example.Class.Order;
import org.example.Class.Product;
import org.example.Class.User;
import org.example.Config.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDao {
    private SessionFactory sessionFactory;

    public ShoppingCartDao() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

//    @Override
//    public void addProduct(User user, Product product) {
//        ShoppingCart shoppingCart = ShoppingCart.builder().idUser(user.getId()).idProduct(product.getIdProducts()).build();
//        Session session =sessionFactory.openSession();
//        session.save(shoppingCart);
//        session.beginTransaction().commit();
//        session.close();
//
//    }
//
//    @Override
//    public void removeProduct(User user, Product product) {
//        ShoppingCart shoppingCart = ShoppingCart.builder().idUser(user.getId()).idProduct(product.getIdProducts()).build();
//        Session session =sessionFactory.openSession();
//        session.delete(shoppingCart);
//        session.beginTransaction().commit();
//        session.close();
//
//    }
//
//    @Override
//    public void removeAllProduct(User user) {
//        ShoppingCart shoppingCart = ShoppingCart.builder().idUser(user.getId()).build();
//        Session session =sessionFactory.openSession();
//        session.delete(shoppingCart);
//        session.beginTransaction().commit();
//        session.close();
//
//    }
//
//    @Override
//    public List<String> getAllProduct(User user) {
//        Session session =sessionFactory.openSession();
//        session.get(User.class,1);
//        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
//        Root<ShoppingCart> userRoot = criteriaQuery.from(ShoppingCart.class);
//        criteriaQuery.select(userRoot);
//        Query query =session.createQuery(criteriaQuery);
//        List<Product> list = query.getResultList();
//        List<String> result = new ArrayList<>();
//        for(int i = 0; i< list.size(); i++){
//            result.add(list.get(i).getName());
//        }
//
//
////        Map<String,Integer> result = new HashMap<>();
////        ProductDao productDao = new ProductDao();
////        for(int i=0; i<list.size();i++){
////            if(list.get(i).getIdUser() == user.getId()){
////                result.put(productDao.getById(list.get(i).getIdProduct()).getName(),list.get(i).getCount());
////            }
////        }
//        session.close();
////        session.close();
////        sessionFactory.close();
//        return result;
//    }
//
////    public List<ShoppingCart[]> getAllProduct1(User user) {
////        Session session =sessionFactory.openSession();
////        session.get(ShoppingCart.class,1);
////        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
////        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
////        Root<ShoppingCart> userRoot = criteriaQuery.from(ShoppingCart.class);
////        criteriaQuery.select(userRoot);
////        Query query =session.createQuery(criteriaQuery);
////        List<ShoppingCart[]> rows = query.getResultList();
//////        Map<String, Integer> list = rows.stream().collect(Collectors.toMap(row -> (String)row[0], row -> (Integer) row[1]));
////
////        session.close();
////        sessionFactory.close();
////        return rows;
////    }
public void addOrders(User user){
    Session session =sessionFactory.openSession();
    OrderDao orderDao = new OrderDao();
    orderDao.saveOrder(user);
    List<Order> orders = new ArrayList<>();
    for(int i= 0 ;i<orderDao.allUserOrder(user).size();i++){
        orders.add(orderDao.allUserOrder(user).get(i));
    }
    user.setOrders(orders);
    session.save(user);
    session.beginTransaction().commit();
    session.close();

}
    public void addProducts(User user, Product product){
        Session session = sessionFactory.openSession();
        user = session.get(User.class,user.getId());
        Hibernate.initialize(user.getProducts());
        product = session.get(Product.class,product.getIdProducts());
        user.getProducts().add(product);
        session.save(user);
        session.save(product);
        session.beginTransaction().commit();
        session.close();
    }
    public List<Product> getAllProduct(User user){
        Session session =sessionFactory.openSession();
        user = session.get(User.class,user.getId());
        Hibernate.initialize(user.getProducts());
        List<Product> rows = new ArrayList<>();
        for(int i = 0; i<user.getProducts().size();i++){
            rows.add(user.getProducts().get(i));
        }
//        session.get(User.class,user.getId());
//        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
//        Root<ShoppingCart> userRoot = criteriaQuery.from(User.class);
//        criteriaQuery.select(userRoot);
//        Query query =session.createQuery(criteriaQuery);
//        List<Product> rows = query.getResultList();
//
////        Map<String, Integer> list = rows.stream().collect(Collectors.toMap(row -> (String)row[0], row -> (Integer) row[1]));
//
        session.close();
//        sessionFactory.close();
        return rows;

    }
    public void removeProducts(User user, Product product){
        Session session = sessionFactory.openSession();
        user = session.get(User.class,user.getId());
        Hibernate.initialize(user.getProducts());
        product = session.get(Product.class,product.getIdProducts());
        user.getProducts().remove(product);
        session.update(user);
        session.update(product);
        session.beginTransaction().commit();
        session.close();
    }
    public void removeAllProducts(User user){
        Session session = sessionFactory.openSession();
        user = session.get(User.class,user.getId());
        Hibernate.initialize(user.getProducts());
        user.getProducts().removeAll(user.getProducts());
        session.update(user);
        session.beginTransaction().commit();
        session.close();
    }
}
