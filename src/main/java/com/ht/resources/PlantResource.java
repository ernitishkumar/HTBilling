/**
 * 
 */
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
import com.ht.beans.MeterData;
import com.ht.beans.MeterDetails;
import com.ht.beans.MeterReading;
import com.ht.beans.Plant;
import com.ht.dao.MeterDetailsDAO;
import com.ht.dao.MeterReadingsDAO;
import com.ht.dao.PlantsDAO;

/**
 * @author NITISH
 *
 */
@Path("/plants")
public class PlantResource {
	
	/*
	 * Object variable plantsDAO
	 */
	private PlantsDAO plantsDAO = new PlantsDAO();
	
	@GET
	@Path("/meterno/{meterno}")
	@Produces(MediaType.APPLICATION_JSON)
	public MeterData getByMeterNo(@PathParam("meterno")String meterno){
		System.out.println("GET GetByMeterNo request for "+meterno);
		MeterData meterData = null;
		if(meterno != null){
			MeterDetailsDAO meterDetailsDAO = new MeterDetailsDAO();
			MeterDetails meterDetails = meterDetailsDAO.getByMeterNo(meterno);
			Plant plant = plantsDAO.getByMainMeterNo(meterno);
			MeterReadingsDAO meterReadingsDAO = new MeterReadingsDAO();
			MeterReading currentReadings = meterReadingsDAO.getLatestInsertedByMeterNo(meterno);
			if(meterDetails != null && plant != null){
				meterData = new MeterData();
				meterData.setMeter(meterDetails);
				meterData.setPlant(plant);
				meterData.setCurrentReading(currentReadings);
			}
		}
		return meterData;
	}
	
	@GET
	@Path("/developer/{developerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Plant> getByDeveloperId(@PathParam("developerId")int developerId){
		System.out.println("GET getByDeveloperId request for "+developerId);
		return plantsDAO.getByDeveloperId(developerId);
	}
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Plant plant){
		System.out.println("Adding Plant : "+plant);
		Plant updatededPlant = null;
		if(plant != null){
			updatededPlant = plantsDAO.update(plant);
		}
		if(updatededPlant != null){
			return Response.status(Status.OK)
					.entity(updatededPlant)
					.build();
		}else{
			return Response.status(Status.EXPECTATION_FAILED)
					.entity(new ErrorBean("Unable to update plant. Try Again !"))
					.build();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insert(Plant plant){
		System.out.println("Adding Plant : "+plant);
		Plant insertedPlant = null;
		if(plant != null){
			insertedPlant = plantsDAO.insert(plant);
		}
		if(insertedPlant != null){
			return Response.status(Status.CREATED)
					.entity(insertedPlant)
					.build();
		}else{
			return Response.status(Status.EXPECTATION_FAILED)
					.entity(new ErrorBean("Unable to add plant. Try Again !"))
					.build();
		}
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Plant> getAllPlants(){
		System.out.println("Getting all plants");
		return plantsDAO.getAll();
	}
	
	@GET()
	@Path("/plantId/{plantId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Plant getPlantById(@PathParam("plantId") int plantId){
		System.out.println("Getting all plants");
		return plantsDAO.getById(plantId);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePlant(@PathParam("id")int id){
		Plant deletedPlant = null;
		deletedPlant = plantsDAO.delete(id);
		if(deletedPlant != null){
			return Response.status(Status.OK)
					.entity(deletedPlant)
					.build();
		}else{
			return Response.status(Status.EXPECTATION_FAILED)
					.entity(new ErrorBean("Unable to delete plant."))
					.build();
		}
	}
}
