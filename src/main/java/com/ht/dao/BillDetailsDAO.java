package com.ht.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ht.beans.BillDetails;
import com.ht.beans.BillDetailsView;
import com.ht.beans.Consumption;
import com.ht.beans.Investor;
import com.ht.beans.InvestorConsumptionView;
import com.ht.beans.MeterReading;
import com.ht.utility.GlobalResources;

public class BillDetailsDAO {

	public BillDetails insert(BillDetails billDetails){
		BillDetails insertedBillDetails = null;
		int lastInsertedId = -1;
		Connection connection = null;
		try {
			//obtaining connection from connection pool
			connection = GlobalResources.getDatasource().getConnection();
			PreparedStatement ps = connection.prepareStatement("insert into bill_details (bill_no, invoice_no, meter_readings_id, investor_id, consumption_id, consumption_bifurcation_id,meter_no, reading_date, bill_generation_date, total_kwh, total_rkvh, kwh_rate, rkvh_rate, active_amount, reactive_amount, total_amount, total_amount_roundoff,total_amount_in_words,plant_id,adjustment) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,"");
			ps.setString(2, billDetails.getInvoiceNo());
			ps.setInt(3, billDetails.getMeterReadingId());
			ps.setInt(4, billDetails.getInvestorId());
			ps.setInt(5, billDetails.getConsumptionId());
			ps.setInt(6,billDetails.getConsumptionBifurcationId());
			ps.setString(7, billDetails.getMeterNo());
			ps.setString(8, billDetails.getReadingDate());
			ps.setString(9, billDetails.getBillGenerationDate());
			ps.setFloat(10, billDetails.getTotalKWH());
			ps.setFloat(11, billDetails.getTotalRKVH());
			ps.setFloat(12, billDetails.getKwhRate());
			ps.setFloat(13, billDetails.getRkvhRate());
			ps.setFloat(14, billDetails.getActiveAmount());
			ps.setFloat(15, billDetails.getReactiveAmount());
			ps.setFloat(16, billDetails.getTotalAmount());
			ps.setFloat(17, billDetails.getTotalAmountRoundOff());
			ps.setString(18,billDetails.getTotalAmountInWords());
			ps.setInt(19,billDetails.getPlantId());
			ps.setFloat(20, billDetails.getAdjustment());
			ps.executeUpdate();
			ResultSet keys = ps.getGeneratedKeys();    
			keys.next();  
			lastInsertedId = keys.getInt(1);
            insertedBillDetails = getById(lastInsertedId);
			keys.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in class : BillDetailsDAO : method : [insert(BillDetails)] "+e.getMessage());
			e.printStackTrace();
		}finally {
			if(connection != null){
				try{
					connection.close();
				}catch(Exception ignoreException){
					System.out.println("Exception while closing connection : "+ignoreException);
				}
			}
		}
		return insertedBillDetails;
	}
	
	public BillDetails update(BillDetails billDetails){
		BillDetails updatedBillDetails = null;
		Connection connection = null;
		try {
			connection = GlobalResources.getDatasource().getConnection();
			PreparedStatement ps = connection.prepareStatement("update bill_details set bill_no=?, invoice_no=?, meter_readings_id=?, investor_id=?, consumption_id=?, consumption_bifurcation_id=?,meter_no=?, reading_date=?, bill_generation_date=?, total_kwh=?, total_rkvh=?, kwh_rate=?, rkvh_rate=?, active_amount=?, reactive_amount=?, total_amount=?, total_amount_roundoff=?,total_amount_in_words=?,plant_id=?,adjustment=? where id = ?");
			ps.setString(1,billDetails.getBillNo());
			ps.setString(2, billDetails.getInvoiceNo());
			ps.setInt(3, billDetails.getMeterReadingId());
			ps.setInt(4, billDetails.getInvestorId());
			ps.setInt(5, billDetails.getConsumptionId());
			ps.setInt(6,billDetails.getConsumptionBifurcationId());
			ps.setString(7, billDetails.getMeterNo());
			ps.setString(8, billDetails.getReadingDate());
			ps.setString(9, billDetails.getBillGenerationDate());
			ps.setFloat(10, billDetails.getTotalKWH());
			ps.setFloat(11, billDetails.getTotalRKVH());
			ps.setFloat(12, billDetails.getKwhRate());
			ps.setFloat(13, billDetails.getRkvhRate());
			ps.setFloat(14, billDetails.getActiveAmount());
			ps.setFloat(15, billDetails.getReactiveAmount());
			ps.setFloat(16, billDetails.getTotalAmount());
			ps.setFloat(17, billDetails.getTotalAmountRoundOff());
			ps.setString(18,billDetails.getTotalAmountInWords());
			ps.setInt(19,billDetails.getPlantId());
			ps.setFloat(20, billDetails.getAdjustment());
			ps.setInt(21, billDetails.getId());
			ps.executeUpdate();
			ps.close();
			updatedBillDetails = getById(billDetails.getId());
		} catch (SQLException e) {
			System.out.println("Exception in class : BillDetailsDAO : method : [update(BillDetails)] "+e.getMessage());
		}finally {
			if(connection != null){
				try{
					connection.close();
				}catch(Exception ignoreException){
					System.out.println("Exception while closing connection : "+ignoreException);
				}
			}
		}
		return updatedBillDetails;
	}
	
	public BillDetails getById(int id){
		ArrayList<BillDetails> billDetails = new ArrayList<BillDetails>();
		Connection connection = null;
		try {
			connection = GlobalResources.getDatasource().getConnection();
			PreparedStatement ps = connection.prepareStatement("select * from bill_details where id = ?");
			ps .setInt(1, id);
			ResultSet rs = ps.executeQuery();
			billDetails = billDetailsMapper(rs);
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in class : BillDetailsDAO : method : [getById(int)] "+e.getMessage());
		}finally {
			if(connection != null){
				try{
					connection.close();
				}catch(Exception ignoreException){
					System.out.println("Exception while closing connection : "+ignoreException);
				}
			}
		}
		return billDetails.size() > 0 ? billDetails.get(0) : null;
	}

	public ArrayList<BillDetails> getAll(){
		ArrayList<BillDetails> billDetails = new ArrayList<BillDetails>();
		Connection connection = null;
		try {
			connection = GlobalResources.getDatasource().getConnection();
			PreparedStatement ps = connection.prepareStatement("select * from bill_details");
			ResultSet rs = ps.executeQuery();
			billDetails = billDetailsMapper(rs);
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in class : BillDetailsDAO : method : [getAll()] "+e.getMessage());
		}finally {
			if(connection != null){
				try{
					connection.close();
				}catch(Exception ignoreException){
					System.out.println("Exception while closing connection : "+ignoreException);
				}
			}
		}
		return billDetails;
	}
	
	public BillDetails getByBillNo(String billNo){
		ArrayList<BillDetails> billDetails = new ArrayList<BillDetails>();
		Connection connection = null;
		try {
			connection = GlobalResources.getDatasource().getConnection();
			PreparedStatement ps = connection.prepareStatement("select * from bill_details where bill_no=?");
			ps.setString(1, billNo);
			ResultSet rs = ps.executeQuery();
			billDetails = billDetailsMapper(rs);
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in class : BillDetailsDAO : method : [getByBillNo(String)] "+e.getMessage());
		}finally {
			if(connection != null){
				try{
					connection.close();
				}catch(Exception ignoreException){
					System.out.println("Exception while closing connection : "+ignoreException);
				}
			}
		}
		return billDetails.size() > 0 ? billDetails.get(0) : null;
	}
	
	public BillDetails getByConsumptionBifurcationId(int consumptionBifurcationId){
		ArrayList<BillDetails> billDetails = new ArrayList<BillDetails>();
		Connection connection = null;
		try {
			connection = GlobalResources.getDatasource().getConnection();
			PreparedStatement ps = connection.prepareStatement("select * from bill_details where consumption_bifurcation_id=?");
			ps.setInt(1, consumptionBifurcationId);
			ResultSet rs = ps.executeQuery();
			billDetails = billDetailsMapper(rs);
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in class : BillDetailsDAO : method : [getByConsumptionBifurcationId(int)] "+e.getMessage());
		}finally {
			if(connection != null){
				try{
					connection.close();
				}catch(Exception ignoreException){
					System.out.println("Exception while closing connection : "+ignoreException);
				}
			}
		}
		return billDetails.size() > 0 ? billDetails.get(0) : null;
	}
	public ArrayList<BillDetails> getByDate(String date) {
		ArrayList<BillDetails> billDetails = new ArrayList<BillDetails>();
		Connection connection = null;
		try {
			connection = GlobalResources.getDatasource().getConnection();
			PreparedStatement ps = connection.prepareStatement("select * from bill_details where reading_date like '%"+date+"%'");
			ResultSet rs = ps.executeQuery();
			billDetails = billDetailsMapper(rs);
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in class : BillDetailsDAO : method : [getByDate(String)] "+e.getMessage());
		}finally {
			if(connection != null){
				try{
					connection.close();
				}catch(Exception ignoreException){
					System.out.println("Exception while closing connection : "+ignoreException);
				}
			}
		}
		return billDetails;
	}
	
	public ArrayList<BillDetails> getByMeterNo(String meterNo) {
		ArrayList<BillDetails> billDetails = new ArrayList<BillDetails>();
		Connection connection = null;
		try {
			connection = GlobalResources.getDatasource().getConnection();
			PreparedStatement ps = connection.prepareStatement("select * from bill_details where meter_no= ?");
			ps.setString(1, meterNo);
			ResultSet rs = ps.executeQuery();
			billDetails = billDetailsMapper(rs);
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in class : BillDetailsDAO : method : [getByMeterNo(String)] "+e.getMessage());
		}finally {
			if(connection != null){
				try{
					connection.close();
				}catch(Exception ignoreException){
					System.out.println("Exception while closing connection : "+ignoreException);
				}
			}
		}
		return billDetails;
	}
	public ArrayList<BillDetails> getByInvestorId(int investorId){
		ArrayList<BillDetails> billDetails = new ArrayList<BillDetails>();
		Connection connection = null;
		try {
			connection = GlobalResources.getDatasource().getConnection();
			PreparedStatement ps = connection.prepareStatement("select * from bill_details where investor_id=?");
			ps.setInt(1, investorId);
			ResultSet rs = ps.executeQuery();
			billDetails = billDetailsMapper(rs);
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in class : BillDetailsDAO : method : [getByInvestorId(int)] "+e.getMessage());
		}finally {
			if(connection != null){
				try{
					connection.close();
				}catch(Exception ignoreException){
					System.out.println("Exception while closing connection : "+ignoreException);
				}
			}
		}
		return billDetails;
	}
	
	public BillDetails getByInvoiceNo(String invoiceNo) {
		ArrayList<BillDetails> billDetails = new ArrayList<BillDetails>();
		Connection connection = null;
		try {
			connection = GlobalResources.getDatasource().getConnection();
			PreparedStatement ps = connection.prepareStatement("select * from bill_details where invoice_no= ?");
			ps.setString(1, invoiceNo);
			ResultSet rs = ps.executeQuery();
			billDetails = billDetailsMapper(rs);
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in class : BillDetailsDAO : method : [getByInvoicerNo(String)] "+e.getMessage());
		}finally {
			if(connection != null){
				try{
					connection.close();
				}catch(Exception ignoreException){
					System.out.println("Exception while closing connection : "+ignoreException);
				}
			}
		}
		return billDetails.size() > 0 ? billDetails.get(0) : null;
	}
	
	public ArrayList<BillDetails> getByConsumptionId(int ConsumptionId){
		ArrayList<BillDetails> billDetails = new ArrayList<BillDetails>();
		Connection connection = null;
		try {
			connection = GlobalResources.getDatasource().getConnection();
			PreparedStatement ps = connection.prepareStatement("select * from bill_details where consumption_id=?");
			ps.setInt(1, ConsumptionId);
			ResultSet rs = ps.executeQuery();
			billDetails = billDetailsMapper(rs);
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in class : BillDetailsDAO : method : [getByConsumptionId(int)] "+e.getMessage());
		}finally {
			if(connection != null){
				try{
					connection.close();
				}catch(Exception ignoreException){
					System.out.println("Exception while closing connection : "+ignoreException);
				}
			}
		}
		return billDetails;
	}
	
	private ArrayList<BillDetails> billDetailsMapper(ResultSet rs) {
		ArrayList<BillDetails> billDetailsList = new ArrayList<BillDetails>();
		try {
			while(rs.next()){
				
				BillDetails billDetails = new BillDetails();
				billDetails.setId(rs.getInt(1));
				billDetails.setBillNo(rs.getString(2));
				billDetails.setInvoiceNo(rs.getString(3));
				billDetails.setMeterReadingId(rs.getInt(4));
				billDetails.setInvestorId(rs.getInt(5));
				billDetails.setConsumptionId(rs.getInt(6));
				billDetails.setConsumptionBifurcationId(rs.getInt(7));
				billDetails.setMeterNo(rs.getString(8));
				billDetails.setReadingDate(rs.getString(9));
				billDetails.setBillGenerationDate(rs.getString(10));
				billDetails.setTotalKWH(rs.getFloat(11));
				billDetails.setTotalRKVH(rs.getFloat(12));
				billDetails.setKwhRate(rs.getFloat(13));
				billDetails.setRkvhRate(rs.getFloat(14));
				billDetails.setActiveAmount(rs.getFloat(15));
				billDetails.setReactiveAmount(rs.getFloat(16));
				billDetails.setTotalAmount(rs.getFloat(17));
				billDetails.setTotalAmountRoundOff(rs.getFloat(18));
				billDetails.setTotalAmountInWords(rs.getString(19));
				billDetails.setPlantId(rs.getInt(20));
				billDetails.setAdjustment(rs.getFloat(21));
				billDetailsList.add(billDetails);
			}
		} catch (SQLException e) {
			System.out.println("Exception in class : BillDetailsDAO : method : [billDetailsMapper(ResultSet)] "+e.getMessage());
		}
		return billDetailsList;
	}
	
	public BillDetailsView getViewFromBean(BillDetails billDetails){
		BillDetailsView billDetailsView = new BillDetailsView();
		billDetailsView.setId(billDetails.getId());
		billDetailsView.setBillNo(billDetails.getBillNo());
		billDetailsView.setTotalAmountInWords(billDetails.getTotalAmountInWords());
		
		PlantsDAO plantsDAO = new PlantsDAO();
		billDetailsView.setPlant(plantsDAO.getById(billDetails.getPlantId()));
		//uncomment below code once invoice is being set
		//billDetailsView.setInvoiceNo(billDetails.getInvoiceNo());
		
		MeterReadingsDAO meterReadginsDAO = new MeterReadingsDAO();
		MeterReading meterReadings = meterReadginsDAO.getById(billDetails.getMeterReadingId());
		billDetailsView.setMeterReadings(meterReadings);
		
		InvestorsDAO investorsDAO = new InvestorsDAO();
		Investor investor = investorsDAO.getById(billDetails.getInvestorId());
		billDetailsView.setInvestor(investor);
		
		MachinesDAO machinesDAO = new MachinesDAO();
		billDetailsView.setMachines(machinesDAO.getByPlantIdAndInvestorId(billDetails.getPlantId(), billDetails.getInvestorId()));
		
		ConsumptionsDAO consumptionsDAO = new ConsumptionsDAO();
		Consumption consumption = consumptionsDAO.getById(billDetails.getConsumptionId());
		billDetailsView.setConsumption(consumption);
		
		InvestorConsumptionDAO investorConsumptionDAO = new InvestorConsumptionDAO();
		InvestorConsumptionView investorConsumptionView = investorConsumptionDAO.getViewFromBean(investorConsumptionDAO.getById(billDetails.getConsumptionBifurcationId()));
		billDetailsView.setInvestorConsumptionView(investorConsumptionView);
		
		billDetailsView.setMeterNo(billDetails.getMeterNo());
		
		billDetailsView.setReadingDate(billDetails.getReadingDate());
		billDetailsView.setBillGenerationDate(billDetails.getBillGenerationDate());
		
		billDetailsView.setTotalKWH(billDetails.getTotalKWH());
		billDetailsView.setTotalRKVH(billDetails.getTotalRKVH());
		
		billDetailsView.setKwhRate(billDetails.getKwhRate());
		billDetailsView.setRkvhRate(billDetails.getRkvhRate());
		
		billDetailsView.setActiveAmount(billDetails.getActiveAmount());
		billDetailsView.setReactiveAmount(billDetails.getReactiveAmount());
		
		billDetailsView.setTotalAmount(billDetails.getTotalAmount());
		billDetailsView.setTotalAmountRoundoff(billDetails.getTotalAmountRoundOff());
		
		return billDetailsView;
	}
}
