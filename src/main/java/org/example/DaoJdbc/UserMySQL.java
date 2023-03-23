package org.example.DaoJdbc;

import org.example.Config.JDBCUtils;
import org.example.Class.User;
import org.example.DAO.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserMySQL implements DAO<User> {
    public void create(User user) {
        PreparedStatement preparedStatement = null;
        String insert = "INSERT INTO user(login, password)VALUES(?,?)";
        try {
            preparedStatement = JDBCUtils.getConnection().prepareStatement(insert);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());

            preparedStatement.executeUpdate();
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
    }

    public List<User> get() {
        List<User> list = new ArrayList<>();
        Statement preparedStatement = null;
        String insert = "SELECT * FROM user";
        try {
            preparedStatement = JDBCUtils.getConnection().prepareStatement(insert);
            ResultSet resultSet = preparedStatement.executeQuery(insert);


            while (resultSet.next()){
                list.add(User.builder().id(resultSet.getInt("id")).login(resultSet.getString("login")).password(resultSet.getString("password")).build());
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

    public User getById(int id)  {
        User user = User.builder().build();
        PreparedStatement preparedStatement = null;
        String insert = "SELECT * FROM user WHERE id = ?";
        try {
            preparedStatement = JDBCUtils.getConnection().prepareStatement(insert);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
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
        return user;

    }

    public void delete(int id) {
        PreparedStatement preparedStatement = null;
        String insert = "DELETE FROM user WHERE id = ?";
        try {
            preparedStatement = JDBCUtils.getConnection().prepareStatement(insert);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();



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

    }

    public void update(User user) {
        PreparedStatement preparedStatement = null;
        String insert = "UPDATE user SET password =?, login = ? WHERE id = ?";
        try {
            preparedStatement = JDBCUtils.getConnection().prepareStatement(insert);
            preparedStatement.setInt(3, user.getId());
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.execute();



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
    }
}
