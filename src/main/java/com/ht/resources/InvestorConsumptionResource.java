/**
 * 
 */
package com.ht.resources;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.ht.beans.InvestorConsumption;
import com.ht.dao.InvestorConsumptionDAO;

/**
 * @author Hp
 *
 */
@Path("investors")
public class InvestorConsumptionResource {

	private InvestorConsumptionDAO investorConsumptionDAO = new InvestorConsumptionDAO();

}
