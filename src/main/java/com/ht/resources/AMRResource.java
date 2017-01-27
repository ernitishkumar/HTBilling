/**
 * 
 */
package com.ht.resources;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.ht.beans.AMRReading;
import com.ht.dao.AMRReadingDAO;
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
		AMRReading amrReadingToInsert = null;
		AMRReading readingAlreadyInserted = null;
		AMRReading insertedReadning = null;
		if(uploadedInputStream != null && fileDetail != null ){
			String savedPMRFilePath = AMRFileLoader.saveAMRFile(uploadedInputStream,fileDetail.getFileName());
			if(savedPMRFilePath != null){
				System.out.println("AMR FIlE saved successfully at: "+savedPMRFilePath);
				System.out.println("PARSING AMR file...");
				amrReadingToInsert = AMRFileParser.parseAMRFile(savedPMRFilePath);
				if(amrReadingToInsert != null){
					readingAlreadyInserted = AMRReadingDAO.getByMeterNoAndReadingDate(amrReadingToInsert.getMeterNo(), amrReadingToInsert.getReadingDate());
					if( readingAlreadyInserted == null){
						amrReadingToInsert.setUploadedOn(new Date());
						amrReadingToInsert.setStatus(0);
						insertedReadning = AMRReadingDAO.insert(amrReadingToInsert);
						System.out.println("AMR File operation successfull");
					}else{
						System.out.println("Unable to insert reading because reading is already present. Please check");
					}
				}
			}else{
				System.out.println("Unable to save dtr pmr file in folder.Please check");
			}
		}else{
			System.out.println("InputStream or FileDetail is null for AMR File Upload");
		}

		if(insertedReadning != null){
			return Response.status(Status.CREATED)
					.build();
		}else{
			return Response.status(Status.EXPECTATION_FAILED)
					.build();
		}
	}
	
	@GET
	@Path("/readings")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<AMRReading> getAllMeters(){
		System.out.println("Getting all AMR Readings");
		return AMRReadingDAO.getAll(0);
	}
	
}
