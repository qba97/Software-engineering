package service;

import dao.DaoException;
import dao.VehicleDao;
import model.Vehicle;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/vehicle")
public class VehicleService {
    // paths
    private static final String PATH_CREATE = "create";
    private static final String PATH_UPDATE = "update";

    // parameters
    private static final String ID = "id";

    @GET
    @Path("/{" + ID + "}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVehicleById(@PathParam(ID) int id) {
        VehicleDao dao = new VehicleDao();
        Vehicle vehicle = dao.read(id);
        if (vehicle == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(vehicle).build();
    }

    @POST
    @Path("/" + PATH_CREATE)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createVehicle(final Vehicle vehicle) {
        VehicleDao dao = new VehicleDao();
        try {
            Vehicle createdVehicle = dao.create(vehicle);
            return Response.ok(createdVehicle).build();
        } catch (DaoException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("/" + PATH_UPDATE)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateVehicle(final Vehicle vehicleToUpdate) {
        VehicleDao dao = new VehicleDao();
        Vehicle updatedVehicle = dao.update(vehicleToUpdate);
        if (updatedVehicle == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(updatedVehicle).build();
    }

    @DELETE
    @Path("/{" + ID + "}")
    public Response deleteVehicle(@PathParam(ID) int id) {
        VehicleDao dao = new VehicleDao();
        if (dao.delete(id)) {
            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}

