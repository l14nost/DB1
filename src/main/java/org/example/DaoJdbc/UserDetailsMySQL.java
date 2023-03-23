package org.example.DaoJdbc;

import org.example.Config.JDBCUtils;
import org.example.Class.UserDetails;
import org.example.DAO.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDetailsMySQL implements DAO<UserDetails> {
    public void create(UserDetails userDetails) {
        PreparedStatement preparedStatement = null;
        String insert = "INSERT INTO userdetails(idUser,name,surname)VALUES(?,?,?)";
        try {
            preparedStatement = JDBCUtils.getConnection().prepareStatement(insert);
            preparedStatement.setInt(1, userDetails.getIdUser());
            preparedStatement.setString(2, userDetails.getName());
            preparedStatement.setString(3, userDetails.getSurname());

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

    public List<UserDetails> get() {
        List<UserDetails> list = new ArrayList<>();
        Statement preparedStatement = null;
        String insert = "SELECT * FROM userdetails";
        try {
            preparedStatement = JDBCUtils.getConnection().prepareStatement(insert);
            ResultSet resultSet = preparedStatement.executeQuery(insert);


            while (resultSet.next()){
                list.add(UserDetails.builder().idUser(resultSet.getInt("idUser")).name(resultSet.getString("name")).surname(resultSet.getString("surname")).build());
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

    public UserDetails getById(int id)  {
        UserDetails userDetails = UserDetails.builder().build();
        PreparedStatement preparedStatement = null;
        String insert = "SELECT * FROM userdetails WHERE idUser = ?";
        try {
            preparedStatement = JDBCUtils.getConnection().prepareStatement(insert);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                userDetails.setIdUser(resultSet.getInt("idUser"));
                userDetails.setName(resultSet.getString("name"));
                userDetails.setSurname(resultSet.getString("surname"));
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
        return userDetails;

    }

    public void delete(int id) {
        PreparedStatement preparedStatement = null;
        String insert = "DELETE FROM userdetails WHERE idUser = ?";
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



    public void update(UserDetails userDetails) {
        PreparedStatement preparedStatement = null;
        String insert = "UPDATE userdetails SET name = ?,surname = ? WHERE idUser = ?";
        try {
            preparedStatement = JDBCUtils.getConnection().prepareStatement(insert);
            preparedStatement.setInt(3, userDetails.getIdUser());
            preparedStatement.setString(1, userDetails.getName());
            preparedStatement.setString(2, userDetails.getSurname());
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
