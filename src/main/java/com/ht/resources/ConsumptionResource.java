/**
 * 
 */
package com.ht.resources;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.ht.beans.MeterReading;
import com.ht.beans.PlantMeterMapping;
import com.ht.dao.ConsumptionsDAO;
import com.ht.dao.MeterReadingsDAO;
import com.ht.dao.PlantMeterMappingDAO;

/**
 * @author NITISH
 *
 */
@Path("/consumptions")
public class ConsumptionResource {

	private ConsumptionsDAO consumptionsDAO=new ConsumptionsDAO();
	private PlantMeterMappingDAO plantMeterMappingDAO = new PlantMeterMappingDAO();
	private MeterReadingsDAO MeterReadingsDAO = new MeterReadingsDAO();

	/**
	 * method to add consumption
	 * @param consumption
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addConsumption(Consumption consumption){
		System.out.println("AddConsumption started for consumption  "+consumption.getActiveConsumption());
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
	
	/**
	 * method to get the consumptions for a given plantId
	 * @param plantId
	 * @return
	 */
	@GET
	@Path("/metermapping/{plantId}/consumption/{consumptionId}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Consumption> getByPlantMeterMapping(@PathParam("plantId")int plantId, @PathParam("consumptionId")int consumptionId){
		System.out.println("getByPlantMeterMapping by plantId called");
		ArrayList<Consumption> consumptions = null;
		ArrayList<PlantMeterMapping> plantMeterMappings = plantMeterMappingDAO.getByPlantId(plantId);
		Consumption consumption = consumptionsDAO.getById(consumptionId);
		MeterReading meterReading = MeterReadingsDAO.getById(consumption.getMeterReadingId());
		System.out.println(meterReading);
		if(plantMeterMappings != null){
			System.out.println("size of mapping array is: "+plantMeterMappings.size());
			consumptions = new ArrayList<Consumption>();
			for(PlantMeterMapping mapping : plantMeterMappings){
				MeterReading reading = MeterReadingsDAO.getReadingsByMeterAndReadingMonth(mapping.getMeterNo(), meterReading.getReadingDate());
				System.out.println(reading);
				if(reading != null){
					consumptions.add(consumptionsDAO.getByMeterReadingId(reading.getId()));
				}else{
					System.out.println("Reading not found for another meter mapped with this plant for Month.");
					consumptions = null;
					break;
				}
			}
		}else{
			System.out.println("Unable to get the Plant Meter Mapping");
		}
		return consumptions;
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
		Consumption updatedConsumption = consumptionsDAO.update(consumption);
		if(updatedConsumption != null){
			return Response.status(Status.OK)
					.entity(updatedConsumption)
					.build();
		}else{
			return Response.status(Status.EXPECTATION_FAILED)
					.entity(new ErrorBean("Unable to update bifurcation flag"))
					.build();
		}
	}
	
	@PUT
	@Path("/bifercate")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateBifurcationFlagForMultipleConsumptions(Consumption consumption){
		Consumption updatedConsumption = consumptionsDAO.update(consumption);
		
		
		if(updatedConsumption != null){
			return Response.status(Status.OK)
					.entity(updatedConsumption)
					.build();
		}else{
			return Response.status(Status.EXPECTATION_FAILED)
					.entity(new ErrorBean("Unable to update bifurcation flag"))
					.build();
		}
	}
}
