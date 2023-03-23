package org.example.DaoJdbc;

import org.example.Class.Order;
import org.example.Class.User;
import org.example.Config.JDBCUtils;
import org.example.DAO.OrderDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderMySQL implements OrderDao {
    @Override
    public void saveOrder(User user) {
        OrderService orderService = new OrderService();
        orderService.create(user);
    }

    @Override
    public List<Order> allUserOrder(User user) {
        List<Order> list = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        String insert = "SELECT * FROM orders WHERE idUser = ?";
        try {
            preparedStatement = JDBCUtils.getConnection().prepareStatement(insert);
            preparedStatement.setInt(1,user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                list.add(Order.builder().idOrder(resultSet.getInt("idOrders")).idUser(resultSet.getInt("idUser")).order(resultSet.getString("products")).sum(resultSet.getInt("sum")).build());
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                JDBCUtils.getConnection().close();
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }

    @Override
    public List<Order> allOrder() {
        List<Order> list = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        String insert = "SELECT * FROM orders";
        try {
            preparedStatement = JDBCUtils.getConnection().prepareStatement(insert);
            ResultSet resultSet = preparedStatement.executeQuery(insert);


            while (resultSet.next()) {
                list.add(Order.builder().idOrder(resultSet.getInt("idOrders")).idUser(resultSet.getInt("idUser")).order(resultSet.getString("products")).sum(resultSet.getInt("sum")).build());
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                JDBCUtils.getConnection().close();
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }
}
