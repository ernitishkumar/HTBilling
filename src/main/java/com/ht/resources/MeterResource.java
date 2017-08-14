package com.ht.resources;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.ht.beans.ErrorBean;
import com.ht.beans.MeterDetails;
import com.ht.dao.MeterDetailsDAO;

/**
 * @author Hp
 *
 */
@Path("/meter")
public class MeterResource {

	private MeterDetailsDAO meterDetailsDAO = new MeterDetailsDAO();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMeterDetails(MeterDetails meterToInsert){
		System.out.println("Add MeterDetails called");
		MeterDetails insertedMeter = null;
		if(meterToInsert != null){
			insertedMeter = meterDetailsDAO.insert(meterToInsert);
		}
		if(insertedMeter != null){
			return Response.status(Status.CREATED)
					.entity(insertedMeter)
					.build();
		}else{
			return Response.status(Status.EXPECTATION_FAILED)
					.entity(new ErrorBean("Unable to add meter.Try Again !"))
					.build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<MeterDetails> getAllMeters(){
		System.out.println("Getting all meters");
		return meterDetailsDAO.getAll();
	}
	
	@GET
	@Path("/unused")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<MeterDetails> getAllUnusedMeters(){
		System.out.println("Getting all unused meters");
		return meterDetailsDAO.getMetersNotInUse();
	}
	
	@GET
	@Path("/unmapped")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<MeterDetails> getAllUnmappedMeters(){
		System.out.println("Getting all unused meters");
		return meterDetailsDAO.getMetersNotMappedToPlant();
	}
	
	@DELETE
	@Path("/{meterno}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeMeterDetails(@PathParam("meterno")String meterno){
		MeterDetails removedMeter = null;
		removedMeter = meterDetailsDAO.delete(meterno);
		if(removedMeter != null){
			return Response.status(Status.OK)
					.entity(removedMeter)
					.build();
		}else{
			return Response.status(Status.EXPECTATION_FAILED)
					.entity(new ErrorBean("Unable to delete meter."))
					.build();
		}
	}
	
	@GET
	@Path("/{meterno}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMeterDetails(@PathParam("meterno")String meterno){
		MeterDetails meterDetails = null;
		meterDetails = meterDetailsDAO.getByMeterNo(meterno);
		if(meterDetails != null){
			return Response.status(Status.OK)
					.entity(meterDetails)
					.build();
		}else{
			return Response.status(Status.EXPECTATION_FAILED)
					.entity(new ErrorBean("Unable to fetch meter."))
					.build();
		}
	}
	
	@PUT
	@Path("/{meterno}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateMeterDetails(@PathParam("meterno")String meterno, MeterDetails meterToUpdate){
		MeterDetails updatedMeterDetails = null;
		updatedMeterDetails = meterDetailsDAO.update(meterToUpdate);
		if(updatedMeterDetails != null){
			return Response.status(Status.OK)
					.entity(updatedMeterDetails)
					.build();
		}else{
			return Response.status(Status.EXPECTATION_FAILED)
					.entity(new ErrorBean("Unable to update meter."))
					.build();
		}
	}
}
