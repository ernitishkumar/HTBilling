package com.ht.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.ht.beans.MeterDetails;
import com.ht.beans.MeterReading;
import com.ht.beans.UserRoles;
import com.ht.utility.GlobalResources;

/*
 * DAO Class having various methods to operate on database table 'meter_readings'
 */
public class MeterReadingsDAO {

	/*
	 * Method isReadingAlreadyAdded to check whether provided readings are
	 * already added for a particular meterNo and readingDate
	 * 
	 * @param MeterReading
	 * 
	 * @return boolean
	 */
	public boolean isReadingAlreadyAdded(MeterReading reading) {
		MeterReading latestInsertedReading = getLatestInsertedByMeterNo(reading.getMeterno());
		boolean isAlreadyAdded = true;
		if (latestInsertedReading != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			Calendar c = Calendar.getInstance();
			try {
				c.setTime(formatter.parse(latestInsertedReading.getReadingDate()));
				int lastReadingMonth = c.get(Calendar.MONTH) + 1;
				c.setTime(formatter.parse(reading.getReadingDate()));
				int currentReadingMonth = c.get(Calendar.MONTH) + 1;
				int result = currentReadingMonth - lastReadingMonth;
				/*
				 * if(result == 1 || result == -11){ isAlreadyAdded = false; }
				 */
				if (result != 0 || latestInsertedReading.getDiscardedFlag() == 1) {
					isAlreadyAdded = false;
				}
			} catch (ParseException parseException) {
				System.out.println(
						"Exception in class : MeterReadingsDAO : method : [isReadingAlreadyAdded(MeterReading)] "
								+ parseException.getMessage());
			}
		} else {
			isAlreadyAdded = false;
		}
		return isAlreadyAdded;
	}

	public MeterReading insert(MeterReading reading) {
		int lastInsertedId = -1;
		MeterReading insertedReading = null;
		if (reading != null) {
			try(
					Connection connection = GlobalResources.getDatasource().getConnection();
					PreparedStatement ps = connection.prepareStatement(
							"insert into meter_readings(meter_no, mf, reading_date, active_reading, active_tod1, active_tod2, active_tod3, reactive_q1, reactive_q2, reactive_q3, reactive_q4, ht_cell_validation, circle_cell_validation, developer_validation, discarded_flag, discarded_by, discarded_on, sr_fr_flag, adjustment) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
							Statement.RETURN_GENERATED_KEYS);
					) {
				ps.setString(1, reading.getMeterno());
				ps.setFloat(2, reading.getMf());
				ps.setString(3, reading.getReadingDate());
				ps.setFloat(4, reading.getActiveEnergy());
				ps.setFloat(5, reading.getActiveTodOne());
				ps.setFloat(6, reading.getActiveTodTwo());
				ps.setFloat(7, reading.getActiveTodThree());
				ps.setFloat(8, reading.getReactiveQuadrantOne());
				ps.setFloat(9, reading.getReactiveQuadrantTwo());
				ps.setFloat(10, reading.getReactiveQuadrantThree());
				ps.setFloat(11, reading.getReactiveQuadrantFour());
				ps.setInt(12, 0);
				ps.setInt(13, 0);
				ps.setInt(14, 0);
				ps.setInt(15, 0);
				ps.setString(16, null);
				ps.setString(17, null);
				ps.setInt(18, reading.getSrfrFlag());
				ps.setFloat(19, reading.getAdjustment());
				ps.executeUpdate();
				ResultSet keys = ps.getGeneratedKeys();
				keys.next();
				lastInsertedId = keys.getInt(1);
				insertedReading = getById(lastInsertedId);
			} catch (SQLException e) {
				System.out.println(
						"Exception in class : MeterReadingsDAO : method : [insert(Readings)] " + e.getMessage());
			} catch (Exception exception) {
				System.out.println("Exception in class : MeterReadingsDAO : method : [insert(Readings)] "
						+ exception.getMessage());
			}
		}
		return insertedReading;
	}

