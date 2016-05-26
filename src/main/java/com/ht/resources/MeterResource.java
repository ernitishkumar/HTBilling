package com.ht.resources;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
		System.out.println("Add MeterDetails called : "+meterToInsert);
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
					.entity(new ErrorBean("Unable to add meter."))
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
}
