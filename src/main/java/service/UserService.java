package service;

import dao.NickTakenException;
import dao.UserDao;
import model.User;
import model.patch.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/users")
public class UserService {
    // paths
    private static final String PATH_ACTIVE = "active";
    private static final String PATH_PASSWORD = "password";
    private static final String PATH_LOGIN = "login";
    private static final String PATH_DETAILS = "details";
    private static final String PATH_LOGOUT = "logout";
    private static final String PATH_ANSWER = "answer";

    // parameters
    private static final String ID = "id";

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(final User user, @Context UriInfo uriInfo) {
        UserDao userDao = new UserDao();

        try {
            User createdUser = userDao.create(user);

            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(Integer.toString(createdUser.getId()));

            return Response.created(uriBuilder.build()).build();
        } catch (NickTakenException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/{" + ID + "}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam(ID) int id) {
        UserDao dao = new UserDao();
        User user = dao.read(id);

        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(user).build();
    }

    @PATCH
    @Path("/{" + ID + "}/" + PATH_ACTIVE)
    public Response updateUserActive(final UserActiveOnly userActiveOnly, @PathParam(ID) int id) {
        UserDao dao = new UserDao();

        if (dao.updateActive(userActiveOnly, id)) {
            return Response.ok().build();
        }

        return Response.noContent().build();
    }

    @PATCH
    @Path("/{" + ID + "}/" + PATH_PASSWORD)
    public Response updateUserPassword(final UserPasswordOnly userPasswordOnly, @PathParam(ID) int id) {
        UserDao dao = new UserDao();

        if (dao.updatePassword(userPasswordOnly, id)) {
            return Response.ok().build();
        }

        return Response.noContent().build();
    }

    @POST
    @Path("/" + PATH_LOGIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(final UserNickAndPassword userNickAndPassword) {
        UserDao dao = new UserDao();

        User user = dao.login(userNickAndPassword);

        if (user != null) {
            return Response.ok(user).build();
        }

        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @POST
    @Path("/{" + ID + "}/" + PATH_LOGOUT)
    public Response logout(@PathParam(ID) int id) {
        UserDao dao = new UserDao();

        if (dao.logout(id)) {
            return Response.ok().build();
        }

        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @PATCH
    @Path("/{" + ID + "}/" + PATH_DETAILS)
    public Response updateUserDetails(final UserDetails userDetails, @PathParam(ID) int id) {
        UserDao dao = new UserDao();

        if (dao.updateDetails(userDetails, id)) {
            return Response.ok().build();
        }

        return Response.noContent().build();
    }

    @POST
    @Path("/" + PATH_ANSWER)
    public Response checkQuestionAnswer(final UserNickAndAnswer userNickAndAnswer) {
        UserDao dao = new UserDao();

        if (dao.checkQuestionAnswer(userNickAndAnswer)) {
            return Response.ok().build();
        }

        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
