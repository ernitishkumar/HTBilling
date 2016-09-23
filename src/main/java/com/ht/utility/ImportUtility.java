/**
 * 
 */
package com.ht.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ht.beans.Developer;
import com.ht.beans.User;
import com.ht.beans.UserRoles;
import com.ht.dao.DevelopersDAO;
import com.ht.dao.UserDAO;
import com.ht.dao.UserRolesDAO;

/**
 * @author NITISH
 *
 */
public class ImportUtility {

	private DevelopersDAO developerDAO = new DevelopersDAO();
	
	private UserDAO userDAO = new UserDAO();
	private UserRolesDAO userRolesDAO = new UserRolesDAO();
	
	public void importDevelopers(){
		try{
			File file = new File("C:\\Users\\Hp\\Desktop\\ht import data\\AGAR\\@DEVELOPER AGAR.xlsx");
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);
			int totalRows = sheet.getPhysicalNumberOfRows();
			XSSFRow headerRow = sheet.getRow(0);
			
			headerRow.createCell(12).setCellValue("GENERATED ID");
			
			int i = 1;
			List<Developer> developers = new ArrayList<Developer>();
			while(i < totalRows){
				XSSFRow row = sheet.getRow(i);
				Developer developer = new Developer();
				developer.setName(row.getCell(1).toString().trim());
				developer.setCin(row.getCell(2).toString().trim());
				developer.setOfficeAddress(row.getCell(3).toString().trim());
				developer.setOfficeContactNo(row.getCell(4).toString().trim());
				developer.setOfficeContactPerson(row.getCell(5).toString().trim());
				developer.setOfficeEmail(row.getCell(6).toString().trim());
				developer.setSiteAddress(row.getCell(7).toString().trim());
				developer.setSiteContactNo(row.getCell(8).toString().trim());
				developer.setSiteContactPerson(row.getCell(9).toString().trim());
				developer.setSiteEmail(row.getCell(10).toString().trim());
				developer.setUsername(row.getCell(11).toString().trim());
				
				Developer insertedDeveloper = developerDAO.insert(developer);
				System.out.println(insertedDeveloper);
				developers.add(developer);
				i++;
			}
			workbook.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void importUsers(){
		try{
			File file = new File("C:\\Users\\Hp\\Desktop\\ht import data\\AGAR\\@user name.xlsx");
			
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);
			int totalRows = sheet.getPhysicalNumberOfRows();
			XSSFRow headerRow = sheet.getRow(0);
			
			headerRow.createCell(8).setCellValue("GENERATED ID");
			
			int i = 1;
			List<Developer> developers = new ArrayList<Developer>();
			while(i < totalRows){
				XSSFRow row = sheet.getRow(i);
				User user = new User();
				user.setUsername(row.getCell(1).toString().trim());
				user.setPassword(row.getCell(2).toString().trim());
				user.setName(row.getCell(3).toString().trim());
				
				boolean inserted = userDAO.insert(user);
				if(inserted){
					UserRoles userRole = new UserRoles();
					userRole.setUsername(row.getCell(1).toString().trim());
					userRole.setRole(row.getCell(4).toString().trim().toLowerCase());
					userRole.setRegion(row.getCell(5).toString().trim());
					userRole.setCircle(row.getCell(6).toString().trim());
					userRole.setDivision(row.getCell(7).toString().trim());
					if(userRolesDAO.insert(userRole)){
						row.createCell(8).setCellValue("INSERTED");
						System.out.println("Inserted User and User Roles");
					}
				}
				i++;
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void main(String gg[]){
		System.out.println("Running import..");
	    ImportUtility iu = new ImportUtility();
	    iu.importDevelopers();
	}
}
