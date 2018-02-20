package io.pax.forum.webservice;

import io.pax.forum.dao.UserDao;
import io.pax.forum.domain.Topic;
import io.pax.forum.domain.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AELION on 15/02/2018.
 */


@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserWs {


    @GET
    public List<User> getUsers() throws SQLException{
        UserDao dao = new UserDao();
        return dao.listUsers();
    }

    @GET
    @Path("{id}")
    public User getUserById(@PathParam("id") int userId) throws SQLException{

        return new UserDao().findUserById(userId);
    }

    @POST

    public User createUser(User user) {

        List<Topic> topics = new ArrayList<>();
        //init list de topic vide du user

        try {
            int id = new UserDao().createUser(user.getName());

            return new User(id, user.getName());


        } catch (SQLException e) {
            throw new ServerErrorException("DTB error", 500);
        }
    }

}
