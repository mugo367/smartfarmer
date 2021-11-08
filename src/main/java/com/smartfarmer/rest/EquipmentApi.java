package com.smartfarmer.rest;

import com.smartfarmer.dto.RestResponse;
import com.smartfarmer.ejb.interfaces.EquipmentEjbI;
import com.smartfarmer.entities.Equipment;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/equipments")
public class EquipmentApi {

    @EJB
    private EquipmentEjbI equipmentEjb;

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(){

        Equipment filter = new Equipment();
        return Response.ok().entity(equipmentEjb.list(filter, 0, 0)).build();

    }

    @Path("find/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") long id){
        Optional<Equipment> equipment = equipmentEjb.findById(id);

        if(equipment.isPresent()){
            return Response.ok().entity(
                    RestResponse.builder().success(true).data(equipment.get()).build()
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
    public Response save(Equipment equipment){
        try {
            Equipment ac = equipmentEjb.add(equipment);
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

        if (!equipmentEjb.existsById(id)) {
            return Response.status(Response.Status.NOT_FOUND).entity(
                    RestResponse.builder().success(false).message("Not found").build()
            ).build();
        }
        equipmentEjb.delete(id);
        return Response.ok().entity(RestResponse.builder().success(true).data(id).message("Deleted Successfully")).build();

    }

}
