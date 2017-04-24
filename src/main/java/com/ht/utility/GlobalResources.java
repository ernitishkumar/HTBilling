package com.ht.utility;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.google.gson.Gson;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

public class GlobalResources {

	//private static Connection connection = null;
	private static DataSource datasource = null;

	private static Gson gson=new Gson();

	private static final String[] tensNames = {""," Ten"," Twenty"," Thirty"," Forty"," Fifty"," Sixty"," Seventy"," Eighty"," Ninety" };
	private static final String[] numNames = {""," One"," Two"," Three"," Four"," Five"," Six"," Seven"," Eight"," Nine"," Ten"," Eleven"," Twelve"," Thirteen"," Fourteen"," Fifteen"," Sixteen"," Seventeen"," Eighteen"," Nineteen"};

	public static DataSource getDatasource()
	{
		if(datasource==null){
			PoolProperties poolProperties = new PoolProperties();
			poolProperties.setDriverClassName("com.mysql.jdbc.Driver");
			
			//Production environment database connection details.Uncomment below code before deploying to production
			/*poolProperties.setUrl("jdbc:mysql://localhost:3306/ht");
			poolProperties.setUsername("ht");
			poolProperties.setPassword("htbilling@452015");*/
			
			//Testing environment database connection details
			poolProperties.setUrl("jdbc:mysql://localhost:3306/ht");
			poolProperties.setUsername("ht");
		    poolProperties.setPassword("ht");
			
		    poolProperties.setJmxEnabled(true);
			poolProperties.setTestWhileIdle(false);
			poolProperties.setTestOnBorrow(true);
			poolProperties.setValidationQuery("SELECT 1");
			poolProperties.setTestOnReturn(false);
			poolProperties.setValidationInterval(3600);   // Change after testing. Do not change if this interval works
			poolProperties.setTimeBetweenEvictionRunsMillis(30000);
			poolProperties.setMaxActive(100);
			poolProperties.setInitialSize(10);
			poolProperties.setMaxWait(10000);
			poolProperties.setRemoveAbandonedTimeout(60);
			poolProperties.setMinEvictableIdleTimeMillis(30000);
			poolProperties.setMinIdle(10);
			poolProperties.setLogAbandoned(true);
			poolProperties.setRemoveAbandoned(true);
			poolProperties.setJdbcInterceptors(
					"org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"+
					"org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
			datasource = new DataSource();
			datasource.setPoolProperties(poolProperties);
		}
		return datasource;
	}

	public static Gson getGson(){
		return gson;
	}

	private static String convertLessThanOneThousand(int number) {
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


	public static String convert(BigDecimal number) {
		long l = (long)number.longValue();
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
	
	public final static String generateBillMonth(String date){
		String billMonth = "";
		String [] monthAndYear = date.split("-");
		String month = monthAndYear[0];
		switch(month){
		case "JAN":
			billMonth = "01";
			break;
		case "FEB":
			billMonth = "02";
			break;
		case "MAR":
			billMonth = "03";
			break;
		case "APR":
			billMonth = "04";
			break;
		case "MAY":
			billMonth = "05";
			break;
		case "JUN":
			billMonth = "06";
			break;
		case "JUL":
			billMonth = "07";
			break;
		case "AUG":
			billMonth = "08";
			break;
		case "SEP":
			billMonth = "09";
			break;
		case "OCT":
			billMonth = "10";
			break;
		case "NOV":
			billMonth = "11";
			break;
		case "DEC":
			billMonth = "12";
			break;
		default:
			break;
		}
		return billMonth+"-"+monthAndYear[1];
	}
	public final static String generatePreviousBillMonth(String date){
		String billMonth = "";
		String [] monthAndYear = date.split("-");
		String month = monthAndYear[0];
		switch(month){
		case "JAN":
			billMonth = "12";
			monthAndYear[1]=String.valueOf(Integer.parseInt(monthAndYear[1])-1);
			break;
		case "FEB":
			billMonth = "01";
			break;
		case "MAR":
			billMonth = "02";
			break;
		case "APR":
			billMonth = "03";
			break;
		case "MAY":
			billMonth = "04";
			break;
		case "JUN":
			billMonth = "05";
			break;
		case "JUL":
			billMonth = "06";
			break;
		case "AUG":
			billMonth = "07";
			break;
		case "SEP":
			billMonth = "08";
			break;
		case "OCT":
			billMonth = "09";
			break;
		case "NOV":
			billMonth = "10";
			break;
		case "DEC":
			billMonth = "11";
			break;
		default:
			break;
		}
		return billMonth+"-"+monthAndYear[1];
	}
	
	
	/*
	 * RELATIVE DTR PMR FILE LOCATION FOR STORAGE OF IMAGES
	 */
	public final static String RELATIVE_AMR_FILE_LOCATION = "ht//amr//";
	
	/*
	 * DRIVE LOCATION FOR DTR PMR IMAGES
	 */
	public final static String AMR_DRIVE_LOCATION = "G://";
}
