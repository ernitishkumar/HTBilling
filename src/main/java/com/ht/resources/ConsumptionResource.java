/**
 * 
 */
package com.ht.resources;

import java.text.SimpleDateFormat;
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

import com.ht.beans.Consumption;
import com.ht.beans.ErrorBean;
import com.ht.beans.MessageBean;
import com.ht.dao.ConsumptionsDAO;

/**
 * @author NITISH
 *
 */
@Path("/consumptions")
public class ConsumptionResource {

	private ConsumptionsDAO consumptionsDAO=new ConsumptionsDAO();

	/**
	 * method to add consumption
	 * @param consumption
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addConsumption(Consumption consumption){
		System.out.println("AddConsumption started for consumption : "+consumption);
		Consumption insertedConsumption = null;
		if(consumption != null){
			SimpleDateFormat formater = new SimpleDateFormat("dd-MM-YYYY");
			Date date = new Date();
			String currentDate = formater.format(date);
			consumption.setDate(currentDate);
			insertedConsumption = consumptionsDAO.insert(consumption);
			System.out.println("Created Consumption is : "+insertedConsumption);
		}
		if(insertedConsumption != null){
			return Response.status(Status.CREATED)
					.entity(insertedConsumption)
					.build();
		}else{
			return Response.status(Status.EXPECTATION_FAILED)
					.entity(new Error("Unable to create consumption.Try Again!"))
					.build();
		}
	}

	/**
	 * method to get consumption for a given meterno
	 * @param meterno
	 * @return Consumption
	 */
	@GET
	@Path("/meterno/{meterno}")
	@Produces(MediaType.APPLICATION_JSON)
	public Consumption getByMeterno(@PathParam("meterno")String meterno){
		System.out.println("GetConsumptions by meterno called");
		Consumption consumption = null;
		if(meterno!=null){
			SimpleDateFormat formater = new SimpleDateFormat("dd-MM-YYYY");
			Date date = new Date();
			String currentDate = formater.format(date);
			consumption=consumptionsDAO.getByMeterNoAndDate(meterno.trim(),currentDate);
		}
		return consumption;
	}
	
	/**
	 * method to get consumption for a given consumptionId
	 * @param consumptionId
	 * @return Consumption
	 */
	@GET
	@Path("/{consumptionId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Consumption getByConsumptionId(@PathParam("consumptionId")String consumptionId){
		System.out.println("GetConsumptions by consumptionId called");
		Consumption consumption = null;
		if(consumptionId!=null){
			consumption=consumptionsDAO.getById(Integer.parseInt(consumptionId.trim()));
		}
		return consumption;
	}

	/**
	 * method to get the consumptions for a given plantId
	 * @param plantId
	 * @return
	 */
	@GET
	@Path("/plantid/{plantId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Consumption getByPlantId(@PathParam("plantId")String plantId){
		System.out.println("GetConsumptions by plantId called");
		Consumption consumption = null;
		if(plantId!=null){
			SimpleDateFormat formater = new SimpleDateFormat("dd-MM-YYYY");
			Date date = new Date();
			String currentDate = formater.format(date);
			consumption=consumptionsDAO.getByPlantIdAndDate(Integer.parseInt(plantId.trim()),currentDate);
		}
		return consumption;
	}
	
	@PUT
	@Path("/bifurcation")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateBifurcationFlag(@QueryParam("flag")int flag,@QueryParam("consumptionId")int consumptionId){
		boolean updated = consumptionsDAO.updateConsumptionBifercated(flag, consumptionId);
		if(updated){
			return Response.status(Status.OK)
					.entity(new MessageBean("Updated successfully"))
					.build();
		}else{
			return Response.status(Status.EXPECTATION_FAILED)
					.entity(new ErrorBean("Unable to update bifurcation flag"))
					.build();
		}
	}
	
	@PUT
	@Path("/{consumptionId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateBifurcationFlag(Consumption consumption){
		boolean updated = consumptionsDAO.update(consumption);
		if(updated){
			return Response.status(Status.OK)
					.entity(new MessageBean("Updated successfully"))
					.build();
		}else{
			return Response.status(Status.EXPECTATION_FAILED)
					.entity(new ErrorBean("Unable to update bifurcation flag"))
					.build();
		}
	}
}
