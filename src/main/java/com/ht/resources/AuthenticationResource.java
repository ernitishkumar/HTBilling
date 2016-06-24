package com.ht.resources;

import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.internal.util.Base64;
import org.glassfish.jersey.servlet.internal.spi.RequestContextProvider;

import com.ht.beans.Result;
import com.ht.beans.User;
import com.ht.beans.UserRoles;
import com.ht.dao.UserDAO;
import com.ht.dao.UserRolesDAO;
import com.sun.research.ws.wadl.Request;

/**
 * @author NITISH
 *
 */
@Path("/authentication")
public class AuthenticationResource {
    
	private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
	
	private UserDAO userDAO = new UserDAO();
	private UserRolesDAO userRolesDAO = new UserRolesDAO();
	
	@GET
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@Context HttpHeaders headers){
		System.out.println("Login started");
		Result result = new Result();
		List<String> authHeaders = headers.getRequestHeader(AUTHORIZATION_HEADER_KEY);
		if(authHeaders!=null && authHeaders.size() > 0){
			String authToken = authHeaders.get(0);
			authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
			authToken = Base64.decodeAsString(authToken);
			StringTokenizer tokenizer = new StringTokenizer(authToken, ":");
			String username = tokenizer.nextToken();
			String password = tokenizer.nextToken();
			if(username != null && password !=null){
				User user=userDAO.getByUsername(username);
				if(user!=null && user.getPassword().equals(password)){
					System.out.println(username+" logged in.");
					UserRoles userRoles = userRolesDAO.getByUsername(username);
					user.setPassword("");
	                result.setLoginResult("Success");
					result.setUser(user);
					result.setUserRoles(userRoles);
					return Response.status(Response.Status.OK).entity(result).build();
				}
			}
		}
		result.setLoginResult("Failure");
		return Response.status(Response.Status.UNAUTHORIZED).entity(result).build();
	}
}
