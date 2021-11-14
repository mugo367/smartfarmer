package com.smartfarmer.rest;


import com.smartfarmer.dto.RestResponse;
import com.smartfarmer.ejb.interfaces.ProductionEjbI;
import com.smartfarmer.entities.Production;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/productions")
public class ProductionApi {
    @EJB
    private ProductionEjbI productionEjb;

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(){

        Production filter = new Production();
        return Response.ok().entity(productionEjb.list(filter, 0, 0)).build();

    }

    @Path("find/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") long id){
        Optional<Production> production = productionEjb.findById(id);

        if(production.isPresent()){
            return Response.ok().entity(
                    RestResponse.builder().success(true).data(production.get()).build()
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
    public Response save(Production production){
        try {
            productionEjb.add(production);
            return Response.ok().entity(
                    RestResponse.builder().message("Added Successfully").data(production)
            ).build();

        }catch (Exception ex){
            return Response.status(400).entity(RestResponse.builder().success(false).message(ex.getMessage())).build();
        }
    }

    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") long id){

        if (!productionEjb.existsById(id)) {
            return Response.status(Response.Status.NOT_FOUND).entity(
                    RestResponse.builder().success(false).message("Not found").build()
            ).build();
        }
        productionEjb.delete(id);
        return Response.ok().entity(RestResponse.builder().success(true).data(id).message("Deleted Successfully")).build();

    }

}
