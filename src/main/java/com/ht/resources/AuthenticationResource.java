package com.ht.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ht.beans.Result;
import com.ht.beans.User;
import com.ht.beans.UserRoles;
import com.ht.dao.UserDAO;
import com.ht.dao.UserRolesDAO;

/**
 * @author NITISH
 *
 */
@Path("authentication")
public class AuthenticationResource {
    
	private UserDAO userDAO = new UserDAO();
	private UserRolesDAO userRolesDAO = new UserRolesDAO();
	
	@GET
	@Path("login")
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@QueryParam("username")String username,@QueryParam("password")String password){
		Result result = new Result();
		if(username!=null && password!=null){
			User user = userDAO.getByUsername(username);
			if(user != null && user.getPassword().equals(password)){
				UserRoles userRoles = userRolesDAO.getByUsername(username);
				user.setPassword("");
                result.setLoginResult("Success");
				result.setUser(user);
				result.setUserRoles(userRoles);
				return Response.status(Response.Status.OK).entity(result).build();
			}
		}
		result.setLoginResult("Failure");
		return Response.status(Response.Status.UNAUTHORIZED).entity(result).build();
	}
}
