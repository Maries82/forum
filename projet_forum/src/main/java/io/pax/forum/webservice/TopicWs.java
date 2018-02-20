package io.pax.forum.webservice;

import io.pax.forum.dao.TopicDao;
import io.pax.forum.domain.Topic;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by AELION on 20/02/2018.
 */

@Path("topics")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)


public class TopicWs {


    private int userId;

    @GET
    public List<Topic> getTopics() throws SQLException {
        TopicDao dao = new TopicDao();
        return dao.listTopics();
    }

    @GET
    @Path("{id}")
    public Topic getTopicById(@PathParam("id") int topicId) throws SQLException {

        return new TopicDao().findTopicById(topicId);

    }


    @POST


    public Topic createTopic(Topic topic) throws SQLException {

        //List<Comment> comments = new ArrayList<>();
            //UserDao dao = new UserDao();

            //userId = 3;

            try {
                topic.setId(new TopicDao().createTopic(topic.getUserId(), topic.getName()));
                return new Topic(topic.getId(), topic.getName(), topic.getUserId());

            } catch (SQLException e) {
                throw new ServerErrorException("DTB error", 500);
            }


    }
}


