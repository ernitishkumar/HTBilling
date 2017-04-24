package com.ht.filters;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;

import com.ht.beans.User;
import com.ht.dao.UserDAO;

@Provider
public class SecurityFilter implements ContainerRequestFilter{

	private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
	private static final String AUTHENTICATION_URL = "authentication/login";
	
	private UserDAO userDAO = new UserDAO();

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		if(!requestContext.getUriInfo().getPath().contains(AUTHENTICATION_URL)){
			List<String> authHeaders = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
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
						//System.out.println("REST Request Authenticated for : "+user.getName());
						return;
					}
				}
			}else{
				MultivaluedMap<String, String> queryParameters = requestContext.getUriInfo().getQueryParameters();
				String param = queryParameters.get("Authorization").get(0);
				//System.out.println("Auth param : "+param);
				if(param != null)
				{
					param = param.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
					param = Base64.decodeAsString(param);
					StringTokenizer tokenizer = new StringTokenizer(param, ":");
					String username = tokenizer.nextToken();
					String password = tokenizer.nextToken();
					if(username != null && password !=null){
						User user=userDAO.getByUsername(username);
						if(user!=null && user.getPassword().equals(password)){
							//System.out.println("REST Request Authenticated for : "+user.getName());
							return;
						}
					}
				}
				System.out.println("Authorization Headers are null. Unauthorized Access !"+param);
	
			}
			Response unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED)
					.entity("Not a registered user. Unauthorized access!").build();
			requestContext.abortWith(unauthorizedStatus);
		}
	}
}