	public MeterReading update(MeterReading reading) {
		if (reading != null) {
			try(
					Connection connection = GlobalResources.getDatasource().getConnection();
					PreparedStatement ps = connection.prepareStatement(
							"update meter_readings set meter_no=?, mf=?, reading_date=?, active_reading=?, active_tod1=?, active_tod2=?, active_tod3=?, reactive_q1=?, reactive_q2=?, reactive_q3=?, reactive_q4=?, ht_cell_validation=?, circle_cell_validation=?, developer_validation=?, discarded_flag=?, discarded_by=?, discarded_on=?, sr_fr_flag=?, adjustment=? where id=?");
					) {
				ps.setString(1, reading.getMeterno());
				ps.setFloat(2, reading.getMf());
				ps.setString(3, reading.getReadingDate());
				ps.setFloat(4, reading.getActiveEnergy());
				ps.setFloat(5, reading.getActiveTodOne());
				ps.setFloat(6, reading.getActiveTodTwo());
				ps.setFloat(7, reading.getActiveTodThree());
				ps.setFloat(8, reading.getReactiveQuadrantOne());
				ps.setFloat(9, reading.getReactiveQuadrantTwo());
				ps.setFloat(10, reading.getReactiveQuadrantThree());
				ps.setFloat(11, reading.getReactiveQuadrantFour());
				ps.setInt(12, reading.getHtCellValidation());
				ps.setInt(13, reading.getCircleCellValidation());
				ps.setInt(14, reading.getDeveloperValidation());
				ps.setInt(15, reading.getDiscardedFlag());
				ps.setString(16, reading.getDiscardedBy());
				ps.setString(17, reading.getDiscardedOn());
				ps.setInt(18, reading.getSrfrFlag());
				ps.setFloat(19, reading.getAdjustment());
				ps.setInt(20, reading.getId());
				ps.executeUpdate();
			} catch (SQLException e) {
				System.out.println(
						"Exception in class : MeterReadingsDAO : method : [insert(Readings)] " + e.getMessage());
			}
		}
		return reading;
	}

