/**
 * 
 */
package com.ht.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.ht.beans.AMRReading;
import com.ht.utility.GlobalResources;

/**
 * @author lenovo
 *
 */
public abstract class AMRReadingDAO {

	public static AMRReading insert(AMRReading reading) {
		int lastInsertedId = -1;
		AMRReading insertedReading = null;
		if (reading != null) {
			try(
					Connection connection = GlobalResources.getDatasource().getConnection();
					PreparedStatement ps = connection.prepareStatement(
							"insert into amr_readings(meter_no, reading_date, active_reading, active_tod1, active_tod2, active_tod3, reactive_q1, reactive_q2, reactive_q3, reactive_q4, uploaded_on, status, misc1, misc2, misc3) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
							Statement.RETURN_GENERATED_KEYS);
					) {
				ps.setString(1, reading.getMeterNo());
				ps.setString(2, reading.getReadingDate());
				ps.setBigDecimal(3, reading.getActiveEnergy());
				ps.setBigDecimal(4, reading.getActiveTodOne());
				ps.setBigDecimal(5, reading.getActiveTodTwo());
				ps.setBigDecimal(6, reading.getActiveTodThree());
				ps.setBigDecimal(7, reading.getReactiveQuadrantOne());
				ps.setBigDecimal(8, reading.getReactiveQuadrantTwo());
				ps.setBigDecimal(9, reading.getReactiveQuadrantThree());
				ps.setBigDecimal(10, reading.getReactiveQuadrantFour());
				ps.setTimestamp(11, new Timestamp(reading.getUploadedOn().getTime()));
				ps.setInt(12, reading.getStatus());
				ps.setString(13, reading.getMisc1());
				ps.setString(14, reading.getMisc2());
				ps.setString(15, reading.getMisc3());
				ps.executeUpdate();
				ResultSet keys = ps.getGeneratedKeys();
				keys.next();
				lastInsertedId = keys.getInt(1);
				insertedReading = getById(lastInsertedId);
			} catch (SQLException e) {
				System.out.println(
						"Exception in class : AMRReadingsDAO : method : [insert(AMRReading)] " + e.getMessage());
			} catch (Exception exception) {
				System.out.println("Exception in class : AMRReadingsDAO : method : [insert(AMRReading)] "
						+ exception.getMessage());
			}
		}
		return insertedReading;
	}
	
	public static AMRReading update(AMRReading reading) {
		if (reading != null) {
			try(
					Connection connection = GlobalResources.getDatasource().getConnection();
					PreparedStatement ps = connection.prepareStatement(
							"update amr_readings set meter_no=?, reading_date=?, active_reading=?, active_tod1=?, active_tod2=?, active_tod3=?, reactive_q1=?, reactive_q2=?, reactive_q3=?, reactive_q4=?, uploaded_on=?, status =?, misc1=?, misc2=?, misc3=? where id=?");
					) {
				ps.setString(1, reading.getMeterNo());
				ps.setString(2, reading.getReadingDate());
				ps.setBigDecimal(3, reading.getActiveEnergy());
				ps.setBigDecimal(4, reading.getActiveTodOne());
				ps.setBigDecimal(5, reading.getActiveTodTwo());
				ps.setBigDecimal(6, reading.getActiveTodThree());
				ps.setBigDecimal(7, reading.getReactiveQuadrantOne());
				ps.setBigDecimal(8, reading.getReactiveQuadrantTwo());
				ps.setBigDecimal(9, reading.getReactiveQuadrantThree());
				ps.setBigDecimal(10, reading.getReactiveQuadrantFour());
				ps.setTimestamp(11, new Timestamp(reading.getUploadedOn().getTime()));
				ps.setInt(12, reading.getStatus());
				ps.setString(13, reading.getMisc1());
				ps.setString(14, reading.getMisc2());
				ps.setString(15, reading.getMisc3());
				ps.setInt(16, reading.getId());
				ps.executeUpdate();
			} catch (SQLException e) {
				reading =null;
				System.out.println(
						"Exception in class : AMRReadingsDAO : method : [insert(AMRReading)] " + e.getMessage());
			}
		}
		return reading;
	}
	
