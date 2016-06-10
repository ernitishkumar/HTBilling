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
	
	@GET
	@Path("/{machineId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getInvestorById(@PathParam("machineId")int machineId){
		System.out.println(machineId);
		Machine machine = null;
		machine = machinesDAO.getById(machineId);
		if(machine != null){
			return Response.status(Status.OK)
					.entity(machine)
					.build();
		}else{
			return Response.status(Status.EXPECTATION_FAILED)
					.entity(new ErrorBean("Unable to fetch Machine."))
					.build();
		}
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteMachine(@PathParam("id")int id){
		Machine deletedMachine = null;
		deletedMachine = machinesDAO.delete(id);
		if(deletedMachine != null){
			return Response.status(Status.OK)
					.entity(deletedMachine)
					.build();
		}else{
			return Response.status(Status.EXPECTATION_FAILED)
					.entity(new ErrorBean("Unable to delete machine."))
					.build();
		}
	}
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateMachine(Machine machine){
		Machine updatedMachine = null;
		updatedMachine = machinesDAO.update(machine);
		if(updatedMachine != null){
			return Response.status(Status.OK)
					.entity(updatedMachine)
					.build();
		}else{
			return Response.status(Status.EXPECTATION_FAILED)
					.entity(new ErrorBean("Unable to update Machine."))
					.build();
		}
	}
}
