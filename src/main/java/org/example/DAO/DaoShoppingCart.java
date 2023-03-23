package org.example.DAO;

import org.example.Class.Product;
import org.example.Class.User;

import java.util.List;

public interface DaoShoppingCart {
    void addProduct(User user, Product product);
    void removeProduct(User user, Product product);
    void removeAllProduct(User user);
    List<String> getAllProduct(User user);

}