	public static ArrayList<AMRReading> getAll() {
		ArrayList<AMRReading> readings = null;
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection.prepareStatement("select * from amr_readings");
				) {
			ResultSet resultSet = ps.executeQuery();
			readings = resultSetParser(resultSet);
		} catch (SQLException e) {
			System.out.println("Exception in class : AMRReadingDAO : method : [getAll()] " + e.getMessage());
		}
		return readings;
	}
	
	public static ArrayList<AMRReading> getAll(int status) {
		ArrayList<AMRReading> readings = null;
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection.prepareStatement("select * from amr_readings where status=?");
				) {
			ps.setInt(1, status);
			ResultSet resultSet = ps.executeQuery();
			readings = resultSetParser(resultSet);
		} catch (SQLException e) {
			System.out.println("Exception in class : AMRReadingDAO : method : [getAll(int)] " + e.getMessage());
		}
		return readings;
	}
	
	public static AMRReading getById(int id) {
		ArrayList<AMRReading> readings = null;
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection.prepareStatement("select * from amr_readings where id=?");
				) {
			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery();
			readings = resultSetParser(resultSet);
		} catch (SQLException e) {
			System.out.println("Exception in class : AMRReadingDAO : method : [getById(id)] " + e.getMessage());
		}
		return readings.size() > 0 ? readings.get(0) : null;
	}
	
	public static ArrayList<AMRReading> getByMeterNo(String meterNo) {
		ArrayList<AMRReading> readings = null;
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection.prepareStatement("select * from amr_readings where meter_no=?");
				) {
			ps.setString(1, meterNo);
			ResultSet resultSet = ps.executeQuery();
			readings = resultSetParser(resultSet);
		} catch (SQLException e) {
			System.out.println("Exception in class : AMRReadingDAO : method : [getByMeterNo(meterNo)] " + e.getMessage());
		}
		return readings;
	}
	
	public static AMRReading getByMeterNoAndReadingDate(String meterNo, String readingDate) {
		ArrayList<AMRReading> readings = null;
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection.prepareStatement("select * from amr_readings where meter_no=? and reading_date = ?");
				) {
			ps.setString(1, meterNo);
			ps.setString(2, readingDate);
			ResultSet resultSet = ps.executeQuery();
			readings = resultSetParser(resultSet);
		} catch (SQLException e) {
			System.out.println("Exception in class : AMRReadingDAO : method : [getByMeterNoAndReadingDate(String,String)] " + e.getMessage());
		}
		return readings.size() > 0 ? readings.get(0) : null;
	}
	
	private static ArrayList<AMRReading> resultSetParser(ResultSet resultSet) {
		ArrayList<AMRReading> readings = new ArrayList<AMRReading>();
		try {
			
			while (resultSet.next()) {
				AMRReading amrReading = new AMRReading();
				amrReading.setId(resultSet.getInt(1));
				amrReading.setMeterNo(resultSet.getString(2));
				amrReading.setReadingDate(resultSet.getString(3));
				amrReading.setActiveEnergy(resultSet.getBigDecimal(4));
				amrReading.setActiveTodOne(resultSet.getBigDecimal(5));
				amrReading.setActiveTodTwo(resultSet.getBigDecimal(6));
				amrReading.setActiveTodThree(resultSet.getBigDecimal(7));
				amrReading.setReactiveQuadrantOne(resultSet.getBigDecimal(8));
				amrReading.setReactiveQuadrantTwo(resultSet.getBigDecimal(9));
				amrReading.setReactiveQuadrantThree(resultSet.getBigDecimal(10));
				amrReading.setReactiveQuadrantFour(resultSet.getBigDecimal(11));
				amrReading.setUploadedOn(resultSet.getDate(12));
				amrReading.setStatus(resultSet.getInt(13));
				amrReading.setMisc1(resultSet.getString(14));
				amrReading.setMisc2(resultSet.getString(15));
				amrReading.setMisc3(resultSet.getString(16));
				readings.add(amrReading);
			}
		} catch (Exception e) {
			System.out.println("Exception in class : AMRReadingsDAO : method : resultSetParser(resultSet,arrayList) "
					+ e.getMessage());
			e.printStackTrace();
		}
		return readings;
	}

	public static AMRReading delete(int id) {
		AMRReading readingToDelete = getById(id);
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection.prepareStatement("delete from amr_readings where id=?");
				) {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			readingToDelete = null;
			System.out.println("Exception in class : AMRReadingDAO : method : [delete(meterNo)] " + e.getMessage());
		}
		return readingToDelete;
	}
}