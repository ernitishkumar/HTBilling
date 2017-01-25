/**
 * 
 */
package com.ht.resources;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.ht.utility.AMRFileLoader;
import com.ht.utility.AMRFileParser;

/**
 * @author NITISH
 *
 */
@Path("/amr")
public class AMRResource {

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadAMRFile(@FormDataParam("file")InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail){
		System.out.println("AMR File upload started with file name as: "+fileDetail.getFileName());
		boolean inserted = true;
		if(uploadedInputStream != null && fileDetail != null ){
			String savedPMRFilePath = AMRFileLoader.saveAMRFile(uploadedInputStream,fileDetail.getFileName());
			if(savedPMRFilePath != null){
				System.out.println("AMR FIlE saved successfully at: "+savedPMRFilePath);
				System.out.println("PARSING AMR file...");
				AMRFileParser.parseAMRFile(savedPMRFilePath);
				System.out.println("AMR File operation successfull");
			}else{
				System.out.println("Unable to save dtr pmr file in folder.Please check");
			}
		}else{
			System.out.println("InputStream or FileDetail is null for AMR File Upload");
		}

		if(inserted){
			return Response.status(Status.CREATED)
					.build();
		}else{
			return Response.status(Status.EXPECTATION_FAILED)
					.build();
		}
	}
}
