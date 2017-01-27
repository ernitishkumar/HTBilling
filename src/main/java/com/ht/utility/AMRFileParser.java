/**
 * 
 */
package com.ht.utility;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.ht.beans.AMRReading;

/**
 * @author lenovo
 *
 */
public abstract class AMRFileParser {

	public static AMRReading parseAMRFile(String filePath){
		System.out.println("Parsing AMR file located at: "+filePath);
		AMRReading amrReading = null;
		if(filePath != null){
			Path fileToParse = Paths.get(filePath);
			List<String> records = null;
			
			try{
				try{
					records = Files.readAllLines(fileToParse, StandardCharsets.UTF_8);
				}catch(MalformedInputException mie){
					System.out.println("MalformedInputExcpetion while parsing saved amr file,using utf-16");
					records = Files.readAllLines(fileToParse, StandardCharsets.UTF_16);
				}
				String firstRow = records.get(0);
				if(firstRow != null){
					amrReading = new AMRReading();
					String readingDateAndTime = "";
					String[] meterData = firstRow.trim().split("#");
					if(meterData != null && meterData.length == 16){
						System.out.println("Meter No is: "+meterData[0]);
						amrReading.setMeterNo(meterData[0]);
						readingDateAndTime = meterData[1];
						String[] date = readingDateAndTime.trim().split("\\s+");
						System.out.println(date[0]);
						amrReading.setReadingDate(date[0]);
						amrReading.setActiveEnergy(new BigDecimal(meterData[3]));
						amrReading.setActiveTodOne(new BigDecimal(meterData[13]));
						amrReading.setActiveTodTwo(new BigDecimal(meterData[14]));
						amrReading.setActiveTodThree(new BigDecimal(meterData[15]));
					}else{
						System.out.println(firstRow);
						System.out.println("AMR file data does not have 16 values. Aborting parsing !!!");
					}
				}else{
					System.out.println(firstRow);
					System.out.println("AMR file data is null.Aborting parsing !!!");
				}
			}catch(IOException ioException){
				System.out.println("IOException in class : AMRFileParser : method : parseAMRFile(String) : "+ioException);
				ioException.printStackTrace();
			}catch(Exception exception){
				System.out.println("Exception in class : AMRFileParser : method : parseAMRFile(String) : "+exception);
				exception.printStackTrace();
			}
		}else{
			System.out.println("FilePath is null in parseAMRFile");
		}
		return amrReading;
	}
}
