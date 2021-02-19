package controllers;

import entities.User;
import repositories.interfaces.IUserRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("users")
public class UserController {
    @Inject
    private IUserRepository repo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        List<User> users;
        try {
            users = repo.getAllUsers();
        } catch (ServerErrorException ex) {
            return Response
                    .status(500).entity(ex.getMessage()).build();
        }

        return Response
                .ok(users)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(User user) {
        boolean created;
        try {
            created = repo.createUser(user);
        } catch (ServerErrorException ex) {
            return Response.serverError().entity(ex.getMessage()).build();
        }

        if (!created) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("User cannot be created!")
                    .build();
        }

        return Response
                .status(Response.Status.CREATED)
                .entity("User created successfully!")
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") int id) {
        User user;
        try {
            user = repo.getUser(id);
        } catch (ServerErrorException ex) {
            return Response
                    .status(500).entity(ex.getMessage()).build();
        }

        if (user == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("User does not exist!")
                    .build();
        }

        return Response
                .status(Response.Status.OK)
                .entity(user)
                .build();
    }

    @GET
    @Path("/word/{word}")
    public Response something(@PathParam("word") String word) {
        return Response.ok("You entered : " + word).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") int id) {
        boolean removed;

        try {
            removed = repo.deleteUser(id);
        } catch (ServerErrorException ex) {
            return Response
                    .status(500).entity(ex.getMessage()).build();
        }

        if (removed) {
            return Response.ok("A user was removed successfully!").build();
        } else {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("A user with such id was not found!")
                    .build();
        }
    }
}
