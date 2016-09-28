/**
 * 
 */
package com.ht.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ht.beans.Developer;
import com.ht.beans.Investor;
import com.ht.beans.Machine;
import com.ht.beans.MeterDetails;
import com.ht.beans.Plant;
import com.ht.beans.User;
import com.ht.beans.UserRoles;
import com.ht.dao.DevelopersDAO;
import com.ht.dao.InvestorsDAO;
import com.ht.dao.MachinesDAO;
import com.ht.dao.MeterDetailsDAO;
import com.ht.dao.PlantsDAO;
import com.ht.dao.UserDAO;
import com.ht.dao.UserRolesDAO;

/**
 * @author NITISH
 *
 */
@Path("/import")
public class ImportUtility {

	private MeterDetailsDAO meterDetailsDAO = new MeterDetailsDAO();
	private PlantsDAO plantDAO = new PlantsDAO();
	private DevelopersDAO developerDAO = new DevelopersDAO();
	private InvestorsDAO investorDAO = new InvestorsDAO();
	private MachinesDAO machineDAO = new MachinesDAO();
	private UserDAO userDAO = new UserDAO();
	private UserRolesDAO userRolesDAO = new UserRolesDAO();

	private ExportUtility exportUtility = new ExportUtility();

	public void importUsers(){
		try{
			System.out.println("Importing Users...");
			File file = new File("C:\\Users\\NITISH\\Desktop\\ht import data\\circle.xlsx");
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("user");
			int totalRows = sheet.getPhysicalNumberOfRows();
			System.out.println("There are: "+totalRows+" users to import into database");
			XSSFRow headerRow = sheet.getRow(0);
			headerRow.createCell(6).setCellValue("GENERATED ID");
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
						row.createCell(6).setCellValue(insertedUser.getId());
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
					row.createCell(6).setCellValue("ALREADY EXISTS");
				}
				i++;
			}
			fis.close();
			FileOutputStream fos =new FileOutputStream(new File("C:\\Users\\NITISH\\Desktop\\ht import data\\circle.xlsx"));
			workbook.write(fos);
			fos.close();
			workbook.close();
			System.out.println("Users Importing complete..Imported: "+insertedUsers.size()+" users.");
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
			File file = new File("C:\\Users\\NITISH\\Desktop\\ht import data\\circle.xlsx");
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("developer");
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
				String username = df.formatCellValue(row.getCell(10));
				if(username != null && username.length() > 0){
					developer.setUsername(username.trim());
				}else{
					row.createCell(11).setCellValue("NO USERNAME PRESENT");
					System.out.println("USERNAME is not present");
					continue;
				}

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
			FileOutputStream fos =new FileOutputStream(new File("C:\\Users\\NITISH\\Desktop\\ht import data\\circle.xlsx"));
			workbook.write(fos);
			fos.close();
			workbook.close();
			System.out.println("Importing Developers complete..Imported: "+developers.size()+" developers.");
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
			File file = new File("C:\\Users\\NITISH\\Desktop\\ht import data\\circle.xlsx");
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("plant");
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
							row.createCell(18).setCellValue("NO PLANT CODE");
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
							row.createCell(18).setCellValue("MAIN METER DOES NOT EXISTS IN DATABASE");
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
							row.createCell(18).setCellValue("NO DEVELOPER FOUND FOR GIVEN USERNAME");
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
			FileOutputStream fos =new FileOutputStream(new File("C:\\Users\\NITISH\\Desktop\\ht import data\\circle.xlsx"));
			workbook.write(fos);
			fos.close();
			workbook.close();
			System.out.println("Importing Plants complete..Imported: "+plants.size()+" plants.");
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
			File file = new File("C:\\Users\\NITISH\\Desktop\\ht import data\\circle.xlsx");
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("investor");
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
							row.createCell(14).setCellValue("NO INVESTOR CODE");
							System.out.println("Continuing since investor has no code" + code);
							continue;
						}
					}

					if((cell = row.getCell(1)) != null){
						String name = df.formatCellValue(cell);
						if(name != null && name.length()>0){
							investorToInsert.setName(name.trim());
						}else{
							row.createCell(14).setCellValue("NO INVESTOR NAME");
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
			FileOutputStream fos =new FileOutputStream(new File("C:\\Users\\NITISH\\Desktop\\ht import data\\circle.xlsx"));
			workbook.write(fos);
			fos.close();
			workbook.close();
			System.out.println("Importing Investors complete..Imported: "+investors.size()+" investors.");
			System.out.println("Generating Investors Excel..");
			exportUtility.exportInvestors(investors);
			System.out.println("Created Imported Excel for Investors");
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public void importMachines(){
		try{
			File file = new File("C:\\Users\\NITISH\\Desktop\\ht import data\\circle.xlsx");
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("machine");
			int totalRows = sheet.getPhysicalNumberOfRows();
			System.out.println("There are: "+totalRows+" machines to import into database");

			XSSFRow headerRow = sheet.getRow(0);
			headerRow.createCell(11).setCellValue("GENERATED ID");
			List<Machine> machines = new ArrayList<Machine>();
			DataFormatter df =  new DataFormatter();
			for(int i=1;i < totalRows ; i++){
				XSSFRow row = sheet.getRow(i);
				if(row != null){
					Machine machineToInsert = new Machine();
					XSSFCell cell = null;
					if((cell = row.getCell(0)) != null){
						String code = df.formatCellValue(cell);
						if(code != null && code.length()>0){
							machineToInsert.setCode(code.trim());
						}
					}

					if((cell = row.getCell(1)) != null){
						String capacity = df.formatCellValue(cell);
						if(capacity != null && capacity.length()>0){
							machineToInsert.setCapacity(capacity.trim());
						}
					}

					if((cell = row.getCell(2)) != null){
						machineToInsert.setCommissionedDate(df.formatCellValue(cell).trim());
					}

					if((cell = row.getCell(3)) != null){
						machineToInsert.setActiveRate(Float.parseFloat(df.formatCellValue(cell).trim()));
					}

					if((cell = row.getCell(4)) != null){
						machineToInsert.setReactiveRate(Float.parseFloat(df.formatCellValue(cell).trim()));
					}

					if((cell = row.getCell(5)) != null){
						machineToInsert.setPpaLetterNo(df.formatCellValue(cell).trim());
					}

					if((cell = row.getCell(6)) != null){
						machineToInsert.setPpaDate(df.formatCellValue(cell).trim());
					}

					if((cell = row.getCell(7)) != null){
						String deverloperUsername = df.formatCellValue(cell).trim();
						if(deverloperUsername != null && deverloperUsername.length() > 0){
							Developer developer = developerDAO.getByUsername(deverloperUsername);
							if(developer != null){
								machineToInsert.setDeveloperId(developer.getId());
							}else{
								row.createCell(11).setCellValue("NO DEVELOPER FOUND");
								System.out.println("Continuing since no developer found for : "+deverloperUsername);
								continue;
							}
						}else{
							row.createCell(11).setCellValue("NO DEVELOPER CODE");
							System.out.println("Continuing since machine has no developer");
							continue;
						}
					}

					if((cell = row.getCell(8)) != null){
						String plantCode = df.formatCellValue(cell).trim();
						if(plantCode != null && plantCode.length() > 0){
							Plant plant = plantDAO.getByCode(plantCode);
							if(plant != null){
								machineToInsert.setPlantId(plant.getId());
							}else{
								row.createCell(11).setCellValue("NO PLANT FOUND");
								System.out.println("Continuing since no plant found for plant code: "+plantCode);
								continue;
							}
						}else{
							row.createCell(11).setCellValue("NO PLANT CODE");
							System.out.println("Continuing since machine has no plant code");
							continue;
						}
					}

					if((cell = row.getCell(9)) != null){
						String investorCode = df.formatCellValue(cell);
						if(investorCode != null && investorCode.length() > 0){
							Investor investor = investorDAO.getByCode(investorCode);
							if(investor != null){
								machineToInsert.setInvestorId(investor.getId());
							}else{
								row.createCell(11).setCellValue("NO INVESTOR FOUND");
								System.out.println("Continuing since no investor found for investor code: "+investorCode);
								continue;
							}
						}else{
							System.out.println("Continuing since machine as no machine code");
							row.createCell(11).setCellValue("NO INVESTOR CODE");
							continue;
						}
					}

					if((cell = row.getCell(10)) != null){
						machineToInsert.setParticulars(df.formatCellValue(cell).trim());
					}

					Machine insertedMachine = machineDAO.insert(machineToInsert);
					if(insertedMachine != null){
							machines.add(insertedMachine);
							row.createCell(11).setCellValue(insertedMachine.getId());
					}
				}
			}
			fis.close();
			//CHANGE PATH WHILE PUSHING IN SERVER
			FileOutputStream fos =new FileOutputStream(new File("C:\\Users\\NITISH\\Desktop\\ht import data\\circle.xlsx"));
			workbook.write(fos);
			fos.close();
			workbook.close();
			System.out.println("Importing Machines complete.. Imported: "+machines.size()+" machines.");
			System.out.println("Generating Machines Excel..");
			exportUtility.exportMachines(machines);
			System.out.println("Created Imported Excel for Machines");
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void main(String gg[]){
		System.out.println("Running import..");
		ImportUtility iu = new ImportUtility();
		//iu.importUsers();
		//iu.importDevelopers();
		//iu.importPlants();
		//importInvestors();
		//iu.importMachines();
	}

	
	public boolean runImport(){
		boolean done = false;
		try{
			ImportUtility iu = new ImportUtility();
			System.out.println("Importing Users..");
			iu.importUsers();
			System.out.println("Importing Developers..");
			iu.importDevelopers();
			System.out.println("Importing Plants..");
			iu.importPlants();
			System.out.println("Importing Investors..");
			iu.importInvestors();
			System.out.println("Importing Machines..");
			iu.importMachines();
			done = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return done;
	}

	@GET
	public Response importData(){
		System.out.println("Import started");
		if(runImport()){
			return Response.status(Status.OK).build();
		}else{
			return Response.status(Status.EXPECTATION_FAILED).build();
		}
	}
}