	public boolean delete(int id) {
		boolean deleted = false;
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection.prepareStatement("delete from meter_readings where id=?");
				) {
			ps.setInt(1, id);
			ps.executeUpdate();
			deleted = true;
		} catch (SQLException e) {
			deleted = false;
			System.out.println("Exception in class : MeterReadingsDAO : method : [delete(int)] " + e.getMessage());
		}
		return deleted;
	}

	public ArrayList<MeterReading> getAll() {
		ArrayList<MeterReading> readings = new ArrayList<MeterReading>();
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection.prepareStatement("select * from meter_readings");
				) {
			ResultSet resultSet = ps.executeQuery();
			resultSetParser(resultSet, readings);
		} catch (SQLException e) {
			System.out.println("Exception in class : MeterDetailsDAO : method : [getAll] " + e.getMessage());
		}
		return readings;
	}

	public int getCount() {
		int count = 0;
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection.prepareStatement("select count(*) as count from meter_readings");
				) {
			ResultSet resultSet = ps.executeQuery();
			resultSet.next();
			count = Integer.parseInt(resultSet.getString("count").trim());
		} catch (SQLException e) {
			System.out.println("Exception in class : MeterDetailsDAO : method : [getCount()] " + e.getMessage());
		}
		return count;
	}

	public MeterReading getById(int id) {
		ArrayList<MeterReading> readings = new ArrayList<MeterReading>();
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection.prepareStatement("select * from meter_readings where id=?");
				) {
			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery();
			resultSetParser(resultSet, readings);
		} catch (SQLException e) {
			System.out.println("Exception in class : MeterDetailsDAO : method : [getById(id)] " + e.getMessage());
		}
		return readings.size() > 0 ? readings.get(0) : null;
	}

	public ArrayList<MeterReading> getByMeterNo(String meterNo) {
		ArrayList<MeterReading> readings = new ArrayList<MeterReading>();
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection.prepareStatement("select * from meter_readings where meter_no=?");
				) {
			ps.setString(1, meterNo);
			ResultSet resultSet = ps.executeQuery();
			resultSetParser(resultSet, readings);
		} catch (SQLException e) {
			System.out.println(
					"Exception in class : MeterDetailsDAO : method : [getByMeterNo(String)] " + e.getMessage());
		}
		return readings;
	}

	public boolean deleteByMeterNo(String meterNo) {
		boolean deleted = false;
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection.prepareStatement("delete from meter_readings where meter_no=?");
				) {
			ps.setString(1, meterNo);
			ps.executeUpdate();
			deleted = true;
		} catch (SQLException e) {
			deleted = true;
			System.out.println(
					"Exception in class : MeterReadingsDAO : method : [deleteByMeterNo(String)] " + e.getMessage());
		}
		return deleted;
	}

	public boolean updateHTCellValidation(int id, int valid) {
		boolean validated = false;
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection
						.prepareStatement("update meter_readings set ht_cell_validation=? where id=?");
				) {
			ps.setInt(1, valid);
			ps.setInt(2, id);
			ps.executeUpdate();
			validated = true;
		} catch (SQLException e) {
			System.out.println("Exception in class : MeterDetailsDAO : method : [updateHTCellValidation(int,int)] "
					+ e.getMessage());
		}
		return validated;
	}

	public boolean updateCircleCellValidation(int id, int valid) {
		boolean validation = false;
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection
						.prepareStatement("update meter_readings set circle_cell_validation=? where id=?");
				) {
			ps.setInt(1, valid);
			ps.setInt(2, id);
			ps.executeUpdate();
			validation = true;
		} catch (SQLException e) {
			System.out.println("Exception in class : MeterDetailsDAO : method : [updateCircleCellValidation(int,int)] "
					+ e.getMessage());
		}
		return validation;
	}

	public boolean updateDeveloperValidation(int id, int valid) {
		boolean validation = false;
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection
						.prepareStatement("update meter_readings set developer_validation=? where id=?");
				) {
			ps.setInt(1, valid);
			ps.setInt(2, id);
			ps.executeUpdate();
			validation = true;
		} catch (SQLException e) {
			System.out.println("Exception in class : MeterDetailsDAO : method : [updateDeveloperValidation(int,int)] "
					+ e.getMessage());
		}
		return validation;
	}

	public MeterReading getLatestInsertedByMeterNo(String meterNo) {
		MeterReading readings = new MeterReading();
		int id = -1;
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection.prepareStatement("select max(id) from meter_readings where meter_no=? and discarded_flag=0");
				) {
			ps.setString(1, meterNo);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				id = resultSet.getInt(1);
			}
			readings = getById(id);
		} catch (SQLException e) {
			System.out.println("Exception in class : MeterDetailsDAO : method : [getByMeterNo(String)] " + e.getMessage());
		}
		return readings;
	}

	public MeterReading getPreviousInsertedByMeterNo(String meterNo) {
		MeterReading readings = new MeterReading();
		int id = -1;
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection.prepareStatement(
						"select max(id) as mid from meter_readings where meter_no=? and id not in (select max(id) as mid from meter_readings where meter_no=?)");
				) {
			ps.setString(1, meterNo);
			ps.setString(2, meterNo);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				id = resultSet.getInt(1);
			}
			readings = getById(id);
		} catch (SQLException e) {
			System.out.println("Exception in class : MeterDetailsDAO : method : [getPreviousInsertedByMeterNo(String)] "
					+ e.getMessage());
		}
		return readings;
	}

	public MeterReading getCurrentMonthMeterReadings(String meterNo, String date) {
		ArrayList<MeterReading> readings = new ArrayList<MeterReading>();
		MeterReading meterReadings = new MeterReading();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				) {
			Calendar c = Calendar.getInstance();
			c.setTime(formatter.parse(date));
			int year = c.get(Calendar.YEAR);
			// System.out.println("Year is :"+year);
			int month = c.get(Calendar.MONTH) + 1;
			// System.out.println("Month is :"+month );
			String mon = null;
			if (month < 10) {
				mon = "0" + month;
			}
			String dateTrim = mon + "-" + year;
			// System.out.println("trimmed date is :"+dateTrim );
			PreparedStatement ps = connection.prepareStatement(
					"select * from meter_readings where meter_no=? and reading_date like '%" + dateTrim + "%'");
			ps.setString(1, meterNo);
			ResultSet rs = ps.executeQuery();
			resultSetParser(rs, readings);
			if (readings.isEmpty()) {
				MeterDetailsDAO meterDetailsDAO = new MeterDetailsDAO();
				MeterDetails meterDetails = meterDetailsDAO.getByMeterNo(meterNo);
				meterReadings.setId(-1);
				meterReadings.setMeterno(meterNo);
				meterReadings.setMf(meterDetails.getMf());
				meterReadings.setReadingDate(date);
				meterReadings.setActiveEnergy(0);
				meterReadings.setActiveTodOne(0);
				meterReadings.setActiveTodTwo(0);
				meterReadings.setActiveTodThree(0);
				meterReadings.setReactiveQuadrantOne(0);
				meterReadings.setReactiveQuadrantTwo(0);
				meterReadings.setReactiveQuadrantThree(0);
				meterReadings.setReactiveQuadrantFour(0);
				meterReadings.setHtCellValidation(0);
				meterReadings.setCircleCellValidation(0);
				meterReadings.setDeveloperValidation(0);
				meterReadings.setDiscardedFlag(0);
				meterReadings.setDiscardedBy("");
				meterReadings.setDiscardedOn("");
				meterReadings.setSrfrFlag(0);
				meterReadings.setAdjustment(0);
			} else {
				meterReadings = readings.get(0);
			}
			ps.close();
		} catch (ParseException e) {
			System.out.println(
					"Exception in class : MeterReadingsDAO : method : [getCurrentMonthMeterReadings(String,String)] "
							+ e.getMessage());
		} catch (SQLException e) {
			System.out.println(
					"Exception in class : MeterReadingsDAO : method : [getCurrentMonthMeterReadings(String,String)] "
							+ e.getMessage());
		}

		return meterReadings;
	}

	public MeterReading getCurrentMonthMeterReadings(String meterNo) {
		MeterReading meterReading = new MeterReading();
		int id = -1;
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection.prepareStatement("select max(id) from meter_readings where meter_no=?");
				) {
			ps.setString(1, meterNo);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt(1);
			}
			meterReading = getById(id);
			if (meterReading == null) {
				meterReading = new MeterReading();
				MeterDetailsDAO meterDetailsDAO = new MeterDetailsDAO();
				MeterDetails meterDetails = meterDetailsDAO.getByMeterNo(meterNo);
				meterReading.setId(-1);
				meterReading.setMeterno(meterNo);
				meterReading.setMf(meterDetails.getMf());
				meterReading.setActiveEnergy(0);
				meterReading.setActiveTodOne(0);
				meterReading.setActiveTodTwo(0);
				meterReading.setActiveTodThree(0);
				meterReading.setReactiveQuadrantOne(0);
				meterReading.setReactiveQuadrantTwo(0);
				meterReading.setReactiveQuadrantThree(0);
				meterReading.setReactiveQuadrantFour(0);
				meterReading.setHtCellValidation(0);
				meterReading.setCircleCellValidation(0);
				meterReading.setDeveloperValidation(0);
				meterReading.setDiscardedFlag(0);
				meterReading.setSrfrFlag(0);
				meterReading.setAdjustment(0);
			}
		} catch (SQLException e) {
			System.out.println(
					"Exception in class : MeterReadingsDAO : method : [getCurrentMonthMeterReadings(String,String)] "
							+ e.getMessage());
		}
		return meterReading;
	}

	public MeterReading getPreviousMonthMeterReadings(String meterNo, String date) {
		ArrayList<MeterReading> readings = new ArrayList<MeterReading>();
		MeterReading meterReadings = new MeterReading();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				) {
			Calendar c = Calendar.getInstance();
			c.setTime(formatter.parse(date));
			c.add(Calendar.MONTH, -1);
			int day = c.get(Calendar.DATE);
			String readingDay = null;
			if (day < 10) {
				readingDay = "0" + day;
			} else {
				readingDay = String.valueOf(day);
			}
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH) + 1;
			String mon = null;
			if (month < 10) {
				mon = "0" + month;
			}
			String dateTrim = mon + "-" + year;
			PreparedStatement ps = connection.prepareStatement(
					"select * from meter_readings where meter_no=? and reading_date like '%" + dateTrim + "%'");
			ps.setString(1, meterNo);
			ResultSet rs = ps.executeQuery();
			resultSetParser(rs, readings);
			if (readings.isEmpty()) {
				MeterDetailsDAO meterDetailsDAO = new MeterDetailsDAO();
				MeterDetails meterDetails = meterDetailsDAO.getByMeterNo(meterNo);
				meterReadings.setId(-1);
				meterReadings.setMeterno(meterNo);
				meterReadings.setMf(meterDetails.getMf());
				meterReadings.setReadingDate(readingDay + "-" + dateTrim);
				meterReadings.setActiveEnergy(0);
				meterReadings.setActiveTodOne(0);
				meterReadings.setActiveTodTwo(0);
				meterReadings.setActiveTodThree(0);
				meterReadings.setReactiveQuadrantOne(0);
				meterReadings.setReactiveQuadrantTwo(0);
				meterReadings.setReactiveQuadrantThree(0);
				meterReadings.setReactiveQuadrantFour(0);
				meterReadings.setHtCellValidation(0);
				meterReadings.setCircleCellValidation(0);
				meterReadings.setDeveloperValidation(0);
				meterReadings.setDiscardedFlag(0);
				meterReadings.setDiscardedBy("");
				meterReadings.setDiscardedOn("");
				meterReadings.setSrfrFlag(0);
				meterReadings.setAdjustment(0);
			} else {
				meterReadings = readings.get(0);
			}
			ps.close();
		} catch (ParseException e) {
			System.out.println(
					"Exception in class : MeterReadingsDAO : method : [getPreviousMonthMeterReadings(String,String)] "
							+ e.getMessage());
		} catch (SQLException e) {
			System.out.println(
					"Exception in class : MeterReadingsDAO : method : [getPreviousMonthMeterReadings(String,String)] "
							+ e.getMessage());
		}

		return meterReadings;
	}

	public MeterReading getPreviousMonthMeterReadings(String meterNo) {
		MeterReading meterReading = new MeterReading();
		int id = -1;
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection.prepareStatement(
						"select max(id) as mid from meter_readings where meter_no=? and id not in (select max(id) as mid from meter_readings where meter_no=?)");
					) {
			ps.setString(1, meterNo);
			ps.setString(2, meterNo);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt(1);
			}
			meterReading = getById(id);
			if (meterReading == null) {
				meterReading = new MeterReading();
				MeterDetailsDAO meterDetailsDAO = new MeterDetailsDAO();
				MeterDetails meterDetails = meterDetailsDAO.getByMeterNo(meterNo);
				meterReading.setId(-1);
				meterReading.setMeterno(meterNo);
				meterReading.setMf(meterDetails.getMf());
				meterReading.setActiveEnergy(0);
				meterReading.setActiveTodOne(0);
				meterReading.setActiveTodTwo(0);
				meterReading.setActiveTodThree(0);
				meterReading.setReactiveQuadrantOne(0);
				meterReading.setReactiveQuadrantTwo(0);
				meterReading.setReactiveQuadrantThree(0);
				meterReading.setReactiveQuadrantFour(0);
				meterReading.setHtCellValidation(0);
				meterReading.setCircleCellValidation(0);
				meterReading.setDeveloperValidation(0);
				meterReading.setDiscardedFlag(0);
				meterReading.setSrfrFlag(0);
				meterReading.setAdjustment(0);
			}
		} catch (SQLException e) {
			System.out
			.println("Exception in class : MeterReadingsDAO : method : [getPreviousMonthMeterReadings(String)] "
					+ e.getMessage());
		}
		return meterReading;
	}

	private void resultSetParser(ResultSet resultSet, ArrayList<MeterReading> readings) {
		try {
			while (resultSet.next()) {
				MeterReading meterReading = new MeterReading();
				meterReading.setId(resultSet.getInt(1));
				meterReading.setMeterno(resultSet.getString(2));
				meterReading.setMf(resultSet.getFloat(3));
				meterReading.setReadingDate(resultSet.getString(4));
				meterReading.setActiveEnergy(resultSet.getFloat(5));
				meterReading.setActiveTodOne(resultSet.getFloat(6));
				meterReading.setActiveTodTwo(resultSet.getFloat(7));
				meterReading.setActiveTodThree(resultSet.getFloat(8));
				meterReading.setReactiveQuadrantOne(resultSet.getFloat(9));
				meterReading.setReactiveQuadrantTwo(resultSet.getFloat(10));
				meterReading.setReactiveQuadrantThree(resultSet.getFloat(11));
				meterReading.setReactiveQuadrantFour(resultSet.getFloat(12));
				meterReading.setHtCellValidation(resultSet.getInt(13));
				meterReading.setCircleCellValidation(resultSet.getInt(14));
				meterReading.setDeveloperValidation(resultSet.getInt(15));
				meterReading.setDiscardedFlag(resultSet.getInt(16));
				meterReading.setDiscardedBy(resultSet.getString(17));
				meterReading.setDiscardedOn(resultSet.getString(18));
				meterReading.setSrfrFlag(resultSet.getInt(19));
				meterReading.setAdjustment(resultSet.getFloat(20));
				readings.add(meterReading);
			}
		} catch (Exception e) {
			System.out.println("Exception in class : MeterReadingsDAO : method : resultSetParser(resultSet,arrayList) "
					+ e.getMessage());
			e.printStackTrace();
		}
	}

	public boolean updateDiscardedFlagByAdmin(int readingId, int discardedFlag, UserRoles userRoles) {
		boolean discarded = false;
		String currentDate = getCurrentDate();
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection.prepareStatement(
						"update meter_readings set discarded_flag=?, discarded_by=?, discarded_on=? where id=?");
			) {
			ps.setInt(1, discardedFlag);
			ps.setString(2, userRoles.getUsername());
			ps.setString(3, currentDate);
			ps.setInt(4, readingId);
			ps.executeUpdate();
			discarded = true;
		} catch (SQLException e) {
			discarded = false;
			System.out.println("Exception in class : MeterDetailsDAO : method : [updateDiscardedFlagByAdmin(int,int,UserRoles)] "
					+ e.getMessage());
		}
		return discarded;
	}

	public boolean updateDiscardedFlagByDeveloper(int readingId, int discardedFlag, UserRoles userRoles) {
		boolean discarded = false;
		String currentDate = getCurrentDate();
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection.prepareStatement(
						"update meter_readings set discarded_flag=?, discarded_by=?, discarded_on=? where id=?");
			) {
			ps.setInt(1, discardedFlag);
			ps.setString(2, userRoles.getUsername());
			ps.setString(3, currentDate);
			ps.setInt(4, readingId);
			ps.executeUpdate();
			discarded = true;
		} catch (SQLException e) {
			discarded = false;
			System.out.println("Exception in class : MeterDetailsDAO : method : [updateDiscardedFlagByDeveloper(int,int,UaerRoles)] "
					+ e.getMessage());
		}
		return discarded;
	}

	private String getCurrentDate() {
		Calendar c = Calendar.getInstance();
		int day = c.get(Calendar.DATE);
		String readingDay = null;
		if (day < 10) {
			readingDay = "0" + day;
		} else {
			readingDay = String.valueOf(day);
		}
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		String mon = null;
		if (month < 10) {
			mon = "0" + month;
		}
		String dateTrim = readingDay + "-" + mon + "-" + year;
		return dateTrim;
	}

}
