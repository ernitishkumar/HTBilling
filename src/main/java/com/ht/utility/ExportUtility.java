/**
 * 
 */
package com.ht.utility;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ht.beans.Developer;
import com.ht.beans.Plant;
import com.ht.beans.User;
import com.ht.beans.UserRoles;

/**
 * @author Hp
 *
 */
public class ExportUtility {
	
	public String exportUsers(ArrayList<User> users){
		XSSFWorkbook workbook = new XSSFWorkbook();
		
	    XSSFSheet sheet = workbook.createSheet("IMPORTED_USERS");
	    
	    XSSFCellStyle style = getHeaderCellStyle(workbook);
	    
	    XSSFRow rowhead = sheet.createRow(0);
	    rowhead.createCell(0).setCellValue("S.NO");
	    rowhead.createCell(1).setCellValue("ID");
	    rowhead.createCell(2).setCellValue("USERNAME");
	    rowhead.createCell(3).setCellValue("PASSWORD");
	    rowhead.createCell(4).setCellValue("NAME");
	    
	    //Setting HEADER Row Styles
	    for(int c=0;c<5;c++){
	    	rowhead.getCell(c).setCellStyle(style);
	    }
	    
	    int i=1;
	    for(User user : users){
	        XSSFRow row = sheet.createRow(i);
	        row.createCell(0).setCellValue(i);
	        row.createCell(1).setCellValue(user.getId());
		    row.createCell(2).setCellValue(user.getUsername());
		    row.createCell(3).setCellValue(user.getPassword());
		    row.createCell(4).setCellValue(user.getName());
		    i++;
	    }
	    
	    try {
			FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Hp\\Desktop\\ht import data\\AGAR\\IMPORTED\\IMPORTED_USERS.xlsx");
			workbook.write(fileOut);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try{
				workbook.close();
			}catch(Exception exception){
				System.out.println("Exception while closing workbook");
				exception.printStackTrace();
			}
		}
	    return "C:\\Users\\Hp\\Desktop\\ht import data\\AGAR\\IMPORTED\\IMPORTED_USERS.xlsx";
	}
	
	public String exportUserRoles(ArrayList<UserRoles> userRoles){
		XSSFWorkbook workbook = new XSSFWorkbook();
		
	    XSSFSheet sheet = workbook.createSheet("IMPORTED_USERROLES");
	    
	    XSSFCellStyle style = getHeaderCellStyle(workbook);
	    
	    XSSFRow rowhead = sheet.createRow(0);
	    rowhead.createCell(0).setCellValue("S.NO");
	    rowhead.createCell(1).setCellValue("ID");
	    rowhead.createCell(2).setCellValue("USERNAME");
	    rowhead.createCell(3).setCellValue("ROLE");
	    rowhead.createCell(4).setCellValue("REGION");
	    rowhead.createCell(5).setCellValue("CIRCLE");
	    rowhead.createCell(6).setCellValue("DIVISION");
	    
	    //Setting HEADER Row Styles
	    for(int c=0;c<7;c++){
	    	rowhead.getCell(c).setCellStyle(style);
	    }
	    
	    int i=1;
	    for(UserRoles userRole : userRoles){
	        XSSFRow row = sheet.createRow(i);
	        row.createCell(0).setCellValue(i);
	        row.createCell(1).setCellValue(userRole.getId());
		    row.createCell(2).setCellValue(userRole.getUsername());
		    row.createCell(3).setCellValue(userRole.getRole());
		    row.createCell(4).setCellValue(userRole.getRegion());
		    row.createCell(5).setCellValue(userRole.getCircle());
		    row.createCell(6).setCellValue(userRole.getDivision());
		    i++;
	    }
	    
	    try {
			FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Hp\\Desktop\\ht import data\\AGAR\\IMPORTED\\IMPORTED_USERROLES.xlsx");
			workbook.write(fileOut);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try{
				workbook.close();
			}catch(Exception exception){
				System.out.println("Exception while closing workbook");
				exception.printStackTrace();
			}
		}
	    return "C:\\Users\\Hp\\Desktop\\ht import data\\AGAR\\IMPORTED\\IMPORTED_USERROLES.xlsx";
	}
	
