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
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.ht.beans.Consumption;
import com.ht.beans.ErrorBean;
import com.ht.beans.Investor;
import com.ht.beans.InvestorConsumption;
import com.ht.beans.InvestorConsumptionView;
import com.ht.beans.InvestorPlantMapping;
import com.ht.beans.MeterReading;
import com.ht.dao.ConsumptionsDAO;
import com.ht.dao.InvestorConsumptionDAO;
import com.ht.dao.InvestorPlantMappingDAO;
import com.ht.dao.InvestorsDAO;
import com.ht.dao.MachinesDAO;
import com.ht.dao.MeterReadingsDAO;

/**
 * @author NITISH
 *
 */
@Path("/investors")
public class InvestorResource {

	private InvestorPlantMappingDAO investorPlantMappingDAO=new InvestorPlantMappingDAO();
	private InvestorsDAO investorsDAO = new InvestorsDAO();
	private MachinesDAO machinesDAO = new MachinesDAO();
	private ConsumptionsDAO consumptionsDAO = new ConsumptionsDAO();
	private InvestorConsumptionDAO investorConsumptionDAO = new InvestorConsumptionDAO();
	private MeterReadingsDAO meterReadingsDAO = new MeterReadingsDAO();

	@GET
	@Path("/bifurcation/{plantId}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<InvestorConsumptionView> getInvestorsForBifurcation(@PathParam("plantId")int plantId){
		System.out.println("GetInvestorsForBifurcation called for plantId : "+plantId);
		ArrayList<InvestorPlantMapping> investorPlantMappings = investorPlantMappingDAO.getByPlantId(plantId);
		ArrayList<InvestorConsumptionView> investors=new ArrayList<InvestorConsumptionView>();
		for(InvestorPlantMapping ipm:investorPlantMappings){
			Investor investor=investorsDAO.getById(ipm.getInvestorId());
			InvestorConsumptionView investorConsumptionView = new InvestorConsumptionView();
			investorConsumptionView.setInvestor(investor);
			investorConsumptionView.setMachines(machinesDAO.getByInvestorId(investor.getId()));
			investors.add(investorConsumptionView);
		}
		return investors;
	}

	@POST
	@Path("/consumption")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveBifurcation(ArrayList<InvestorConsumption> investorsConsumption){
		System.out.println("SaveBifurcation for Investors Consumption called");
		ArrayList<InvestorConsumption> insertedInvestorsConsumption = investorConsumptionDAO.insert(investorsConsumption);
		if(investorsConsumption.size() == insertedInvestorsConsumption.size() && insertedInvestorsConsumption.size()>0){
			//This is a way to convert List,ArrayList to jsonString using JAX-RS(jersey's moxy conversion)
			GenericEntity<ArrayList<InvestorConsumption>> responseEntity = new GenericEntity<ArrayList<InvestorConsumption>>(insertedInvestorsConsumption){};
			return Response.status(Status.CREATED)
					.entity(responseEntity)
					.build();
		}else{
			return Response.status(Status.EXPECTATION_FAILED)
					.entity(new ErrorBean("Unable to save investors consumption.Try Again!"))
					.build();
		}
	}

	@GET
	@Path("/plant/{plantId}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Investor> getInvestorsByPlantId(@PathParam("plantId")int plantId){
		System.out.println("GetInvestorsByPlantId for Investors called for plantId : "+plantId);
		ArrayList<Investor> investors = null;
        ArrayList<InvestorPlantMapping> investorPlantMappings = investorPlantMappingDAO.getByPlantId(plantId);
        if(investorPlantMappings != null && investorPlantMappings.size()>0){
        	investors = new ArrayList<Investor>();
        	for(InvestorPlantMapping ipm : investorPlantMappings){
        		Investor investor = investorsDAO.getById(ipm.getInvestorId());
        		if(investor!=null){
        			investors.add(investor);
        		}
        	}
        }
		return investors;
	}
	
