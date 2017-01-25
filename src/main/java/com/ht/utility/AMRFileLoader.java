/**
 * 
 */
package com.ht.utility;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author NITISH
 *
 */
public abstract class AMRFileLoader {
	
	public static String saveAMRFile(InputStream inputStream,String fileName){
		String savedFilePath = null;
		System.out.println("Saving uploaded AMR File with name: "+fileName+" at: "+GlobalResources.AMR_DRIVE_LOCATION
		+ GlobalResources.RELATIVE_AMR_FILE_LOCATION);
		String pathForSavingImage = null;
		if(inputStream != null && fileName != null){
			try{
				// Adding DTR PMR Drive location with DTR PMR Directory Location
				// to make complete path.
			    pathForSavingImage = GlobalResources.AMR_DRIVE_LOCATION
						+ GlobalResources.RELATIVE_AMR_FILE_LOCATION
						/*+ meterNo+"//"
						+ billMonth+"//"*/
						+getCurrentDateTime()+"_"
						+ fileName;
				System.out.println("Saving file at \n "+pathForSavingImage);
				Path file = Paths.get(pathForSavingImage); 
				if(!Files.exists(file)){
					System.out.println("Directory does not exists creating new directory");
					Files.createDirectories(file);
				}else{
					System.out.println("Direcotry exists !!!");
				}
				System.out.println("Using Files.copy to save uploaded PMR Image");
				Files.copy(inputStream, file, StandardCopyOption.REPLACE_EXISTING);
			}catch(Exception exception){
				System.out.println("Exception while saving uploaded uploaded PMR Image: "+exception);
				exception.printStackTrace();
				pathForSavingImage = null;
			}
			System.out.println("Saved uploaded PMR Image successfully at " + pathForSavingImage);
			savedFilePath = pathForSavingImage;
		}else{
			System.out.println("InputStream or file name is null hence skipping");
		}
		return savedFilePath;
	}
	
	public static String getCurrentDateTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
		String date =  sdf.format(new Date());
		return date;
	}
}
