package com.smartfarmer.rest;

import com.smartfarmer.dto.RestResponse;
import com.smartfarmer.ejb.interfaces.FieldTaskEjbI;
import com.smartfarmer.entities.FieldTask;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

public class FieldTaskApi {
    @EJB
    private FieldTaskEjbI fieldTaskEjb;

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll(){

        FieldTask filter = new FieldTask();
        return Response.ok().entity(fieldTaskEjb.list(filter, 0, 0)).build();

    }

    @GET
    @Path("list/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(@PathParam("id") long id){

        FieldTask filter = new FieldTask();
        filter.setFieldId(id);
        return Response.ok().entity(fieldTaskEjb.list(filter, 0, 0)).build();

    }

    @GET
    @Path("find/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") long id){
        Optional<FieldTask> FieldTask = fieldTaskEjb.findById(id);

        if(FieldTask.isPresent()){
            return Response.ok().entity(
                    RestResponse.builder().success(true).data(FieldTask.get()).build()
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
    public Response save(FieldTask fieldTask){
        try {
            FieldTask task = fieldTaskEjb.add(fieldTask);
            return Response.ok().entity(
                    RestResponse.builder().success(true).message("Added Successfully").data(task).build()
            ).build();

        }catch (Exception ex){
            return Response.status(400).entity(RestResponse.builder().success(false).message(ex.getMessage())).build();
        }
    }

    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") long id){

        if (!fieldTaskEjb.existsById(id)) {
            return Response.status(Response.Status.NOT_FOUND).entity(
                    new RestResponse( false, "Not found")).build() ;
        }
        fieldTaskEjb.delete(id);
        return Response.ok().entity(new RestResponse( true, "Deleted Successfully")).build();

    }
}
