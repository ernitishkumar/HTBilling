/**
 * 
 */
package com.ht.utility;

import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author lenovo
 *
 */
public abstract class AMRFileParser {

	public static void parseAMRFile(String filePath){
		System.out.println("Parsing AMR file located at: "+filePath);
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
					String[] meterData = firstRow.trim().split("#");
					if(meterData != null && meterData.length == 16){
						System.out.println("Meter No is: "+meterData[0]);
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
	}
}
