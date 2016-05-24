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

import com.ht.beans.Developer;
import com.ht.beans.ErrorBean;
import com.ht.beans.UserRoles;
import com.ht.dao.DevelopersDAO;
import com.ht.dao.UserRolesDAO;

/**
 * @author Hp
 *
 */
@Path("/developers")
public class DeveloperResource {

	private DevelopersDAO developersDAO = new DevelopersDAO();
	UserRolesDAO userRolesDAO = new UserRolesDAO();
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Developer> getAllDevelopers(){
		System.out.println("GET All developers started");
		return developersDAO.getAllDevelopers();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createDeveloper(Developer developer){
		System.out.println("POST Developer started.");
		Developer createdDeveloper = null;
		if(developer != null){
			createdDeveloper = developersDAO.insert(developer);
		}
		if(createdDeveloper != null){
			return Response.status(Status.CREATED)
					.entity(createdDeveloper)
					.build();
		}else{
			return Response.status(Status.EXPECTATION_FAILED)
					.entity(new ErrorBean("unable to created developer."))
					.build();
		}
	}
	
	@GET
	@Path("/username")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<UserRoles> getDeveloperUsername(){
		System.out.println("GET All getDeveloperUsername started");
		return userRolesDAO.getByDeveloperRole();
	}
	
}
