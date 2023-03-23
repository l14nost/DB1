package org.example;

import org.example.Class.User;
import org.example.DaoJdbc.OrderMySQL;

public class Main {
    public static void main(String[] args) {
        User user = User.builder().id(1).build();
//        UserMySQL userMySQL = new UserMySQL();
//        UserDetails userDetails = UserDetails.builder().idUser(3).name("Andrey").surname("Pashkov").build();
//        UserDetailsMySQL userDetailsMySQL = new UserDetailsMySQL();
//        userDetailsMySQL.create(userDetails);
//        Product product  = Product.builder().idProducts(2).name("Milk").price(1000).build();
//        ProductMySQL productMySQL = new ProductMySQL();
//        System.out.println(productMySQL.get());
//        ShoppingCart shoppingCart = ShoppingCart.builder().build();
//        ShoppingCartMySQL shoppingCartMySQL = new ShoppingCartMySQL();
//        System.out.println(shoppingCartMySQL.getAllProduct(user));
//        User user = User.builder().id(1).build();
//        Product product = Product.builder().idProducts(4).build();
//        OrderMySQL orderMySQL = new OrderMySQL();
//        orderMySQL.saveOrder(user);


//        System.out.println(orderMySQL.allUserOrder(user));
        OrderMySQL orderMySQL = new OrderMySQL();
        orderMySQL.saveOrder(user);
//        User user = User.builder().id(1).build();
        System.out.println(orderMySQL.allUserOrder(user));



    }
}
