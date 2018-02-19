package io.pax.forum.dao;

import io.pax.forum.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AELION on 15/02/2018.
 */
public class UserDao {

    JdbcConnector connector = new JdbcConnector();

    public List<User> listUsers() throws SQLException {

        List<User> users = new ArrayList<>();
        Connection conn = this.connector.getConnection();
        String query = "SELECT * FROM user";
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while(rs.next()) {
            String name = rs.getString("name");
            int id = rs.getInt("id");
            users.add(new User(id, name) {
            });
        }

        rs.close();
        stmt.close();
        conn.close();
        return users;

    }

    public int createUser(String name)throws SQLException{
        String query = "INSERT INTO user (name) VALUES(?)";
        System.out.println(query);
        Connection conn = this.connector.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1,name);
        stmt.executeUpdate();


        ResultSet keys = stmt.getGeneratedKeys();
        keys.next();
        int id = keys.getInt(1);
        stmt.close();
        conn.close();
        return  id;
    }

    public User findUserById (int userId) throws SQLException{
        Connection connection = connector.getConnection();
        String query ="SELECT * FROM user u WHERE u.id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1,userId);
        ResultSet resultSet = statement.executeQuery();

        User user = null;
        List<User> users = new ArrayList<>();
        while (resultSet.next()){
            String userName = resultSet.getString("name");
            user = new User(userId,userName);
        }

        resultSet.close();
        statement.close();
        connection.close();

        return user;
    }

    public List<User> findUserByName (String userNameExtract) throws SQLException{
        Connection connection = connector.getConnection();
        String query ="SELECT * FROM user WHERE name LIKE ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1,userNameExtract+"%");
        ResultSet resultSet = statement.executeQuery();

        User user = null;
        List<User> users = new ArrayList<>();

        while (resultSet.next()){
            String name = resultSet.getString(1);
            int userId = resultSet.getInt("id");
            user = new User(userId,name);
            users.add(user);

        }

        resultSet.close();
        statement.close();
        connection.close();

        return users;
    }

    public int deleteUser(int userId) throws SQLException {

        String query = "DELETE FROM user WHERE id=?";

        Connection conn = this.connector.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, userId);
        stmt.executeUpdate();

        stmt.close();
        conn.close();


        return userId;
    }

    public static void main(String[] args) throws SQLException {

        UserDao dao= new UserDao();
        //System.out.println(dao.createUser("trop forts"));
       // System.out.println(dao.createUser("Cool"));
       // System.out.println(dao.createUser("Cool+++"));
        System.out.println(dao.listUsers().get(3).getName());
       System.out.println(dao.findUserById(6).getName());
        System.out.println(dao.findUserByName("Co"));
        System.out.println(dao.deleteUser(8));
        System.out.println(dao.deleteUser(9));
        System.out.println(dao.deleteUser(10));

    }
}
