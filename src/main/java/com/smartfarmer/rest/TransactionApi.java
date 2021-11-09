package com.smartfarmer.rest;

import com.smartfarmer.dto.RestResponse;
import com.smartfarmer.ejb.interfaces.TransactionEjbI;
import com.smartfarmer.entities.Transaction;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/transactions")
public class TransactionApi {
    @EJB
    private TransactionEjbI transactionEjb;

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(){

        Transaction filter = new Transaction();
        return Response.ok().entity(transactionEjb.list(filter, 0, 0)).build();
    }

    @GET
    @Path("listExpenses")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listExpenses(){

        Transaction filter = new Transaction();
        return Response.ok().entity(transactionEjb.listExpenses(filter, 0, 0)).build();
    }

    @GET
    @Path("listIncomes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listIncomes(){
        Transaction filter = new Transaction();
        return Response.ok().entity(transactionEjb.listIncomes(filter, 0, 0)).build();
    }

    @GET
    @Path("totalIncome")
    @Produces(MediaType.APPLICATION_JSON)
    public double totalIncome(){
        Transaction transaction = new Transaction();
        return transactionEjb.totalIncomes(transaction);
    }

    @GET
    @Path("totalExpense")
    @Produces(MediaType.APPLICATION_JSON)
    public double totalExpense(){
        Transaction transaction = new Transaction();
        return transactionEjb.totalExpenses(transaction);
    }

    @GET
    @Path("find/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") long id){
        Optional<Transaction> transaction = transactionEjb.findById(id);

        if(transaction.isPresent()){
            return Response.ok().entity(
                    RestResponse.builder().success(true).data(transaction.get()).build()
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
    public Response save(Transaction transaction){
        try {
            Transaction trac = transactionEjb.add(transaction);
            return Response.ok().entity(
                    RestResponse.builder().message("Added Successfully").data(trac)
            ).build();

        }catch (Exception ex){
            return Response.status(400).entity(RestResponse.builder().success(false).message(ex.getMessage())).build();
        }
    }

    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") long id){

        if (!transactionEjb.existsById(id)) {
            return Response.status(Response.Status.NOT_FOUND).entity(
                    RestResponse.builder().success(false).message("Not found").build()
            ).build();
        }
        transactionEjb.delete(id);
        return Response.ok().entity(RestResponse.builder().success(true).data(id).message("Deleted Successfully")).build();

    }

}
