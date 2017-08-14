/**
 * 
 */
package com.ht.resources;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.ht.beans.ErrorBean;
import com.ht.beans.PlantMeterMapping;
import com.ht.dao.PlantMeterMappingDAO;

/**
 * @author ANKIT
 *
 */
@Path("/plant-meter-mapping")
public class PlantMeterMappingResource {

	private PlantMeterMappingDAO plantMeterMappingDAO = new PlantMeterMappingDAO();
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertMapping(PlantMeterMapping plantMeterMapping){
		PlantMeterMapping insertedMapping = null;
		if(plantMeterMapping != null){
			insertedMapping = plantMeterMappingDAO.insert(plantMeterMapping);	
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
}
