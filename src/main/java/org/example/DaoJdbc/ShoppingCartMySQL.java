package org.example.DaoJdbc;

import org.example.Class.Product;
import org.example.Class.User;
import org.example.Config.JDBCUtils;
import org.example.DAO.DaoShoppingCart;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartMySQL implements DaoShoppingCart {

    @Override
    public void addProduct(User user, Product product) {
        PreparedStatement preparedStatement = null;
        String insert = "INSERT INTO shopingcart(idUser,idProduct)VALUES(?,?)";
        try {
            preparedStatement = JDBCUtils.getConnection().prepareStatement(insert);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, product.getIdProducts());

            preparedStatement.executeUpdate();
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

    }
    @Override
    public List<String> getAllProduct(User user) {
        List<String> list = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        String insert = "SELECT p.name " +
                "FROM shopingcart c " +
                "JOIN products p ON c.idProduct = p.idProducts " +
                "WHERE c.idUser = ?";
        try {
            preparedStatement = JDBCUtils.getConnection().prepareStatement(insert);
            preparedStatement.setInt(1,user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();



            while (resultSet.next()) {
                list.add(resultSet.getString("name"));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                JDBCUtils.getConnection().close();
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }
//    @Override
//    public List<Product> getAllProduct(User user) {
//        List<Product> list = new ArrayList<>();
//        PreparedStatement preparedStatement = null;
//        String insert = "SELECT p.name " +
//                "FROM shopingcart c " +
//                "JOIN products p ON c.idProduct = p.idProducts " +
//                "WHERE c.idUser = ?";
//        try {
//            preparedStatement = DBUtils.getConnection().prepareStatement(insert);
//            preparedStatement.setInt(1,user.getId());
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//
//
//            while (resultSet.next()) {
//                list.add(Product.builder().name(resultSet.getString("name")).build());
//            }
//
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        finally {
//            try {
//                DBUtils.getConnection().close();
//                preparedStatement.close();
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        return list;
//    }
    @Override
    public void removeProduct(User user, Product product) {
        PreparedStatement preparedStatement = null;
        String insert = "DELETE FROM shopingcart WHERE idUser = ? AND idProduct = ?";
        try {
            preparedStatement = JDBCUtils.getConnection().prepareStatement(insert);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, product.getIdProducts());

            preparedStatement.executeUpdate();

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

    }
    @Override
    public void removeAllProduct(User user) {
        PreparedStatement preparedStatement = null;
        String insert = "DELETE FROM shopingcart WHERE idUser = ?";
        try {
            preparedStatement = JDBCUtils.getConnection().prepareStatement(insert);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();


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

    }


}
