/**
 * 
 */
package com.ht.resources;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.ht.beans.ErrorBean;
import com.ht.beans.Investor;
import com.ht.beans.InvestorPlantMapping;
import com.ht.beans.Plant;
import com.ht.dao.InvestorPlantMappingDAO;
import com.ht.dao.InvestorsDAO;
import com.ht.dao.PlantsDAO;

/**
 * @author Hp
 *
 */
@Path("/investor-plant-mapping")
public class InvestorPlantMappingResource {

	private InvestorPlantMappingDAO investorPlantMappingDAO = new InvestorPlantMappingDAO();
	private PlantsDAO plantsDAO = new PlantsDAO();
	private InvestorsDAO investorsDAO = new InvestorsDAO();

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertMapping(@QueryParam("plantId")int plantId,@QueryParam("investorId")int investorId){
		Plant plant = plantsDAO.getById(plantId);
		Investor investor = investorsDAO.getById(investorId);
		InvestorPlantMapping insertedMapping = null;
		if(plant != null && investor != null){
			InvestorPlantMapping mappingToInsert = new InvestorPlantMapping();
			mappingToInsert.setPlantId(plant.getId());
			mappingToInsert.setPlantCode(plant.getCode());
			mappingToInsert.setInvestorId(investor.getId());
			mappingToInsert.setInvestorCode(investor.getCode());
			if(investorPlantMappingDAO.getByPlantIdAndInvestorId(plant.getId(), investor.getId()) == null){
				insertedMapping = investorPlantMappingDAO.insert(mappingToInsert);	
			}
		}
		if(insertedMapping != null){
			return Response.status(Status.CREATED)
					.entity(insertedMapping)
					.build();
		}else{
			return Response.status(Status.EXPECTATION_FAILED)
					.entity(new ErrorBean("Unable to create mapping"))
					.build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<InvestorPlantMapping> getAll(){
		return investorPlantMappingDAO.getAllMappings();
	}
}
