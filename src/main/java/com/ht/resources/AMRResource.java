/**
 * 
 */
package com.ht.resources;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

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

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.ht.beans.AMRReading;
import com.ht.beans.ErrorBean;
import com.ht.beans.MeterDetails;
import com.ht.beans.MeterReading;
import com.ht.dao.AMRReadingDAO;
import com.ht.dao.MeterDetailsDAO;
import com.ht.dao.MeterReadingsDAO;
import com.ht.utility.AMRFileLoader;
import com.ht.utility.AMRFileParser;

/**
 * @author NITISH
 *
 */
@Path("/amr")
public class AMRResource {
	
	MeterDetailsDAO meterDetailsDAO = new MeterDetailsDAO();
	MeterReadingsDAO meterReadingsDAO = new MeterReadingsDAO();

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
			ErrorBean error = new ErrorBean();
			if(readingAlreadyInserted != null){
				error.setErrorMessage("Readings for meter already exists for this month.");
			}
			else if(amrReadingToInsert == null){
				error.setErrorMessage("Unable to Parse the file please check.");
			}else{
				error.setErrorMessage("Unable to Upload the file please check.");
			}
			return Response.status(Status.EXPECTATION_FAILED).entity(error)
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
	
	@DELETE
	@Path("/reading/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeReading(@PathParam("id")int id){
		AMRReading removedReading = null;
		removedReading = AMRReadingDAO.delete(id);
		if(removedReading != null){
			return Response.status(Status.OK)
					.entity(removedReading)
					.build();
		}else{
			return Response.status(Status.EXPECTATION_FAILED)
					.entity(new ErrorBean("Unable to delete reading."))
					.build();
		}
	}
	
	@PUT
	@Path("/reading")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public MeterReading approve(AMRReading amrReading){
		MeterDetails meterDetails = meterDetailsDAO.getByMeterNo(amrReading.getMeterNo());
		System.out.println(meterDetails.getMf());
		MeterReading meterReading = null;
		if(meterDetails != null){
			MeterReading previousReading = meterReadingsDAO.getCurrentMonthMeterReadings(amrReading.getMeterNo());
			System.out.println("meter present in DB "+meterDetails.getMf());
			meterReading = new MeterReading(amrReading,meterDetails);
			boolean isReadingAlreadyAdded = meterReadingsDAO.isReadingAlreadyAdded(meterReading);  
			if(meterReading.getActiveEnergy().compareTo(previousReading.getActiveEnergy()) == 1 &&
					meterReading.getActiveTodOne().compareTo(previousReading.getActiveTodOne()) ==1 &&
					meterReading.getActiveTodTwo().compareTo(previousReading.getActiveTodTwo())==1 &&
					meterReading.getActiveTodThree().compareTo(previousReading.getActiveTodThree())==1 && isReadingAlreadyAdded){
				meterReadingsDAO.insert(meterReading);
			}
		}
		return meterReading;
		
	}
	
}
