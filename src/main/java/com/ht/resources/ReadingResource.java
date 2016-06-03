/**
 * 
 */
package com.ht.resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.ht.beans.SRFRReadings;
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
			MeterReading currentMonthReading = meterReadingsDAO.getCurrentMonthMeterReadings(meterNo);
			if(currentMonthReading.getSrfrFlag()==1){
				System.out.println("inside if of SR FR" );
				String checkMeterNo = p.getCheckMeterNo();
				viewMeterReadings.setMeterNo(checkMeterNo);
				currentMonthReading = meterReadingsDAO.getCurrentMonthMeterReadings(checkMeterNo);
				viewMeterReadings.setPreviousMeterReading(meterReadingsDAO.getPreviousMonthMeterReadings(checkMeterNo));
				viewMeterReadings.setCurrentMeterReading(currentMonthReading);
			}else{
				viewMeterReadings.setPreviousMeterReading(meterReadingsDAO.getPreviousMonthMeterReadings(meterNo));
				viewMeterReadings.setCurrentMeterReading(currentMonthReading);
			}
			if(currentMonthReading != null && currentMonthReading.getDeveloperValidation() == 1){
				viewMeterReadings.setConsumption(consumptionDAO.getByMeterReadingId(currentMonthReading.getId()));
			}
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
				MeterReading currentMonthReading = meterReadingsDAO.getCurrentMonthMeterReadings(meterNo);
				if(currentMonthReading.getSrfrFlag()==1){
					System.out.println("inside if of SR FR" );
					String checkMeterNo = p.getCheckMeterNo();
					viewMeterReadings.setMeterNo(checkMeterNo);
					currentMonthReading = meterReadingsDAO.getCurrentMonthMeterReadings(checkMeterNo);
					viewMeterReadings.setPreviousMeterReading(meterReadingsDAO.getPreviousMonthMeterReadings(checkMeterNo));
					viewMeterReadings.setCurrentMeterReading(currentMonthReading);
				}else{
					viewMeterReadings.setPreviousMeterReading(meterReadingsDAO.getPreviousMonthMeterReadings(meterNo));
					viewMeterReadings.setCurrentMeterReading(currentMonthReading);
				}
				if(currentMonthReading != null && currentMonthReading.getDeveloperValidation() == 1){
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
				MeterReading currentMonthReading = meterReadingsDAO.getCurrentMonthMeterReadings(meterNo);
				if(currentMonthReading.getSrfrFlag()==1){
					System.out.println("inside if of SR FR" );
					String checkMeterNo = p.getCheckMeterNo();
					viewMeterReadings.setMeterNo(checkMeterNo);
					currentMonthReading = meterReadingsDAO.getCurrentMonthMeterReadings(checkMeterNo);
					viewMeterReadings.setPreviousMeterReading(meterReadingsDAO.getPreviousMonthMeterReadings(checkMeterNo));
					viewMeterReadings.setCurrentMeterReading(currentMonthReading);
				}else{
					viewMeterReadings.setPreviousMeterReading(meterReadingsDAO.getPreviousMonthMeterReadings(meterNo));
					viewMeterReadings.setCurrentMeterReading(currentMonthReading);
				}
				if(currentMonthReading != null && currentMonthReading.getDeveloperValidation() == 1){
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
			MeterReading currentMonthReading = meterReadingsDAO.getCurrentMonthMeterReadings(meterNo);
			if(currentMonthReading.getSrfrFlag()==1){
				System.out.println("inside if of SR FR" );
				String checkMeterNo = plant.getCheckMeterNo();
				viewMeterReadings.setMeterNo(checkMeterNo);
				currentMonthReading = meterReadingsDAO.getCurrentMonthMeterReadings(checkMeterNo);
				viewMeterReadings.setPreviousMeterReading(meterReadingsDAO.getPreviousMonthMeterReadings(checkMeterNo));
				viewMeterReadings.setCurrentMeterReading(currentMonthReading);
			}else{
				viewMeterReadings.setPreviousMeterReading(meterReadingsDAO.getPreviousMonthMeterReadings(meterNo));
				viewMeterReadings.setCurrentMeterReading(currentMonthReading);
			}
			if(currentMonthReading != null && currentMonthReading.getDeveloperValidation() == 1){
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
	
	
	@POST
	@Path("/srfr")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveSRFRReading(SRFRReadings srfrReading){
		System.out.println("saveReading called ");
		MeterReading insertedReading = null;
		MeterReading meterReading = new MeterReading();
		MeterReading preCheckReading = new MeterReading();
		MeterReading currCheckReading = new MeterReading();
		meterReading.setMeterno(srfrReading.getMeterno());
		meterReading.setMf(srfrReading.getMf());
		meterReading.setReadingDate(srfrReading.getReadingDate());
		meterReading.setActiveEnergy(srfrReading.getActiveEnergy());
		meterReading.setActiveTodOne(srfrReading.getActiveTodOne());
		meterReading.setActiveTodTwo(srfrReading.getActiveTodTwo());
		meterReading.setActiveTodThree(srfrReading.getActiveTodThree());
		meterReading.setReactiveQuadrantOne(srfrReading.getReactiveQuadrantOne());
		meterReading.setReactiveQuadrantTwo(srfrReading.getReactiveQuadrantTwo());
		meterReading.setReactiveQuadrantThree(srfrReading.getReactiveQuadrantThree());
		meterReading.setReactiveQuadrantFour(srfrReading.getReactiveQuadrantFour());
		meterReading.setSrfrFlag(1);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			Calendar c = Calendar.getInstance();
			try {
				c.setTime(formatter.parse(srfrReading.getReadingDate()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			c.add(Calendar.MONTH,-1);
			int day = c.get(Calendar.DATE);
			String readingDay= null;
			if(day < 10){
				readingDay = "0"+day;
			}else{
				readingDay = String.valueOf(day);
			}
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH)+1;
			String mon = null;
			if (month < 10) {
				mon  = "0"+month;
			}
			String dateTrim = readingDay+"-"+mon+"-"+year;
		preCheckReading.setMeterno(srfrReading.getCheckMeterNo());
		preCheckReading.setMf(srfrReading.getMf());
		preCheckReading.setReadingDate(dateTrim);
		preCheckReading.setActiveEnergy(srfrReading.getPreCheckActiveEnergy());
		preCheckReading.setActiveTodOne(srfrReading.getPreCheckActiveTodOne());
		preCheckReading.setActiveTodTwo(srfrReading.getPreCheckActiveTodTwo());
		preCheckReading.setActiveTodThree(srfrReading.getPreCheckActiveTodThree());
		preCheckReading.setReactiveQuadrantOne(srfrReading.getPreCheckReactiveQuadrantOne());
		preCheckReading.setReactiveQuadrantTwo(srfrReading.getPreCheckReactiveQuadrantTwo());
		preCheckReading.setReactiveQuadrantThree(srfrReading.getPreCheckReactiveQuadrantThree());
		preCheckReading.setReactiveQuadrantFour(srfrReading.getPreCheckReactiveQuadrantFour());
		preCheckReading.setSrfrFlag(1);
		
		currCheckReading.setMeterno(srfrReading.getCheckMeterNo());
		currCheckReading.setMf(srfrReading.getMf());
		currCheckReading.setReadingDate(srfrReading.getReadingDate());
		currCheckReading.setActiveEnergy(srfrReading.getCurCheckActiveEnergy());
		currCheckReading.setActiveTodOne(srfrReading.getCurCheckActiveTodOne());
		currCheckReading.setActiveTodTwo(srfrReading.getCurCheckActiveTodTwo());
		currCheckReading.setActiveTodThree(srfrReading.getCurCheckActiveTodThree());
		currCheckReading.setReactiveQuadrantOne(srfrReading.getCurCheckReactiveQuadrantOne());
		currCheckReading.setReactiveQuadrantTwo(srfrReading.getCurCheckReactiveQuadrantTwo());
		currCheckReading.setReactiveQuadrantThree(srfrReading.getCurCheckReactiveQuadrantThree());
		currCheckReading.setReactiveQuadrantFour(srfrReading.getCurCheckReactiveQuadrantFour());
		currCheckReading.setSrfrFlag(1);
		boolean isReadingAlreadyAdded = false;		
		    isReadingAlreadyAdded = meterReadingsDAO.isReadingAlreadyAdded(meterReading);
			if(!isReadingAlreadyAdded){
				insertedReading = meterReadingsDAO.insert(meterReading);
				meterReadingsDAO.insert(preCheckReading);
				meterReadingsDAO.insert(currCheckReading);
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
}