	@GET
	@Path("/consumption/{consumptionId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getInvestorsConsumption(@PathParam("consumptionId")int consumptionId){
		System.out.println("GetInvestorsConsumption for Investors Consumption called");
		Consumption consumption = consumptionsDAO.getById(consumptionId);
		ArrayList<InvestorConsumptionView> investorConsumptionViews = null;
		if(consumption != null){
			ArrayList<InvestorConsumption> investorConsumptionList = investorConsumptionDAO.getByConsumptionId(consumptionId);
			investorConsumptionViews= investorConsumptionDAO.getViewFromList(investorConsumptionList, consumption);	
		}

		if(investorConsumptionViews != null){
			//This is a way to convert List,ArrayList to jsonString using JAX-RS(jersey's moxy conversion)
			GenericEntity<ArrayList<InvestorConsumptionView>> responseEntity = new GenericEntity<ArrayList<InvestorConsumptionView>>(investorConsumptionViews){};
			return Response.status(Status.OK)
					.entity(responseEntity)
					.build();
		}else{
			return Response.status(Status.NO_CONTENT)
					.entity(new ErrorBean("No data found for given consumption id."))
					.build();
		}
	}

	@PUT
	@Path("/consumption/{consumptionId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response validateInvestorsConsumption(@PathParam("consumptionId")int consumptionId){
		System.out.println("ValidateInvestorsConsumption for Investors Consumption called");
		Consumption consumption = consumptionsDAO.getById(consumptionId);
		ArrayList<InvestorConsumptionView> investorConsumptionViews = null;
		if(consumption != null){
			ArrayList<InvestorConsumption> investorConsumptionList = investorConsumptionDAO.getByConsumptionId(consumptionId);
			for(InvestorConsumption ic : investorConsumptionList){
				ic.setCircleValidation(1);
			}
			ArrayList<InvestorConsumption> updatedInvestorConsumptions = investorConsumptionDAO.update(investorConsumptionList);
			if(updatedInvestorConsumptions.size() == investorConsumptionList.size() && updatedInvestorConsumptions.size()>0){
				//Now updating the circle_cell_validation flag of corresponding reading of this consumption in database.
				MeterReading meterReading = meterReadingsDAO.getById(consumption.getMeterReadingId());
				meterReading.setCircleCellValidation(1);
				meterReadingsDAO.update(meterReading);
				investorConsumptionViews= investorConsumptionDAO.getViewFromList(updatedInvestorConsumptions, consumption);
			}	
		}
		if(investorConsumptionViews != null){
			//This is a way to convert List,ArrayList to jsonString using JAX-RS(jersey's moxy conversion)
			GenericEntity<ArrayList<InvestorConsumptionView>> responseEntity = new GenericEntity<ArrayList<InvestorConsumptionView>>(investorConsumptionViews){};
			return Response.status(Status.OK)
					.entity(responseEntity)
					.build();
		}else{
			return Response.status(Status.NO_CONTENT)
					.entity(new ErrorBean("No data found for given consumption id.Unable to validate consumptions."))
					.build();
		}
	}

	@GET
	@Path("/{investorId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getInvestorById(@PathParam("investorId")int investorId){
		Investor investor = null;
		investor = investorsDAO.getById(investorId);
		if(investor != null){
			return Response.status(Status.OK)
					.entity(investor)
					.build();
		}else{
			return Response.status(Status.EXPECTATION_FAILED)
					.entity(new ErrorBean("Unable to fetch Investor."))
					.build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Investor> getAllInvestors(){
		return investorsDAO.getAll();
	}
	
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateInvestor(Investor investor){
		Investor updatedInvestor = null;
		updatedInvestor = investorsDAO.update(investor);
		if(updatedInvestor != null){
			return Response.status(Status.OK)
					.entity(updatedInvestor)
					.build();
		}else{
			return Response.status(Status.EXPECTATION_FAILED)
					.entity(new ErrorBean("Unable to update Investor."))
					.build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertInvestor(Investor investor){
		System.err.println("POST for investor insert called");
		Investor insertedInvestor = null;
		insertedInvestor = investorsDAO.insert(investor);
		if(insertedInvestor != null){
			return Response.status(Status.CREATED)
					.entity(insertedInvestor)
					.build();
		}else{
			return Response.status(Status.EXPECTATION_FAILED)
					.entity(new ErrorBean("Unable to create investor"))
					.build();
		}	
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteInvestor(@PathParam("id")int id){
		Investor deletedInvestor = null;
		deletedInvestor = investorsDAO.delete(id);
		if(deletedInvestor != null){
			return Response.status(Status.OK)
					.entity(deletedInvestor)
					.build();
		}else{
			return Response.status(Status.EXPECTATION_FAILED)
					.entity(new ErrorBean("Unable to delete investor"))
					.build();
		}
	}
}
