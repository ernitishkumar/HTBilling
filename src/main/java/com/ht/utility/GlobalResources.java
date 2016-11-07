package com.ht.utility;

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
			poolProperties.setUrl("jdbc:mysql://localhost:3306/ht_test");
			poolProperties.setUsername("ht_test");
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


	public static String convert(float number) {
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
