package org.example.DaoJdbc;

import org.example.Class.Product;

import org.example.Config.JDBCUtils;
import org.example.DAO.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductMySQL implements DAO<Product> {
    public void create(Product product) {
        PreparedStatement preparedStatement = null;
        String insert = "INSERT INTO products(name,price)VALUES(?,?)";
        try {
            preparedStatement = JDBCUtils.getConnection().prepareStatement(insert);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());

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

    public List<Product> get() {
        List<Product> list = new ArrayList<>();
        Statement preparedStatement = null;
        String insert = "SELECT * FROM products";
        try {
            preparedStatement = JDBCUtils.getConnection().prepareStatement(insert);
            ResultSet resultSet = preparedStatement.executeQuery(insert);


            while (resultSet.next()) {
                list.add(Product.builder().idProducts(resultSet.getInt("idproducts")).name(resultSet.getString("name")).price(resultSet.getInt("price")).build());
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

    public Product getById(int id) {
        Product product = Product.builder().build();
        PreparedStatement preparedStatement = null;
        String insert = "SELECT * FROM products WHERE idproducts = ?";
        try {
            preparedStatement = JDBCUtils.getConnection().prepareStatement(insert);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                product.setIdProducts(resultSet.getInt("idproducts"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getInt("price"));
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
        return product;

    }

    public void delete(int id) {
        PreparedStatement preparedStatement = null;
        String insert = "DELETE FROM products WHERE idproducts = ?";
        try {
            preparedStatement = JDBCUtils.getConnection().prepareStatement(insert);
            preparedStatement.setInt(1, id);
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


    public void update(Product product) {
        PreparedStatement preparedStatement = null;
        String insert = "UPDATE products SET name = ?,price = ? WHERE idproducts = ?";
        try {
            preparedStatement = JDBCUtils.getConnection().prepareStatement(insert);
            preparedStatement.setInt(3, product.getIdProducts());
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.execute();


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
