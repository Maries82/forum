package io.pax.forum.dao;

import io.pax.forum.domain.Comment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AELION on 15/02/2018.
 */

    

    public class CommentDao {

        JdbcConnector connector = new JdbcConnector();

        public List<Comment> listComments() throws SQLException {

            List<Comment> comments = new ArrayList<>();
            Connection conn = this.connector.getConnection();
            String query = "SELECT * FROM comment";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                String content = rs.getString("content");
                int id = rs.getInt("id");
                comments.add(new Comment(id, content) {
                });
            }

            rs.close();
            stmt.close();
            conn.close();
            return comments;

        }

        public int createComment(int userId,int topicId,String content)throws SQLException{
            String query = "INSERT INTO comment (content, user_id, topic_id) VALUES(?,?,?)";
            System.out.println(query);
            Connection conn = this.connector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,content);
            stmt.setInt(2,userId);
            stmt.setInt(3,topicId);
            stmt.executeUpdate();


            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();
            int id = keys.getInt(1);
            stmt.close();
            conn.close();
            return  id;
        }

        public Comment findCommentById (int commentId) throws SQLException{
            Connection connection = connector.getConnection();
            String query ="SELECT * FROM comment u WHERE u.id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,commentId);
            ResultSet resultSet = statement.executeQuery();

            Comment comment = null;
            List<Comment> comments = new ArrayList<>();
            while (resultSet.next()){
                String commentContent = resultSet.getString("content");
                comment = new Comment(commentId,commentContent);
            }

            resultSet.close();
            statement.close();
            connection.close();

            return comment;
        }

    public int deleteComment(int commentId) throws SQLException {

        String query = "DELETE FROM comment WHERE id=?";

        Connection conn = this.connector.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, commentId);
        stmt.executeUpdate();

        stmt.close();
        conn.close();


        return commentId;
    }


        public static void main(String[] args) throws SQLException {

            CommentDao dao= new CommentDao();
            //System.out.println(dao.createComment(3,1,"J'aime le foot"));
            //System.out.println(dao.createComment(4,3,"j'aime les animaux"));
           //System.out.println(dao.createComment(5,1," :( le TFC est releguable sniff"));
            System.out.println(dao.listComments().get(3).getContent());
            System.out.println(dao.findCommentById(6).getContent());
            System.out.println(dao.deleteComment(6));


        }
    }

