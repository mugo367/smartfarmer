package com.smartfarmer.rest;

import com.smartfarmer.dto.RestResponse;
import com.smartfarmer.ejb.interfaces.ActivityEjbI;
import com.smartfarmer.entities.Activity;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/activities")
public class ActivityApi {
    @EJB
    private ActivityEjbI activityEjb;

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(){

        Activity filter = new Activity();
        return Response.ok().entity(activityEjb.list(filter, 0, 0)).build();

    }

    @Path("find/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") long id){
        Optional<Activity> activity = activityEjb.findById(id);

        if(activity.isPresent()){
            return Response.ok().entity(
                    RestResponse.builder().success(true).data(activity.get()).build()
            ).build();
        }else{
           return Response.status(Response.Status.NOT_FOUND).entity(
                    RestResponse.builder().success(false).message("Not found").build()
            ).build();
        }
    }

    @POST
    @Path("save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(Activity activity){
        try {
            Activity ac = activityEjb.add(activity);
            return Response.ok().entity(
                    RestResponse.builder().message("Added Successfully").data(ac)
            ).build();

        }catch (Exception ex){
            return Response.status(400).entity(RestResponse.builder().success(false).message(ex.getMessage())).build();
        }
    }

    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") long id){

        if (!activityEjb.existsById(id)) {
            return Response.status(Response.Status.NOT_FOUND).entity(
                    RestResponse.builder().success(false).message("Not found").build()
            ).build();
        }
        activityEjb.delete(id);
        return Response.ok().entity(RestResponse.builder().success(true).data(id).message("Deleted Successfully")).build();

    }

}
