/**
 * 
 */
package com.ht.resources;

import java.io.File;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.ht.beans.MeterDetails;
import com.ht.beans.MeterReading;
import com.ht.beans.Plant;
import com.ht.beans.ViewMeterReadings;
import com.ht.dao.ConsumptionsDAO;
import com.ht.dao.DevelopersDAO;
import com.ht.dao.MeterDetailsDAO;
import com.ht.dao.MeterReadingsDAO;
import com.ht.dao.PlantsDAO;
import com.ht.utility.ExportUtility;
import com.ht.utility.GlobalResources;

/**
 * @author Ankit
 *
 */
@Path("/export")
public class ExportResource {

	private MeterReadingsDAO meterReadingsDAO = new MeterReadingsDAO();
	private PlantsDAO plantsDAO = new PlantsDAO();
	private DevelopersDAO developersDAO = new DevelopersDAO();
	private ConsumptionsDAO consumptionDAO = new ConsumptionsDAO();
	private MeterDetailsDAO meterDetailsDAO = new MeterDetailsDAO();
	ExportUtility export = new ExportUtility();
	
	@GET
	@Path("/month/circle/{readingMonth}")
	@Produces("application/vnd.ms-excel")
	public Response getByReadingMonth(@PathParam("readingMonth") String readingMonth, @QueryParam("circle") String circle) {
		String currentReadingMonth = GlobalResources.generateBillMonth(readingMonth);
		String previousBillMonth = GlobalResources.generatePreviousBillMonth(readingMonth);
		ArrayList<ViewMeterReadings> viewReadings = new ArrayList<ViewMeterReadings>();
		System.out.println("getting Data for Circle "+circle);
		ArrayList<Plant> plants = null;
		if(circle==null || circle.equals("")){
			plants = plantsDAO.getAll();
		}else{
			plants = plantsDAO.getByCircle(circle);
		}
		
		for (Plant p : plants) {
			ViewMeterReadings viewMeterReadings = new ViewMeterReadings();
			String meterNo = p.getMainMeterNo();
			MeterDetails meter = meterDetailsDAO.getByMeterNo(meterNo);
			viewMeterReadings.setMeterNo(meterNo);
			if (meter != null) {
				viewMeterReadings.setMeterMake(meter.getMake().toUpperCase());
			}
			viewMeterReadings.setPlant(p);
			viewMeterReadings.setDeveloper(developersDAO.getById(p
					.getDeveloperId()));
			MeterReading currentMonthReading = meterReadingsDAO
					.getReadingsByReadingMonth(meterNo, currentReadingMonth);
			if (currentMonthReading != null
					&& (currentMonthReading.getDiscardedFlag() == 0 || currentMonthReading
							.getHtCellValidation() == 1)) {
				if (currentMonthReading.getSrfrFlag() == 1) {
					// System.out.println("inside if of SR FR" );
					String checkMeterNo = p.getCheckMeterNo();
					viewMeterReadings.setMeterNo(checkMeterNo);
					MeterDetails checkMeter = meterDetailsDAO
							.getByMeterNo(checkMeterNo);
					if (checkMeter != null) {
						viewMeterReadings.setMeterMake(checkMeter.getMake()
								.toUpperCase());
					}
					currentMonthReading = meterReadingsDAO
							.getReadingsByReadingMonth(checkMeterNo, currentReadingMonth);
					viewMeterReadings.setPreviousMeterReading(meterReadingsDAO
							.getReadingsByReadingMonth(checkMeterNo, previousBillMonth));
					viewMeterReadings
							.setCurrentMeterReading(currentMonthReading);
				} else {
					viewMeterReadings.setPreviousMeterReading(meterReadingsDAO
							.getReadingsByReadingMonth(meterNo, previousBillMonth));
					viewMeterReadings
							.setCurrentMeterReading(currentMonthReading);
				}
				if (currentMonthReading != null
						&& currentMonthReading.getDeveloperValidation() == 1) {
					viewMeterReadings.setConsumption(consumptionDAO
							.getByMeterReadingId(currentMonthReading.getId()));
				}
				if (viewMeterReadings.getCurrentMeterReading().getId() != -1) {
					viewReadings.add(viewMeterReadings);
				}
			}
		}
		String fileName = export.exportReadings(viewReadings);
		File file = new File(fileName);

		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition",
				"attachment; filename=List_of_Readings.xls");
		return response.build();
		//return viewReadings;
	}
}
