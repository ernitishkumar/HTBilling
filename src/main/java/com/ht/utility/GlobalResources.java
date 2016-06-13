package com.ht.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DecimalFormat;

import com.google.gson.Gson;
import com.ht.dao.MeterDetailsDAO;
import com.ht.dao.MeterReadingsDAO;
import com.ht.dao.UserDAO;

public class GlobalResources {

	private static Connection connection = null;
	private static Gson gson=new Gson();
	private static UserDAO userDAO=new UserDAO();
	private static MeterDetailsDAO meterDetailsDAO=new MeterDetailsDAO();
    private static MeterReadingsDAO meterReadingsDAO=new MeterReadingsDAO();
    private static final String[] tensNames = {""," Ten"," Twenty"," Thirty"," Forty"," Fifty"," Sixty"," Seventy"," Eighty"," Ninety" };
	private static final String[] numNames = {""," One"," Two"," Three"," Four"," Five"," Six"," Seven"," Eight"," Nine"," Ten"," Eleven"," Twelve"," Thirteen"," Fourteen"," Fifteen"," Sixteen"," Seventeen"," Eighteen"," Nineteen"};
	
	public static Connection getConnection()
	{
		try {
			if(connection==null){
				Class.forName("com.mysql.jdbc.Driver");
				connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/ht_test","ht_test","ht");	
			}
		} catch (SQLException exception) {
			System.out.println("Not able to connect to the Database "+exception.getMessage());
			exception.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found "+e.getMessage());
			e.printStackTrace();
		}
		return connection;
	}
	
	public static Gson getGson(){
		return gson;
	}
	
	public static UserDAO getUserDAO(){
		return userDAO;
	}
	
	public static MeterDetailsDAO getMeterDetailsDAO() {
		return meterDetailsDAO;
	}

    public static MeterReadingsDAO getMeterReadingsDAO() {
		return meterReadingsDAO;
	}
    
	private String convertLessThanOneThousand(int number) {
	    String soFar;

	    if (number % 100 < 20){
	      soFar = numNames[number % 100];
	      number /= 100;
	    }
	    else {
	      soFar = numNames[number % 10];
	      number /= 10;

	      soFar = tensNames[number % 10] + soFar;
	      number /= 10;
	    }
	    if (number == 0) return soFar;
	    return numNames[number] + " hundred &" + soFar;
	  }

	
	 public String convert(float number) {
		 long l = (long)number;
		    if (l == 0) { return "Zero"; }
		    String snumber = Long.toString(l);

		    // pad with "0"
		    String mask = "000000000";
		    DecimalFormat df = new DecimalFormat(mask);
		    snumber = df.format(number);
		    int carore = Integer.parseInt(snumber.substring(0,2));
		    int lakh  = Integer.parseInt(snumber.substring(2,4));
		    int tenThousand = Integer.parseInt(snumber.substring(4,6));
		    int thousands = Integer.parseInt(snumber.substring(6,9));
		    String carores;
		    switch (carore) {
		    case 0:
		    	carores = "";
		      break;
		    case 1 :
		    	carores = convertLessThanOneThousand(carore)
		      + " Carore ";
		      break;
		    default :
		    	carores = convertLessThanOneThousand(carore)
		      + " Carore ";
		    }
		    String result =  carores;

		    String lakhs;
		    switch (lakh) {
		    case 0:
		    	lakhs = "";
		      break;
		    case 1 :
		    	lakhs = convertLessThanOneThousand(lakh)
		         + " Lakhs ";
		      break;
		    default :
		    	lakhs = convertLessThanOneThousand(lakh)
		         + " Lakhs ";
		    }
		    result =  result + lakhs;

		    String tenThousands;
		    switch (tenThousand) {
		    case 0:
		    	tenThousands = "";
		      break;
		    case 1 :
		    	tenThousands = "One Thousand ";
		      break;
		    default :
		    	tenThousands = convertLessThanOneThousand(tenThousand)
		         + " Thousand ";
		    }
		    result =  result + tenThousands;

		    String thousand;
		    thousand = convertLessThanOneThousand(thousands);
		    result =  result + thousand + " Only";

		    // remove extra spaces!
		    return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
	 }
}
