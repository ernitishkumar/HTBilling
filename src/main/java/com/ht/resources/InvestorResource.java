/**
 * 
 */
package com.ht.resources;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ht.beans.Investor;
import com.ht.beans.InvestorConsumptionView;
import com.ht.beans.InvestorPlantMapping;
import com.ht.dao.InvestorPlantMappingDAO;
import com.ht.dao.InvestorsDAO;
import com.ht.dao.MachinesDAO;

/**
 * @author NITISH
 *
 */
@Path("/investors")
public class InvestorResource {
	
	private InvestorPlantMappingDAO investorPlantMappingDAO=new InvestorPlantMappingDAO();
	private InvestorsDAO investorsDAO = new InvestorsDAO();
	private MachinesDAO machinesDAO = new MachinesDAO();
	
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
}
