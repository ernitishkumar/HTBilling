/**
 * 
 */
package com.ht.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ht.beans.Developer;
import com.ht.beans.Investor;
import com.ht.beans.MeterDetails;
import com.ht.beans.Plant;
import com.ht.beans.User;
import com.ht.beans.UserRoles;
import com.ht.dao.DevelopersDAO;
import com.ht.dao.InvestorsDAO;
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
	private InvestorsDAO investorDAO = new InvestorsDAO();
	
	private UserDAO userDAO = new UserDAO();
	private UserRolesDAO userRolesDAO = new UserRolesDAO();
	
	private ExportUtility exportUtility = new ExportUtility();
	
	public void importUsers(){
		try{
			System.out.println("Importing Users...");
			File file = new File("C:\\Users\\NITISH\\Desktop\\ht import data\\AGAR\\@user name.xlsx");
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);
			int totalRows = sheet.getPhysicalNumberOfRows();
			System.out.println("There are: "+totalRows+" users to import into database");
			XSSFRow headerRow = sheet.getRow(0);
			headerRow.createCell(7).setCellValue("GENERATED ID");
			int i = 1;
			ArrayList<User> insertedUsers = new ArrayList<User>();
			ArrayList<UserRoles> insertedUserRoles = new ArrayList<UserRoles>();
			DataFormatter df = new DataFormatter();
			while(i < totalRows){
				XSSFRow row = sheet.getRow(i);
				User user = new User();
				user.setUsername(df.formatCellValue(row.getCell(0)).trim());
				user.setPassword(df.formatCellValue(row.getCell(1)).trim());
				user.setName(df.formatCellValue(row.getCell(2)).trim());
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
						userRole.setRole(df.formatCellValue(row.getCell(3)).trim().toLowerCase());
						userRole.setRegion(df.formatCellValue(row.getCell(4)).trim());
						userRole.setCircle(df.formatCellValue(row.getCell(5)).trim());
						userRole.setDivision(df.formatCellValue(row.getCell(6)).trim());
						if(userRolesDAO.insert(userRole)){
							insertedUserRoles.add(userRolesDAO.getByUsername(userRole.getUsername()));
							System.out.println("Inserted User Role");
						}
					}
				}else{
					row.createCell(7).setCellValue("ALREADY EXISTS");
				}
				i++;
			}
			fis.close();
			FileOutputStream fos =new FileOutputStream(new File("C:\\Users\\NITISH\\Desktop\\ht import data\\AGAR\\@user name.xlsx"));
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
			File file = new File("C:\\Users\\NITISH\\Desktop\\ht import data\\AGAR\\@DEVELOPER AGAR.xlsx");
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);
			int totalRows = sheet.getPhysicalNumberOfRows();
			System.out.println("There are: "+totalRows+" developers to import into database");
			
			XSSFRow headerRow = sheet.getRow(0);
			headerRow.createCell(11).setCellValue("GENERATED ID");
			int i = 1;
			List<Developer> developers = new ArrayList<Developer>();
			DataFormatter df = new DataFormatter();
			while(i < totalRows){
				XSSFRow row = sheet.getRow(i);
				Developer developer = new Developer();
				developer.setName(df.formatCellValue(row.getCell(0)).trim());
				developer.setCin(df.formatCellValue(row.getCell(1)).trim());
				developer.setOfficeAddress(df.formatCellValue(row.getCell(2)).trim());
				developer.setOfficeContactNo(df.formatCellValue(row.getCell(3)).trim());
				developer.setOfficeContactPerson(df.formatCellValue(row.getCell(4)).trim());
				developer.setOfficeEmail(df.formatCellValue(row.getCell(5)).trim());
				developer.setSiteAddress(df.formatCellValue(row.getCell(6)).trim());
				developer.setSiteContactNo(df.formatCellValue(row.getCell(7)).trim());
				developer.setSiteContactPerson(df.formatCellValue(row.getCell(8)).trim());
				developer.setSiteEmail(df.formatCellValue(row.getCell(9)).trim());
				developer.setUsername(df.formatCellValue(row.getCell(10)).trim());
				
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
					row.createCell(11).setCellValue("ALREADY EXISTS");
				}
				i++;
			}
			fis.close();
			FileOutputStream fos =new FileOutputStream(new File("C:\\Users\\NITISH\\Desktop\\ht import data\\AGAR\\@DEVELOPER AGAR.xlsx"));
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
			File file = new File("C:\\Users\\NITISH\\Desktop\\ht import data\\AGAR\\@PLANT AGAR.xlsx");
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("Sheet1");
			int totalRows = sheet.getPhysicalNumberOfRows();
			
			System.out.println("There are: "+totalRows+" plants to import into database");
			XSSFRow headerRow = sheet.getRow(0);
			headerRow.createCell(18).setCellValue("GENERATED ID");
			List<Plant> plants = new ArrayList<Plant>();
			DataFormatter df = new DataFormatter();
			for(int i=1;i < totalRows ; i++){
				XSSFRow row = sheet.getRow(i);
				if(row != null){
					Plant plantToInsert = new Plant();
					XSSFCell cell = null;
					if((cell = row.getCell(0)) != null){
						String code = df.formatCellValue(cell);
						if(code != null && code.length()>0){
							plantToInsert.setCode(code.trim());
						}else{
							System.out.print("Continuing since plant has no code");
							continue;
						}
					}
					
					if((cell = row.getCell(1)) != null){
						plantToInsert.setName(df.formatCellValue(cell).trim());
					}
					
					if((cell = row.getCell(2)) != null){
						plantToInsert.setAddress(df.formatCellValue(cell).trim());
					}
					
					if((cell = row.getCell(3)) != null){
						plantToInsert.setContactNo(df.formatCellValue(cell).trim());
					}
					
					if((cell = row.getCell(4)) != null){
						plantToInsert.setContactPerson(df.formatCellValue(cell).trim());
					}
					
					if((cell = row.getCell(5)) != null){
						plantToInsert.setEmail(df.formatCellValue(cell).trim());
					}
					
					if((cell = row.getCell(6)) != null){
						plantToInsert.setCommissionedDate(df.formatCellValue(cell).trim());
					}
					
					if((cell = row.getCell(7)) != null){
						plantToInsert.setType(df.formatCellValue(cell).trim());
					}
					
					if((cell = row.getCell(8)) != null){
						plantToInsert.setCircuitVoltage(df.formatCellValue(cell).trim());
					}
					
					if((cell = row.getCell(9)) != null){
						plantToInsert.setInjectingSubstation(df.formatCellValue(cell).trim());
					}
					
					if((cell = row.getCell(10)) != null){
						plantToInsert.setFeederName(df.formatCellValue(cell).trim());
					}
					
					if((cell = row.getCell(11)) != null){
						plantToInsert.setRegion(df.formatCellValue(cell).trim());
					}
					
					if((cell = row.getCell(12)) != null){
						plantToInsert.setCircle(df.formatCellValue(cell).trim());
					}
					
					if((cell = row.getCell(13)) != null){
						plantToInsert.setDivision(df.formatCellValue(cell).trim());
					}
					
					if((cell = row.getCell(14)) != null){
						String mainMeterNo = df.formatCellValue(cell);
						MeterDetails meterDetails = meterDetailsDAO.getByMeterNo(mainMeterNo.trim());
						if(meterDetails != null){
							plantToInsert.setMainMeterNo(meterDetails.getMeterNo().trim());
						}else{
							System.out.println("Continuing Since Main Meter Does not exists");
							continue;
						}
					}
					
					if((cell = row.getCell(15)) != null){
						plantToInsert.setCheckMeterNo(df.formatCellValue(cell).trim());
					}
					
					if((cell = row.getCell(16)) != null){
						plantToInsert.setStandByMeterNo(df.formatCellValue(cell).trim());
					}
					
					if((cell = row.getCell(17)) != null){
						String username = df.formatCellValue(cell).trim();
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
						row.createCell(18).setCellValue("ALREADY EXISTS");
					}
				}
			}
			fis.close();
			FileOutputStream fos =new FileOutputStream(new File("C:\\Users\\NITISH\\Desktop\\ht import data\\AGAR\\@PLANT AGAR.xlsx"));
			workbook.write(fos);
			fos.close();
			workbook.close();
			System.out.println("Importing Plants complete..");
			System.out.println("Generating Plants Excel..");
			exportUtility.exportPlants(plants);
			System.out.println("Created Imported Excel for Plants");
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void importInvestors(){
		try{
			File file = new File("C:\\Users\\NITISH\\Desktop\\ht import data\\AGAR\\@INVESTER AGAR.xlsx");
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("Sheet1");
			int totalRows = sheet.getPhysicalNumberOfRows();
			
			System.out.println("There are: "+totalRows+" investors to import into database");
			XSSFRow headerRow = sheet.getRow(0);
			headerRow.createCell(14).setCellValue("GENERATED ID");
			List<Investor> investors = new ArrayList<Investor>();
			DataFormatter df =  new DataFormatter();
			for(int i=1;i < totalRows ; i++){
				XSSFRow row = sheet.getRow(i);
				if(row != null){
					Investor investorToInsert = new Investor();
					XSSFCell cell = null;
					if((cell = row.getCell(0)) != null){
						String code = df.formatCellValue(cell);
						if(code != null && code.length()>0){
							investorToInsert.setCode(code.trim());
						}else{
							System.out.println("Continuing since investor has no code" + code);
							continue;
						}
					}
					
					if((cell = row.getCell(1)) != null){
						String name = df.formatCellValue(cell);
						if(name != null && name.length()>0){
							investorToInsert.setName(name.trim());
						}else{
							System.out.println("Continuing since investor has no Name");
							continue;
						}
					}
					
					if((cell = row.getCell(2)) != null){
						investorToInsert.setCin(df.formatCellValue(cell).trim());
					}
					
					if((cell = row.getCell(3)) != null){
						investorToInsert.setTin(df.formatCellValue(cell).trim());
					}
					
					if((cell = row.getCell(4)) != null){
						investorToInsert.setVat(df.formatCellValue(cell).trim());
					}
					
					if((cell = row.getCell(5)) != null){
						investorToInsert.setInvoiceNo(df.formatCellValue(cell).trim());
					}
					
					if((cell = row.getCell(6)) != null){
						investorToInsert.setOfficeAddress(df.formatCellValue(cell).trim());
					}
					
					if((cell = row.getCell(7)) != null){
						investorToInsert.setOfficeContactNo(df.formatCellValue(cell).trim());
					}
					
					if((cell = row.getCell(8)) != null){
						investorToInsert.setOfficeContactPerson(df.formatCellValue(cell).trim());
					}
					
					if((cell = row.getCell(9)) != null){
						investorToInsert.setOfficeEmail(df.formatCellValue(cell).trim());
					}
					
					if((cell = row.getCell(10)) != null){
						investorToInsert.setSiteAddress(df.formatCellValue(cell).trim());
					}
					
					if((cell = row.getCell(11)) != null){
						investorToInsert.setSiteContactNo(df.formatCellValue(cell).trim());
					}
					
					if((cell = row.getCell(12)) != null){
						investorToInsert.setSiteContactPerson(df.formatCellValue(cell).trim());
					}
					
					if((cell = row.getCell(13)) != null){
						investorToInsert.setSiteEmail(df.formatCellValue(cell).trim());
					}
					
					Investor existingInvestor = investorDAO.getByCode(investorToInsert.getCode());
					if(existingInvestor == null){
						Investor insertedInvestor = investorDAO.insert(investorToInsert);
						if(insertedInvestor != null){
							investors.add(insertedInvestor);
							row.createCell(14).setCellValue(insertedInvestor.getId());
						}
					}else{
						System.out.println("Skipping since Investor already exists...: "+existingInvestor.getCode());
						row.createCell(14).setCellValue("ALREADY PRESENT");
					}
				}
			}
			fis.close();
			FileOutputStream fos =new FileOutputStream(new File("C:\\Users\\NITISH\\Desktop\\ht import data\\AGAR\\@INVESTER AGAR.xlsx"));
			workbook.write(fos);
			fos.close();
			workbook.close();
			System.out.println("Importing Investors complete..");
			System.out.println("Generating Investors Excel..");
			exportUtility.exportInvestors(investors);
			System.out.println("Created Imported Excel for Investors");
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void main(String gg[]){
		System.out.println("Running import..");
	    ImportUtility iu = new ImportUtility();
	    iu.importUsers();
	    iu.importDevelopers();
	    iu.importPlants();
	    iu.importInvestors();
	}
}
