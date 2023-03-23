package org.example.DAO;

import org.example.Class.Order;
import org.example.Class.User;

import java.util.List;

public interface OrderDao {
    void saveOrder(User user);
    List<Order> allUserOrder(User user);
    List<Order> allOrder();

}
