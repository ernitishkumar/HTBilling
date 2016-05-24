package com.ht.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.ht.beans.ErrorBean;
import com.ht.beans.User;
import com.ht.beans.UserDetails;
import com.ht.beans.UserRoles;
import com.ht.dao.UserDAO;
import com.ht.dao.UserRolesDAO;

@Path("/users")
public class UserResource {
	
	/*
	 * Object variable userDAO and userRolesDAO
	 */
	private UserDAO userDAO = new UserDAO();
	private UserRolesDAO userRolesDAO = new UserRolesDAO();
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insert(UserDetails userDetails){
		User user = new User();
		UserRoles userRoles = new UserRoles();
		user.setUsername(userDetails.getUsername());
		user.setPassword(userDetails.getPassword());
		user.setName(userDetails.getName());
		user.setUsername(userDetails.getUsername());
		userRoles.setUsername(userDetails.getUsername());
		userRoles.setRole(userDetails.getUserRole());
		userRoles.setRegion(userDetails.getRegion());
		userRoles.setCircle(userDetails.getCircle());
		userRoles.setDivision(userDetails.getDivision());
		boolean insertedUser = userDAO.insert(user);
		boolean insertedUserRoles = userRolesDAO.insert(userRoles);
		if(insertedUser == true && insertedUserRoles == true){
			return Response.status(Status.CREATED)
					.entity(userDetails)
					.build();
		}else{
			return Response.status(Status.EXPECTATION_FAILED)
					.entity(new ErrorBean("Unable to create the User.."))
					.build();
		}
	}

}
