/**
 * 
 */
package com.ht.resources;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ht.beans.Developer;
import com.ht.dao.DevelopersDAO;

/**
 * @author Hp
 *
 */
@Path("/developers")
public class DeveloperResource {

	private DevelopersDAO developersDAO = new DevelopersDAO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Developer> getAllDevelopers(){
		System.out.println("GET All developers started");
		return developersDAO.getAllDevelopers();
	}
}
