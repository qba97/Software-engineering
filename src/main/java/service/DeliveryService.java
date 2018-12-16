package service;

import dao.DaoException;
import dao.DeliveryDao;
import model.Delivery;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/delivery")
public class DeliveryService {
    // paths
    private static final String PATH_CREATE = "create";
    private static final String PATH_UPDATE = "update";

    // parameters
    private static final String ID = "id";

    @GET
    @Path("/{" + ID + "}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDeliveryById(@PathParam(ID) int id) {
        DeliveryDao dao = new DeliveryDao();
        Delivery delivery = dao.read(id);
        if (delivery == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(delivery).build();
    }

    @POST
    @Path("/" + PATH_CREATE)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDelivery(final Delivery delivery) {
        DeliveryDao dao = new DeliveryDao();
        try {
            Delivery createdDelivery = dao.create(delivery);
            return Response.ok(createdDelivery).build();
        } catch (DaoException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("/" + PATH_UPDATE)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDelivery(final Delivery deliveryToUpdate) {
        DeliveryDao dao = new DeliveryDao();
        Delivery updatedDelivery = dao.update(deliveryToUpdate);
        if (updatedDelivery == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(updatedDelivery).build();
    }

    @DELETE
    @Path("/{" + ID + "}")
    public Response deleteDelivery(@PathParam(ID) int id) {
        DeliveryDao dao = new DeliveryDao();
        if (dao.delete(id)) {
            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
