/**
 * 
 */
package com.ht.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ht.beans.Developer;
import com.ht.beans.MeterDetails;
import com.ht.beans.Plant;
import com.ht.beans.User;
import com.ht.beans.UserRoles;
import com.ht.dao.DevelopersDAO;
import com.ht.dao.MeterDetailsDAO;
import com.ht.dao.PlantsDAO;
import com.ht.dao.UserDAO;
import com.ht.dao.UserRolesDAO;

/**
 * @author NITISH
 *
 */
public class ImportUtility {

	private MeterDetailsDAO meterDetailsDAO = new MeterDetailsDAO();
	private PlantsDAO plantDAO = new PlantsDAO();
	private DevelopersDAO developerDAO = new DevelopersDAO();
	private UserDAO userDAO = new UserDAO();
	private UserRolesDAO userRolesDAO = new UserRolesDAO();
	
	private ExportUtility exportUtility = new ExportUtility();
	
	public void importUsers(){
		try{
			System.out.println("Importing Users...");
			File file = new File("C:\\Users\\Hp\\Desktop\\ht import data\\AGAR\\@user name.xlsx");
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);
			int totalRows = sheet.getPhysicalNumberOfRows();
			System.out.println("There are: "+totalRows+" users to import into database");
			XSSFRow headerRow = sheet.getRow(0);
			headerRow.createCell(8).setCellValue("GENERATED ID");
			int i = 1;
			ArrayList<User> insertedUsers = new ArrayList<User>();
			ArrayList<UserRoles> insertedUserRoles = new ArrayList<UserRoles>();
			while(i < totalRows){
				XSSFRow row = sheet.getRow(i);
				User user = new User();
				user.setUsername(row.getCell(0).toString().trim());
				user.setPassword(row.getCell(1).toString().trim());
				user.setName(row.getCell(2).toString().trim());
				User existingUser = userDAO.getByUsername(user.getUsername());
				UserRoles existingRole = userRolesDAO.getByUsername(user.getUsername());
				if(existingUser == null && existingRole == null){
					boolean inserted = userDAO.insert(user);
					if(inserted){
						User insertedUser = userDAO.getByUsername(user.getUsername());
						row.createCell(7).setCellValue(insertedUser.getId());
						insertedUsers.add(insertedUser);
						System.out.println("Inserted User");
						UserRoles userRole = new UserRoles();
						userRole.setUsername(insertedUser.getUsername());
						userRole.setRole(row.getCell(3).toString().trim().toLowerCase());
						userRole.setRegion(row.getCell(4).toString().trim());
						userRole.setCircle(row.getCell(5).toString().trim());
						userRole.setDivision(row.getCell(6).toString().trim());
						if(userRolesDAO.insert(userRole)){
							insertedUserRoles.add(userRolesDAO.getByUsername(userRole.getUsername()));
							System.out.println("Inserted User Role");
						}
					}
				}
				i++;
			}
			fis.close();
			FileOutputStream fos =new FileOutputStream(new File("C:\\Users\\Hp\\Desktop\\ht import data\\AGAR\\@user name.xlsx"));
			workbook.write(fos);
			fos.close();
			workbook.close();
			System.out.println("Users Importing complete..");
			System.out.println("Generating Users Import Excel..");
			exportUtility.exportUsers(insertedUsers);
			exportUtility.exportUserRoles(insertedUserRoles);
			System.out.println("Created Imported Excel for Users & User Roles");
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void importDevelopers(){
		try{
			File file = new File("C:\\Users\\Hp\\Desktop\\ht import data\\AGAR\\@DEVELOPER AGAR.xlsx");
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);
			int totalRows = sheet.getPhysicalNumberOfRows();
			System.out.println("There are: "+totalRows+" developers to import into database");
			
			XSSFRow headerRow = sheet.getRow(0);
			headerRow.createCell(11).setCellValue("GENERATED ID");
			int i = 1;
			List<Developer> developers = new ArrayList<Developer>();
			while(i < totalRows){
				XSSFRow row = sheet.getRow(i);
				Developer developer = new Developer();
				developer.setName(row.getCell(0).toString().trim());
				developer.setCin(row.getCell(1).toString().trim());
				developer.setOfficeAddress(row.getCell(2).toString().trim());
				developer.setOfficeContactNo(row.getCell(3).toString().trim());
				developer.setOfficeContactPerson(row.getCell(4).toString().trim());
				developer.setOfficeEmail(row.getCell(5).toString().trim());
				developer.setSiteAddress(row.getCell(6).toString().trim());
				developer.setSiteContactNo(row.getCell(7).toString().trim());
				developer.setSiteContactPerson(row.getCell(8).toString().trim());
				developer.setSiteEmail(row.getCell(9).toString().trim());
				developer.setUsername(row.getCell(10).toString().trim());
				
				Developer existingDeveloper = developerDAO.getByUsername(developer.getUsername());
				if(existingDeveloper == null){
					Developer insertedDeveloper = developerDAO.insert(developer);
					if(insertedDeveloper != null){
						System.out.println("Inserted Developer with Id : "+insertedDeveloper.getId());
						developers.add(insertedDeveloper);
						row.createCell(11).setCellValue(insertedDeveloper.getId());
					}
				}else{
					System.out.println("Skipping since developer already exists...: "+existingDeveloper.getUsername());
				}
				i++;
			}
			fis.close();
			FileOutputStream fos =new FileOutputStream(new File("C:\\Users\\Hp\\Desktop\\ht import data\\AGAR\\@DEVELOPER AGAR.xlsx"));
			workbook.write(fos);
			fos.close();
			workbook.close();
			System.out.println("Importing Developers complete..");
			System.out.println("Generating Developers Excel..");
			exportUtility.exportDevelopers(developers);
			System.out.println("Created Imported Excel for Developers");
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void importPlants(){
		try{
			File file = new File("C:\\Users\\Hp\\Desktop\\ht import data\\AGAR\\@PLANT AGAR.xlsx");
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);
			int totalRows = sheet.getPhysicalNumberOfRows();
			
			System.out.println("There are: "+totalRows+" plants to import into database");
			XSSFRow headerRow = sheet.getRow(0);
			System.out.println(headerRow == null);
			/*headerRow.createCell(18).setCellValue("GENERATED ID");
			
			List<Plant> plants = new ArrayList<Plant>();
			for(int i=1;i < totalRows ; i++){
				XSSFRow row = sheet.getRow(i);
				if(row != null){
					Plant plantToInsert = new Plant();
					XSSFCell cell = null;
					if((cell = row.getCell(0)) != null){
						String code = cell.toString().trim();
						if(code != null && code.length()>0){
							plantToInsert.setCode(code);
						}else{
							System.out.print("Continuing since plant has no code");
							continue;
						}
					}
					
					if((cell = row.getCell(1)) != null){
						plantToInsert.setName(cell.toString().trim());
					}
					
					if((cell = row.getCell(2)) != null){
						plantToInsert.setAddress(cell.toString().trim());
					}
					
					if((cell = row.getCell(3)) != null){
						plantToInsert.setContactNo(cell.toString().trim());
					}
					
					if((cell = row.getCell(4)) != null){
						plantToInsert.setContactPerson(cell.toString().trim());
					}
					
					if((cell = row.getCell(5)) != null){
						plantToInsert.setEmail(cell.toString().trim());
					}
					
					if((cell = row.getCell(6)) != null){
						plantToInsert.setCommissionedDate(cell.toString().trim());
					}
					
					if((cell = row.getCell(7)) != null){
						plantToInsert.setType(cell.toString().trim());
					}
					
					if((cell = row.getCell(8)) != null){
						plantToInsert.setCircuitVoltage(cell.toString().trim());
					}
					
					if((cell = row.getCell(9)) != null){
						plantToInsert.setInjectingSubstation(cell.toString().trim());
					}
					
					if((cell = row.getCell(10)) != null){
						plantToInsert.setFeederName(cell.toString().trim());
					}
					
					if((cell = row.getCell(11)) != null){
						plantToInsert.setRegion(cell.toString().trim());
					}
					
					if((cell = row.getCell(12)) != null){
						plantToInsert.setCircle(cell.toString().trim());
					}
					
					if((cell = row.getCell(13)) != null){
						plantToInsert.setDivision(cell.toString().trim());
					}
					
					if((cell = row.getCell(14)) != null){
						String mainMeterNo = cell.toString().trim();
						MeterDetails meterDetails = meterDetailsDAO.getByMeterNo(mainMeterNo);
						if(meterDetails != null){
							plantToInsert.setMainMeterNo(meterDetails.getMeterNo().trim());
						}else{
							System.out.println("Continuing Since Main Meter Does not exists");
							continue;
						}
					}
					
					if((cell = row.getCell(15)) != null){
						plantToInsert.setCheckMeterNo(cell.toString().trim());
					}
					
					if((cell = row.getCell(16)) != null){
						plantToInsert.setStandByMeterNo(cell.toString().trim());
					}
					
					if((cell = row.getCell(17)) != null){
						String username = cell.toString().trim();
						Developer developer = developerDAO.getByUsername(username);
						if(developer != null){
							plantToInsert.setDeveloperId(developer.getId());
						}else{
							System.out.println("Continuing since no developer found with username: "+username);
							continue;
						}
						
					}
					Plant existingPlant = plantDAO.getByCode(plantToInsert.getCode());
					if(existingPlant == null){
						Plant insertedPlant = plantDAO.insert(plantToInsert);
						if(insertedPlant != null){
							System.out.println("Inserted Plant with Id : "+insertedPlant.getId());
							plants.add(insertedPlant);
							row.createCell(18).setCellValue(insertedPlant.getId());
						}
					}else{
						System.out.println("Skipping since plant already exists...: "+existingPlant.getCode());
					}
				}
			}
			fis.close();
			FileOutputStream fos =new FileOutputStream(new File("C:\\Users\\Hp\\Desktop\\ht import data\\AGAR\\@PLANT AGAR.xlsx"));
			workbook.write(fos);
			fos.close();
			workbook.close();*/
			System.out.println("Importing Plants complete..");
			System.out.println("Generating Plants Excel..");
			//exportUtility.exportPlants(plants);
			System.out.println("Created Imported Excel for Plants");
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void main(String gg[]){
		System.out.println("Running import..");
	    ImportUtility iu = new ImportUtility();
	    iu.importPlants();
	}
}
