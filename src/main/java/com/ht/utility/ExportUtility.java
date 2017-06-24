/**
 * 
 */
package com.ht.utility;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ht.beans.BillDetailsView;
import com.ht.beans.Machine;
import com.ht.beans.ViewMeterReadings;


/**
 * @author NITISH
 *
 */
public class ExportUtility {
	
	public String exportReadings(ArrayList<ViewMeterReadings> meterReadings) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFCellStyle style = getHeaderCellStyle(workbook);
		
	    HSSFSheet sheet = workbook.createSheet("Readings");
	    HSSFRow rowhead = sheet.createRow(0);
	    
	    rowhead.createCell(0).setCellValue("METER NO");
	    rowhead.createCell(1).setCellValue("DEVELOPER NAME");
	    rowhead.createCell(2).setCellValue("ADDRESS");
	    rowhead.createCell(3).setCellValue("MF");
	    rowhead.createCell(4).setCellValue("ADJUSTMENT");
	    rowhead.createCell(5).setCellValue("PREVIOUS READING DATE");
	    rowhead.createCell(6).setCellValue("CURRENT READING DATE");
	    rowhead.createCell(7).setCellValue("PREVOIUS ACTIVE ENERGY");
	    rowhead.createCell(8).setCellValue("CURRENT ACTIVE ENERGY");
	    rowhead.createCell(9).setCellValue("ACTIVE DIFF");
	    rowhead.createCell(10).setCellValue("ACTIVE CONSUMPTION");
	    rowhead.createCell(11).setCellValue("PREVOIUS TOD1");
	    rowhead.createCell(12).setCellValue("CURRENT TOD1");
	    rowhead.createCell(13).setCellValue("TOD1 DIFF");
	    rowhead.createCell(14).setCellValue("TOD1 CONSUMPTION");
	    rowhead.createCell(15).setCellValue("PREVOIUS TOD2");
	    rowhead.createCell(16).setCellValue("CURRENT TOD2");
	    rowhead.createCell(17).setCellValue("TOD2 DIFF");
	    rowhead.createCell(18).setCellValue("TOD2 CONSUMPTION");
	    rowhead.createCell(19).setCellValue("PREVOIUS TOD3");
	    rowhead.createCell(20).setCellValue("CURRENT TOD3");
	    rowhead.createCell(21).setCellValue("TOD3 DIFF");
	    rowhead.createCell(22).setCellValue("TOD3 CONSUMPTION");
	    rowhead.createCell(23).setCellValue("HT VALIDATION");
	    
	    //Setting HEADER Row Styles
	    for(int c=0;c<24;c++){
	    	rowhead.getCell(c).setCellStyle(style);
	    }
	    
