package com.smartfarmer.rest;

import com.smartfarmer.dto.RestResponse;
import com.smartfarmer.ejb.interfaces.FieldDetailEjbI;
import com.smartfarmer.entities.Field;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

public class FieldApi {
    @EJB
    private FieldDetailEjbI fieldDetailEjb;

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(){

        Field filter = new Field();
        return Response.ok().entity(fieldDetailEjb.list(filter, 0, 0)).build();

    }

    @Path("find/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") long id){
        Optional<Field> field = fieldDetailEjb.findById(id);

        if(field.isPresent()){
            return Response.ok().entity(
                    RestResponse.builder().success(true).data(field.get()).build()
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
    public Response save(Field field){
        try {
            Field fi = fieldDetailEjb.add(field);
            return Response.ok().entity(
                    RestResponse.builder().message("Added Successfully").data(fi)
            ).build();

        }catch (Exception ex){
            return Response.status(400).entity(RestResponse.builder().success(false).message(ex.getMessage())).build();
        }
    }

    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") long id){

        if (!fieldDetailEjb.existsById(id)) {
            return Response.status(Response.Status.NOT_FOUND).entity(
                    RestResponse.builder().success(false).message("Not found").build()
            ).build();
        }
        fieldDetailEjb.delete(id);
        return Response.ok().entity(RestResponse.builder().success(true).data(id).message("Deleted Successfully")).build();

    }

}
