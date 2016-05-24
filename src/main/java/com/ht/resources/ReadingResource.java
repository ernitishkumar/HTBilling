/**
 * 
 */
package com.ht.resources;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.ht.beans.Developer;
import com.ht.beans.ErrorBean;
import com.ht.beans.MessageBean;
import com.ht.beans.MeterReading;
import com.ht.beans.Plant;
import com.ht.beans.ViewMeterReadings;
import com.ht.dao.ConsumptionsDAO;
import com.ht.dao.DevelopersDAO;
import com.ht.dao.MeterReadingsDAO;
import com.ht.dao.PlantsDAO;

/**
 * @author NITISH
 *
 */
@Path("/readings")
public class ReadingResource {

	private MeterReadingsDAO meterReadingsDAO = new MeterReadingsDAO();
	private PlantsDAO plantsDAO = new PlantsDAO();
	private DevelopersDAO developersDAO = new DevelopersDAO();
	private ConsumptionsDAO consumptionDAO = new ConsumptionsDAO();

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveReading(MeterReading meterReading){
		System.out.println("saveReading called ");
		MeterReading insertedReading = null;
		boolean isReadingAlreadyAdded = false;
		if(meterReading !=null){
		    isReadingAlreadyAdded = meterReadingsDAO.isReadingAlreadyAdded(meterReading);
			if(!isReadingAlreadyAdded){
				insertedReading = meterReadingsDAO.insert(meterReading);
			}
		}
		if(insertedReading != null){
			return Response.status(Status.CREATED)
					.entity(insertedReading)
					.build();
		}else{
			ErrorBean error = new ErrorBean();
			if(isReadingAlreadyAdded){
				error.setErrorMessage("Readings for meter already exists for this month.");
			}
			return Response.status(Status.EXPECTATION_FAILED)
					.entity(error)
					.build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<ViewMeterReadings> getAll(){
		System.out.println("GetAllReadings started ");
		ArrayList<ViewMeterReadings> viewReadings = new ArrayList<ViewMeterReadings>();
		ArrayList<Plant> plants = plantsDAO.getAll();
		SimpleDateFormat formater = new SimpleDateFormat("dd-MM-YYYY");
		Date date = new Date();
		String currentDate = formater.format(date);
		for(Plant p:plants){
			ViewMeterReadings viewMeterReadings = new ViewMeterReadings();
			String meterNo=p.getMainMeterNo();
			viewMeterReadings.setMeterNo(meterNo);
			viewMeterReadings.setPlant(p);
			viewMeterReadings.setDeveloper(developersDAO.getById(p.getDeveloperId()));
			//viewMeterReadings.setCurrentMeterReading(meterReadingsDAO.getCurrentMonthMeterReadings(meterNo, currentDate));
			//viewMeterReadings.setPreviousMeterReading(meterReadingsDAO.getPreviousMonthMeterReadings(meterNo, currentDate));
			viewMeterReadings.setPreviousMeterReading(meterReadingsDAO.getPreviousInsertedByMeterNo(meterNo));
			viewMeterReadings.setCurrentMeterReading(meterReadingsDAO.getLatestInsertedByMeterNo(meterNo));
			viewReadings.add(viewMeterReadings);
		}
		return viewReadings;
	}

	@GET
	@Path("/circle/{circleName}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<ViewMeterReadings> getByCircle(@PathParam("circleName")String circle){
		System.out.println("GetReadings by circle started for circle : "+circle);
		ArrayList<ViewMeterReadings> viewReadings = new ArrayList<ViewMeterReadings>();
		if(circle!=null){
			ArrayList<Plant> plants = plantsDAO.getByCircle(circle);
			SimpleDateFormat formater = new SimpleDateFormat("dd-MM-YYYY");
			Date date = new Date();
			String currentDate = formater.format(date);
			for(Plant p:plants){
				ViewMeterReadings viewMeterReadings = new ViewMeterReadings();
				String meterNo=p.getMainMeterNo();
				viewMeterReadings.setMeterNo(meterNo);
				viewMeterReadings.setPlant(p);
				viewMeterReadings.setDeveloper(developersDAO.getById(p.getDeveloperId()));
				MeterReading currentMonthReading = meterReadingsDAO.getCurrentMonthMeterReadings(meterNo, currentDate);
				viewMeterReadings.setCurrentMeterReading(currentMonthReading);
				viewMeterReadings.setPreviousMeterReading(meterReadingsDAO.getPreviousMonthMeterReadings(meterNo, currentDate));
				if(currentMonthReading.getDeveloperValidation() == 1){
					//viewMeterReadings.setConsumption(consumptionDAO.getBifercationFlagByPlantIdAndDate(p.getId(),currentDate));
					viewMeterReadings.setConsumption(consumptionDAO.getByMeterReadingId(currentMonthReading.getId()));
				}
				viewReadings.add(viewMeterReadings);
			}
		}
		return viewReadings;
	}

	@GET
	@Path("/developer/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<ViewMeterReadings> getByDeveloperUsername(@PathParam("username")String username){
		System.out.println("GetReadings by Developer username started for username : "+username);
		ArrayList<ViewMeterReadings> viewReadings = new ArrayList<ViewMeterReadings>();
		if(username!=null){
			Developer developer=developersDAO.getByUsername(username);
			ArrayList<Plant> plants = plantsDAO.getByDeveloperId(developer.getId());
			SimpleDateFormat formater = new SimpleDateFormat("dd-MM-YYYY");
			Date date = new Date();
			String currentDate = formater.format(date);
			for(Plant p:plants){
				ViewMeterReadings viewMeterReadings = new ViewMeterReadings();
				String meterNo=p.getMainMeterNo();
				viewMeterReadings.setMeterNo(meterNo);
				viewMeterReadings.setPlant(p);
				viewMeterReadings.setDeveloper(developersDAO.getById(p.getDeveloperId()));
				MeterReading currentMonthReading = meterReadingsDAO.getCurrentMonthMeterReadings(meterNo, currentDate);
				viewMeterReadings.setCurrentMeterReading(currentMonthReading);
				viewMeterReadings.setPreviousMeterReading(meterReadingsDAO.getPreviousMonthMeterReadings(meterNo, currentDate));
				if(currentMonthReading.getDeveloperValidation() == 1){
					//viewMeterReadings.setConsumption(consumptionDAO.getBifercationFlagByPlantIdAndDate(p.getId(),currentDate));
					viewMeterReadings.setConsumption(consumptionDAO.getByMeterReadingId(currentMonthReading.getId()));
				}
				viewReadings.add(viewMeterReadings);
			}
		}
		return viewReadings;
	}

	@GET
	@Path("/meterno/{meterno}")
	@Produces(MediaType.APPLICATION_JSON)
	public ViewMeterReadings getByMainMeterno(@PathParam("meterno")String meterno){
		System.out.println("GetReadings by meterno started for meterno : "+meterno);
		ViewMeterReadings viewReading = null;
		if(meterno!=null){
			Plant plant = plantsDAO.getByMainMeterNo(meterno);
			SimpleDateFormat formater = new SimpleDateFormat("dd-MM-YYYY");
			Date date = new Date();
			String currentDate = formater.format(date);
			ViewMeterReadings viewMeterReadings = new ViewMeterReadings();
			String meterNo=plant.getMainMeterNo();
			viewMeterReadings.setMeterNo(meterNo);
			viewMeterReadings.setPlant(plant);
			viewMeterReadings.setDeveloper(developersDAO.getById(plant.getDeveloperId()));
			MeterReading currentMonthReading = meterReadingsDAO.getCurrentMonthMeterReadings(meterNo, currentDate);
			viewMeterReadings.setCurrentMeterReading(currentMonthReading);
			viewMeterReadings.setPreviousMeterReading(meterReadingsDAO.getPreviousMonthMeterReadings(meterNo, currentDate));
			if(currentMonthReading.getDeveloperValidation() == 1){
				viewMeterReadings.setConsumption(consumptionDAO.getBifercationFlagByPlantIdAndDate(plant.getId(),currentDate));
			}		
		}
		return viewReading;
	}

	@PUT
	@Path("/validate")
	@Produces(MediaType.APPLICATION_JSON)
	public Response validateReadings(@QueryParam("role")String role,@QueryParam("readingId")int readingId){
		System.out.println("validateReadings called for role "+role);
		boolean validated = false;
		if(role!=null){
			if(role.equalsIgnoreCase("htcell")||role.equalsIgnoreCase("admin")){
				validated=meterReadingsDAO.updateHTCellValidation(readingId,1);
			}else if(role.equalsIgnoreCase("circle")){
				validated=meterReadingsDAO.updateCircleCellValidation(readingId,1);
			}
			else if(role.equalsIgnoreCase("developer")){
				validated=meterReadingsDAO.updateDeveloperValidation(readingId,1); 
			}
		}
		if(role!=null && validated){
			return Response.status(Status.OK)
					.entity(new MessageBean("validated"))
					.build();
		}else{
			return Response.status(Status.EXPECTATION_FAILED)
					.entity(new ErrorBean("not validated"))
					.build();
		}

	}
}
