/**
 * 
 */
package com.ht.resources;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.ht.beans.ErrorBean;
import com.ht.beans.Machine;
import com.ht.dao.MachinesDAO;

/**
 * @author NITISH
 *
 */
@Path("/machines")
public class MachineResource {

	private MachinesDAO machinesDAO = new MachinesDAO();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertMachine(Machine machine){
		System.out.println("POST insertMachine started");
		Machine insertedMachine = null;
		if(machine != null){
			insertedMachine = machinesDAO.insert(machine);
		}
		if(insertedMachine != null){
			return Response.status(Status.CREATED)
					.entity(insertedMachine)
					.build();
		}else{
			return Response.status(Status.EXPECTATION_FAILED)
					.entity(new ErrorBean("Unable to insert machine"))
					.build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Machine> getAllMachines(){
		System.out.println("GET All machines started");
		return machinesDAO.getAll();
	}
}
