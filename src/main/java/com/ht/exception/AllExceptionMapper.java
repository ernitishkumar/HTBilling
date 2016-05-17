/**
 * 
 */
package com.ht.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.ht.beans.ErrorBean;

/**
 * @author NITISH
 *
 */
@Provider
public class AllExceptionMapper implements ExceptionMapper<Exception>{

	@Override
	public Response toResponse(Exception exception) {
		ErrorBean errorBean = new ErrorBean(exception.getMessage());
		return Response.status(Status.BAD_REQUEST)
			   .entity(errorBean)
			   .build();
	}

}
