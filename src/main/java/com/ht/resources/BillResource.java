/**
 * 
 */
package com.ht.resources;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.ht.beans.BillDetails;
import com.ht.beans.BillDetailsView;
import com.ht.beans.Consumption;
import com.ht.beans.ErrorBean;
import com.ht.beans.InvestorConsumption;
import com.ht.beans.Machine;
import com.ht.beans.MeterReading;
import com.ht.dao.BillDetailsDAO;
import com.ht.dao.ConsumptionsDAO;
import com.ht.dao.InvestorConsumptionDAO;
import com.ht.dao.MachinesDAO;
import com.ht.dao.MeterReadingsDAO;
import com.ht.utility.GlobalResources;

/**
 * @author NITISH
 *
 */
@Path("/bill")
public class BillResource {

	private BillDetailsDAO billDetailsDAO = new BillDetailsDAO();
	
	private ConsumptionsDAO consumptionsDAO = new ConsumptionsDAO();
	
	private InvestorConsumptionDAO investorConsumptionDAO = new InvestorConsumptionDAO();
	
	private MeterReadingsDAO meterReadingsDAO = new MeterReadingsDAO();
	
	private MachinesDAO machinesDAO = new MachinesDAO();
	
	/*
	 * currentDate an object variable to hold current date of the system.
	 */
	private String currentDate;
	
	/*
	 * final variable BILL_PREFIX.
	 */
	private final String BILL_PREFIX = "MPWZHT";
	
	/**
	 * Default constructor to intialize the current date of the system.
	 */
	public BillResource() {
		SimpleDateFormat formater = new SimpleDateFormat("dd-MM-YYYY");
		Date date = new Date();
		this.currentDate = formater.format(date);
	}
	
	@GET
	@Path("/{billDetailsId}")
	@Produces(MediaType.APPLICATION_JSON)
	public BillDetailsView getBillDetails(@PathParam("billDetailsId")int billDetailsId){
		System.out.print("Getting Bill Details for ID :  "+billDetailsId);
		BillDetailsView billDetailsView = null;
		BillDetails billDetails = billDetailsDAO.getById(billDetailsId);
		if(billDetails != null){
			billDetailsView = billDetailsDAO.getViewFromBean(billDetails);
		}
		return billDetailsView;
	}
	
	/**
	 * function to Generated Bill and add it to the database.
	 * @param bifurcationId
	 * @return
	 */
	@POST
	@Path("/{bifurcationId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response generateBill(@PathParam("bifurcationId")int bifurcationId){
		System.out.println("GenerateBill for bifurcation id : "+bifurcationId+" started");
		InvestorConsumption investorConsumption = investorConsumptionDAO.getById(bifurcationId);
		BillDetails generatedBillDetails = null;
		if(investorConsumption != null){
			Consumption consumption = consumptionsDAO.getById(investorConsumption.getConsumptionId());
			MeterReading meterReadings = meterReadingsDAO.getById(consumption.getMeterReadingId());
			Machine machine = machinesDAO.getByInvestorId(investorConsumption.getInvestorId()).get(0);
			if(consumption != null && meterReadings!=null && machine != null){
				
				BillDetails billDetails = new BillDetails();
				billDetails.setMeterReadingId(consumption.getMeterReadingId());
				billDetails.setInvestorId(investorConsumption.getInvestorId());
				billDetails.setConsumptionId(consumption.getId());
				billDetails.setConsumptionBifurcationId(investorConsumption.getId());
				billDetails.setMeterNo(consumption.getMeterNo());
				billDetails.setReadingDate(meterReadings.getReadingDate());
				billDetails.setBillGenerationDate(currentDate);
				billDetails.setParticulars(machine.getParticulars());
				
				float activeConsumption = investorConsumption.getActiveConsumption();
				float reactiveConsumption = investorConsumption.getReactiveConsumption();

				float activeRate = machine.getActiveRate();
				float reactiveRate = machine.getReactiveRate();

				float activeAmount = activeConsumption * activeRate;
				float reactiveAmount = reactiveConsumption * reactiveRate;

				float totalAmount = activeAmount - reactiveAmount;
				float totalAmountRoundOff = Math.round (totalAmount);

				billDetails.setTotalKWH(activeConsumption);
				billDetails.setTotalRKVH(reactiveConsumption);
				billDetails.setKwhRate(activeRate);
				billDetails.setRkvhRate(reactiveRate);

				billDetails.setActiveAmount(activeAmount);
				billDetails.setReactiveAmount(reactiveAmount);

				billDetails.setTotalAmount(totalAmount);
				billDetails.setTotalAmountRoundOff(totalAmountRoundOff);
				
				//getting amounts in words.
				String amountInWords = GlobalResources.convert(totalAmountRoundOff);
				billDetails.setTotalAmountInWords(amountInWords);
				
				//Inserting the created bill details in database
				generatedBillDetails = billDetailsDAO.insert(billDetails);
				if(generatedBillDetails != null){
					generatedBillDetails.setBillNo(BILL_PREFIX+generatedBillDetails.getId());
					generatedBillDetails.setInvoiceNo(generatedBillDetails.getBillNo());
					generatedBillDetails = billDetailsDAO.update(generatedBillDetails);
					if(generatedBillDetails != null){
						investorConsumption.setBillGenerated(1);
						investorConsumptionDAO.update(investorConsumption);
					}
				}
			}
		}
		
		//checking whether generatedbill is null or not and sending response accordingly
		if(generatedBillDetails != null){
			return Response.status(Status.CREATED)
					.entity(generatedBillDetails)
					.build();
		}else{
			return Response.status(Status.EXPECTATION_FAILED)
					.entity(new ErrorBean("Unable to generate bill."))
					.build();
		}
	}
}