	    int i=1;
	    for(ViewMeterReadings viewMeterReading : meterReadings){
	    	BigDecimal tod1 = viewMeterReading.getPreviousMeterReading().getActiveTodOne().subtract(viewMeterReading.getCurrentMeterReading().getActiveTodOne());
	    	BigDecimal tod2 = viewMeterReading.getPreviousMeterReading().getActiveTodTwo().subtract(viewMeterReading.getCurrentMeterReading().getActiveTodTwo());
	    	BigDecimal tod3 = viewMeterReading.getPreviousMeterReading().getActiveTodThree().subtract(viewMeterReading.getCurrentMeterReading().getActiveTodThree());
	    	BigDecimal activeConsumption = viewMeterReading.getCurrentMeterReading().getActiveEnergy().subtract(viewMeterReading.getPreviousMeterReading().getActiveEnergy());
	    	BigDecimal mf = viewMeterReading.getPreviousMeterReading().getMf();
	    	
	        HSSFRow row = sheet.createRow(i);
	        row.createCell(0).setCellValue(viewMeterReading.getMeterNo());
		    row.createCell(1).setCellValue(viewMeterReading.getDeveloper().getName());
		    row.createCell(2).setCellValue(viewMeterReading.getPlant().getAddress());
		    row.createCell(3).setCellValue(mf.doubleValue());
		    row.createCell(4).setCellValue(viewMeterReading.getCurrentMeterReading().getAdjustment().doubleValue());
		    row.createCell(5).setCellValue(viewMeterReading.getPreviousMeterReading().getReadingDate());
		    row.createCell(6).setCellValue(viewMeterReading.getCurrentMeterReading().getReadingDate());
		    row.createCell(7).setCellValue(viewMeterReading.getPreviousMeterReading().getActiveEnergy().doubleValue());
		    row.createCell(8).setCellValue(viewMeterReading.getCurrentMeterReading().getActiveEnergy().doubleValue());
		    row.createCell(9).setCellValue(activeConsumption.doubleValue());
		    row.createCell(10).setCellValue(activeConsumption.multiply(mf).doubleValue());
		    row.createCell(11).setCellValue(viewMeterReading.getPreviousMeterReading().getActiveTodOne().doubleValue());
		    row.createCell(12).setCellValue(viewMeterReading.getCurrentMeterReading().getActiveTodOne().doubleValue());
		    row.createCell(13).setCellValue(tod1.doubleValue());
		    row.createCell(14).setCellValue(tod1.multiply(mf).doubleValue());
		    row.createCell(15).setCellValue(viewMeterReading.getPreviousMeterReading().getActiveTodTwo().doubleValue());
		    row.createCell(16).setCellValue(viewMeterReading.getCurrentMeterReading().getActiveTodTwo().doubleValue());
		    row.createCell(17).setCellValue(tod2.doubleValue());
		    row.createCell(18).setCellValue(tod2.multiply(mf).doubleValue());
		    row.createCell(19).setCellValue(viewMeterReading.getPreviousMeterReading().getActiveTodTwo().doubleValue());
		    row.createCell(20).setCellValue(viewMeterReading.getCurrentMeterReading().getActiveTodTwo().doubleValue());
		    row.createCell(21).setCellValue(tod3.doubleValue());
		    row.createCell(22).setCellValue(tod3.multiply(mf).doubleValue());
		    row.createCell(23).setCellValue(viewMeterReading.getCurrentMeterReading().getHtCellValidation());
		    i++;
	    }
	    try {
			FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Public\\Readings.xls");
			workbook.write(fileOut);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try{
				workbook.close();
			}catch(Exception e){
				System.out.println("Exception while closing working "+e.getMessage());
				e.printStackTrace();
			}
			
		}
	    return "C:\\Users\\Public\\Readings.xls";
	}
	
	public String exportConsumptionReport(ArrayList<BillDetailsView> billDetailsView) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFCellStyle style = getHeaderCellStyle(workbook);
		
	    HSSFSheet sheet = workbook.createSheet("ConsumptionReport");
	    HSSFRow rowhead = sheet.createRow(0);
	    
	    try {
	    rowhead.createCell(0).setCellValue("FEEDER NAME");
	    rowhead.createCell(1).setCellValue("SUBSTATION NAME");
	    rowhead.createCell(2).setCellValue("LOCATION");
	    rowhead.createCell(3).setCellValue("DEVELOPER NAME");
	    rowhead.createCell(4).setCellValue("INVESTOR NAME");
	    rowhead.createCell(5).setCellValue("PPA LETTER NO");
	    rowhead.createCell(6).setCellValue("PPA DATE");
	    rowhead.createCell(7).setCellValue("MACHINES");
	    rowhead.createCell(8).setCellValue("NO OF MACHINES");
	    rowhead.createCell(9).setCellValue("CAPACITY");
	    rowhead.createCell(10).setCellValue("BILL gENERATION DATE");
	    rowhead.createCell(11).setCellValue("MONTH WISE ENERGY");
	    rowhead.createCell(12).setCellValue("ADJUSTMENT");
	    rowhead.createCell(13).setCellValue("REMARK");
	    //Setting HEADER Row Styles
	    for(int c=0;c<14;c++){
	    	rowhead.getCell(c).setCellStyle(style);
	    }
	    int i=1;
	    for(BillDetailsView billDetail : billDetailsView){
	    	String machines = "";
	    	for(Machine machine : billDetail.getMachines()){
	    		machines = machines+machine.getCode()+", ";
	    	}
	        HSSFRow row = sheet.createRow(i);
	        row.createCell(0).setCellValue(billDetail.getPlant().getFeederName());
		    row.createCell(1).setCellValue(billDetail.getPlant().getInjectingSubstation());
		    row.createCell(2).setCellValue(billDetail.getPlant().getAddress());
		    row.createCell(3).setCellValue(billDetail.getPlant().getDeveloper().getName());
		    row.createCell(4).setCellValue(billDetail.getInvestor().getName());
		    if(billDetail.getMachines().size() > 0){
		    	row.createCell(5).setCellValue(billDetail.getMachines().get(0).getPpaLetterNo());
		    	row.createCell(6).setCellValue(billDetail.getMachines().get(0).getPpaDate());
		    }
		    row.createCell(7).setCellValue(machines);
		    row.createCell(8).setCellValue(billDetail.getMachines().size());
		    if(billDetail.getMachines().size() > 0){
		    	row.createCell(9).setCellValue(billDetail.getMachines().get(0).getCapacity());
		    }
		    row.createCell(10).setCellValue(billDetail.getBillGenerationDate());
		    row.createCell(11).setCellValue(billDetail.getInvestorConsumptionView().getActiveConsumption().doubleValue());
		    row.createCell(12).setCellValue(billDetail.getInvestorConsumptionView().getAdjustment().doubleValue());
		    i++;
	    }
	   
			FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Public\\ConsumptionReport.xls");
			workbook.write(fileOut);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch(Exception e){
			System.out.println("Exception aaya " + e.getMessage());
			e.printStackTrace();
		}
	    finally{
			try{
				workbook.close();
			}catch(Exception e){
				System.out.println("Exception while closing working "+e.getMessage());
				e.printStackTrace();
			}
			
		}
	    return "C:\\Users\\Public\\ConsumptionReport.xls";
	}
	
	public HSSFCellStyle getHeaderCellStyle(HSSFWorkbook workbook){
		HSSFCellStyle style = workbook.createCellStyle();
	    HSSFFont font = workbook.createFont();
	    font.setFontName(HSSFFont.FONT_ARIAL);
	    font.setFontHeightInPoints((short)10);
	    font.setBold(true);
	    style.setFont(font);
	    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	    style.setBorderTop(HSSFCellStyle.BORDER_THIN);
	    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	    return style;
	}
}
