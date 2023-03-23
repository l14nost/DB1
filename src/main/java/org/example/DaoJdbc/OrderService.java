package org.example.DaoJdbc;

import org.example.Class.User;
import org.example.Config.JDBCUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderService {

    public void create(User user){
        ShoppingCartMySQL shoppingCartMySQL = new ShoppingCartMySQL();
        ProductMySQL productMySQL = new ProductMySQL();
        PreparedStatement preparedStatement = null;
        StringBuilder sb = new StringBuilder();

        String insert = "INSERT INTO orders(idUser,sum,products)VALUES(?,?,?)";
        Integer sum = 0;
        String res;


        try {

            preparedStatement = JDBCUtils.getConnection().prepareStatement(insert);
            for( int i = 0; i<shoppingCartMySQL.getAllProduct(user).size();i++) {
                String val1 = shoppingCartMySQL.getAllProduct(user).get(i);
                for (int j = 0; j < productMySQL.get().size(); j++) {
                    String val2 = productMySQL.get().get(j).getName();
                    if (val1.equals(val2)) {
                        sb.append(val1).append(",");
                        sum += productMySQL.get().get(j).getPrice();
                    }
                }
            }
            sb.deleteCharAt(sb.length()-1);
            res = sb.toString();
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2,sum);
            preparedStatement.setString(3, res);


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