	public String exportDevelopers(List<Developer> developers){
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		
	    XSSFSheet sheet = workbook.createSheet("IMPORTED_DEVELOPERS");
	    
	    XSSFCellStyle style = getHeaderCellStyle(workbook);
	    
	    XSSFRow rowhead = sheet.createRow(0);
	    rowhead.createCell(0).setCellValue("S.NO");
	    rowhead.createCell(1).setCellValue("ID");
	    rowhead.createCell(2).setCellValue("NAME");
	    rowhead.createCell(3).setCellValue("CIN");
	    rowhead.createCell(4).setCellValue("OFFICE ADDRESS");
	    rowhead.createCell(5).setCellValue("OFFICE CONTACT NO");
	    rowhead.createCell(6).setCellValue("OFFICE CONTACT PERSON");
	    rowhead.createCell(7).setCellValue("OFFICE EMAIL");
	    rowhead.createCell(8).setCellValue("SITE ADDRESS");
	    rowhead.createCell(9).setCellValue("SITE CONTACT NO");
	    rowhead.createCell(10).setCellValue("SITE CONTACT PERSON");
	    rowhead.createCell(11).setCellValue("SITE EMAIL");
	    rowhead.createCell(12).setCellValue("USERNAME");
	    
	    //Setting HEADER Row Styles
	    for(int c=0;c<12;c++){
	    	rowhead.getCell(c).setCellStyle(style);
	    }
	    
	    int i=1;
	    for(Developer developer : developers){
	        XSSFRow row = sheet.createRow(i);
	        row.createCell(0).setCellValue(i);
	        row.createCell(1).setCellValue(developer.getId());
		    row.createCell(2).setCellValue(developer.getName());
		    row.createCell(3).setCellValue(developer.getCin());
		    row.createCell(4).setCellValue(developer.getOfficeAddress());
		    row.createCell(5).setCellValue(developer.getOfficeContactNo());
		    row.createCell(6).setCellValue(developer.getOfficeContactPerson());
		    row.createCell(7).setCellValue(developer.getOfficeEmail());
		    row.createCell(8).setCellValue(developer.getSiteAddress());
		    row.createCell(9).setCellValue(developer.getSiteContactNo());
		    row.createCell(10).setCellValue(developer.getSiteContactPerson());
		    row.createCell(11).setCellValue(developer.getSiteEmail());
		    row.createCell(12).setCellValue(developer.getUsername());
		    i++;
	    }
	    
	    try {
			FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Hp\\Desktop\\ht import data\\AGAR\\IMPORTED\\IMPORTED_DEVELOPERS.xlsx");
			workbook.write(fileOut);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try{
				workbook.close();
			}catch(Exception exception){
				System.out.println("Exception while closing workbook");
				exception.printStackTrace();
			}
		}
	    return "C:\\Users\\Hp\\Desktop\\ht import data\\AGAR\\IMPORTED\\IMPORTED_DEVELOPERS.xlsx";
	}
	
public String exportPlants(List<Plant> plants){
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		
	    XSSFSheet sheet = workbook.createSheet("IMPORTED_PLANTS");
	    
	    XSSFCellStyle style = getHeaderCellStyle(workbook);
	    
	    XSSFRow rowhead = sheet.createRow(0);
	    rowhead.createCell(0).setCellValue("S.NO");
	    rowhead.createCell(1).setCellValue("ID");
	    rowhead.createCell(2).setCellValue("CODE");
	    rowhead.createCell(3).setCellValue("NAME");
	    rowhead.createCell(4).setCellValue("ADDRESS");
	    rowhead.createCell(5).setCellValue("CONTACT NO");
	    rowhead.createCell(6).setCellValue("CONTACT PERSON");
	    rowhead.createCell(7).setCellValue("EMAIL");
	    rowhead.createCell(8).setCellValue("COMMISSIONED DATE");
	    rowhead.createCell(9).setCellValue("TYPE");
	    rowhead.createCell(10).setCellValue("CIRCUIT VOLTAGE");
	    rowhead.createCell(11).setCellValue("INJECTING SUBSTATION");
	    rowhead.createCell(12).setCellValue("FEEDER NAME");
	    rowhead.createCell(13).setCellValue("REGION");
	    rowhead.createCell(14).setCellValue("CIRCLE");
	    rowhead.createCell(15).setCellValue("DIVISION");
	    rowhead.createCell(16).setCellValue("MAIN METER NO");
	    rowhead.createCell(17).setCellValue("CHECK METER NO");
	    rowhead.createCell(18).setCellValue("STANDBY METER NO");
	    rowhead.createCell(19).setCellValue("DEVELOPER NAME");
	    
	    //Setting HEADER Row Styles
	    for(int c=0;c<20;c++){
	    	rowhead.getCell(c).setCellStyle(style);
	    }
	    
	    int i=1;
	    for(Plant plant : plants){
	        XSSFRow row = sheet.createRow(i);
	        row.createCell(0).setCellValue(i);
	        row.createCell(1).setCellValue(plant.getId());
		    row.createCell(2).setCellValue(plant.getCode());
		    row.createCell(3).setCellValue(plant.getName());
		    row.createCell(4).setCellValue(plant.getAddress());
		    row.createCell(5).setCellValue(plant.getContactNo());
		    row.createCell(6).setCellValue(plant.getContactPerson());
		    row.createCell(7).setCellValue(plant.getEmail());
		    row.createCell(8).setCellValue(plant.getCommissionedDate());
		    row.createCell(9).setCellValue(plant.getType());
		    row.createCell(10).setCellValue(plant.getCircuitVoltage());
		    row.createCell(11).setCellValue(plant.getInjectingSubstation());
		    row.createCell(12).setCellValue(plant.getFeederName());
		    row.createCell(12).setCellValue(plant.getRegion());
		    row.createCell(12).setCellValue(plant.getCircle());
		    row.createCell(12).setCellValue(plant.getDivision());
		    row.createCell(12).setCellValue(plant.getMainMeterNo());
		    row.createCell(12).setCellValue(plant.getCheckMeterNo());
		    row.createCell(12).setCellValue(plant.getStandByMeterNo());
		    row.createCell(12).setCellValue(plant.getDeveloperId());
		    i++;
	    }
	    
	    try {
			FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Hp\\Desktop\\ht import data\\AGAR\\IMPORTED\\IMPORTED_PLANTS.xlsx");
			workbook.write(fileOut);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try{
				workbook.close();
			}catch(Exception exception){
				System.out.println("Exception while closing workbook");
				exception.printStackTrace();
			}
		}
	    return "C:\\Users\\Hp\\Desktop\\ht import data\\AGAR\\IMPORTED\\IMPORTED_PLANTS.xlsx";
	}
	
	public XSSFCellStyle getHeaderCellStyle(XSSFWorkbook workbook){
		XSSFCellStyle style = workbook.createCellStyle();
	    XSSFFont font = workbook.createFont();
	    font.setFontName(XSSFFont.DEFAULT_FONT_NAME);
	    font.setFontHeightInPoints((short)10);
	    font.setBold(true);
	    style.setFont(font);
	    return style;
	}
}
