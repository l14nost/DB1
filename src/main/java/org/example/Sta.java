package org.example;


import org.example.Class.User;
import org.example.Config.HibernateUtil;
import org.example.DAOHibernate.OrderDao;
import org.example.DAOHibernate.ProductDao;
import org.example.DAOHibernate.ShoppingCartDao;
import org.example.DAOHibernate.UserDao;
import org.hibernate.Session;

public class Sta {
    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
//        User user = User.builder().id(8).login("Misha").password("misha2").build();
//        UserDao userDao = new UserDao();
//        System.out.println(userDao.get());
//        UserDetails userDetails = UserDetails.builder().idUser(5).name("Misha").surname("Petrov").build();
//        UserDetailsDao userDetailsDao = new UserDetailsDao();
//        userDetailsDao.delete(5);
//        System.out.println(userDetailsDao.get());
//        Product product = Product.builder().name("Tea").price(60).build();
//        ProductDao productDao = new ProductDao();
//        System.out.println(productDao.getById(3));
//        ShoppingCart shoppingCart = ShoppingCart.builder().idUser(1).idProduct(5).count(10).build();

//        Product product = Product.builder().idProducts(1).build();
//        ShoppingCartDao shoppingCartDao = new ShoppingCartDao();
//        System.out.println(shoppingCartDao.getAllProduct(user));
//shoppingCartDao.addProduct(user, Product.builder().idProducts(4).build(),10);
//        OrderDao orderDao = new OrderDao();
//        orderDao.saveOrder(user);
//        System.out.println(orderDao.allUserOrder(user));
        User user = User.builder().id(1).login("Amir").password("amir1").build();
        User user1 = User.builder().id(2).login("Amir").password("amir1").build();

        ProductDao productDao = new ProductDao();
        ShoppingCartDao shoppingCartDao = new ShoppingCartDao();
        UserDao userDao = new UserDao();

//        OrderDao orderDao = new OrderDao();
//        User user1 = User.builder().id(1).build();
//        orderDao.saveOrder(user1);
//        userDao.addOrders(user);
//        shoppingCartDao.removeProducts(userDao.getById(1),productDao.getById(5));
//        shoppingCartDao.removeAllProducts(userDao.getById(1));
//        shoppingCartDao.addProducts(userDao.getById(1),productDao.getById(4));
//        shoppingCartDao.addProducts(userDao.getById(1),productDao.getById(5));
//        shoppingCartDao.addProducts(userDao.getById(2),productDao.getById(6));
//        shoppingCartDao.addProducts(userDao.getById(2),productDao.getById(7));
//        shoppingCartDao.removeProducts(userDao.getById(1),productDao.getById(4));
//        shoppingCartDao.removeAllProducts(user);
//        System.out.println(shoppingCartDao.getAllProduct(user1));
        OrderDao orderDao = new OrderDao();
        orderDao.saveOrder(user1);
//        System.out.println(orderDao.allUserOrder(user1));

//        System.out.println(shoppingCartDao.getAllProduct(user1));
        session.close();


    }
}
