package io.pax.forum.dao;

import io.pax.forum.domain.Topic;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AELION on 15/02/2018.
 */
public class TopicDao {

    JdbcConnector connector = new JdbcConnector();

    public List<Topic> listTopics() throws SQLException {

        List<Topic> topics = new ArrayList<>();
        Connection conn = this.connector.getConnection();
        String query = "SELECT * FROM topic";
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while(rs.next()) {
            String name = rs.getString("name");
            int id = rs.getInt("id");
            topics.add(new Topic(id, name) {
            });
        }

        rs.close();
        stmt.close();
        conn.close();
        return topics;

    }

    public int createTopic(int userId, String name)throws SQLException{
        String query = "INSERT INTO topic (name, user_id) VALUES(?,?)";
        System.out.println(query);
        Connection conn = this.connector.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        stmt.setString(1,name);
        stmt.setInt(2,userId);
        stmt.executeUpdate();


        ResultSet keys = stmt.getGeneratedKeys();
        keys.next();
        int id = keys.getInt(1);
        stmt.close();
        conn.close();
        return  id;
    }

    public Topic findTopicById (int topicId) throws SQLException{
        Connection connection = connector.getConnection();
        String query ="SELECT * FROM topic t WHERE t.id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1,topicId);
        ResultSet resultSet = statement.executeQuery();

        Topic topic = null;
        List<Topic> topics = new ArrayList<>();
        while (resultSet.next()){
            String userName = resultSet.getString("name");

           topic = new Topic(topicId,userName);
        }

        resultSet.close();
        statement.close();
        connection.close();

        return topic;
    }

    public List<Topic> findTopicByName (String userNameExtract) throws SQLException{
        Connection connection = connector.getConnection();
        String query ="SELECT * FROM topic WHERE name LIKE ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1,userNameExtract+"%");
        ResultSet resultSet = statement.executeQuery();

       Topic topic = null;
        List<Topic> topics = new ArrayList<>();

        while (resultSet.next()){
            String name = resultSet.getString(1);
            int topicId = resultSet.getInt("id");
            topic = new Topic(topicId,name);
          topics.add(topic);

        }

        resultSet.close();
        statement.close();
        connection.close();

        return topics;
    }

    public int deleteTopic(int topicId) throws SQLException {

        String query = "DELETE FROM topic WHERE id=?";

        Connection conn = this.connector.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, topicId);
        stmt.executeUpdate();

        stmt.close();
        conn.close();


        return topicId;
    }

    public static void main(String[] args) throws SQLException {

        TopicDao dao= new TopicDao();
       // System.out.println(dao.createTopic(1,"JavaEE"));
       // System.out.println(dao.deleteTopic(9));

        System.out.println(dao.listTopics().get(3).getName());
        System.out.println(dao.findTopicById(6).getName());
        System.out.println(dao.findTopicByName("a"));

        //tests ok
    }
}
