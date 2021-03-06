package com.ht.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ht.beans.Consumption;
import com.ht.beans.Developer;
import com.ht.beans.Investor;
import com.ht.beans.InvestorConsumption;
import com.ht.beans.InvestorConsumptionView;
import com.ht.utility.GlobalResources;

public class InvestorConsumptionDAO {

	public InvestorConsumption insert(InvestorConsumption investorConsumption){
		InvestorConsumption insertedInvestorConsumption = null;
		int lastInsertedId;
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection.prepareStatement("insert into investor_consumption (consumption_id, investor_id, active_consumption, reactive_consumption,circle_validation,bill_generated, adjustment, adjustment_units) values(?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
				) {
			ps.setInt(1,investorConsumption.getConsumptionId());
			ps.setInt(2, investorConsumption.getInvestorId());
			ps.setBigDecimal(3, investorConsumption.getActiveConsumption());
			ps.setBigDecimal(4, investorConsumption.getReactiveConsumption());
			ps.setInt(5,0);
			ps.setInt(6,0);
			ps.setBigDecimal(7, investorConsumption.getAdjustment());
			ps.setBigDecimal(8, investorConsumption.getAdjustmentUnit());
			ps.executeUpdate();
			ResultSet keys = ps.getGeneratedKeys();    
			keys.next();  
			lastInsertedId = keys.getInt(1);
			insertedInvestorConsumption = getById(lastInsertedId);
		} catch (SQLException e) {
			System.out.println("Exception in class : InvestorConsumptionDAO : method : [insert(Investors)] "+e.getMessage());
		}
		return insertedInvestorConsumption;
	}
	
	/**
	 * @param investorsConsumption
	 * @return
	 */
	public ArrayList<InvestorConsumption> insert(ArrayList<InvestorConsumption> investorsConsumption){
		ArrayList<InvestorConsumption> insertedInvestorConsumptions = new ArrayList<InvestorConsumption>();
		for(InvestorConsumption ic : investorsConsumption){
			InvestorConsumption insertedInvestorConsumption = insert(ic);
			insertedInvestorConsumptions.add(insertedInvestorConsumption);
		}
		return insertedInvestorConsumptions;
	}
	
	public InvestorConsumption update(InvestorConsumption investorConsumption){
		InvestorConsumption updatedConsumption = null;
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection.prepareStatement("update investor_consumption set active_consumption = ?, reactive_consumption=?,bill_generated=?,circle_validation=?, adjustment=?,adjustment_units=? where id = ?");
				) {
			ps.setBigDecimal(1, investorConsumption.getActiveConsumption());
			ps.setBigDecimal(2, investorConsumption.getReactiveConsumption());
			ps.setInt(3, investorConsumption.getBillGenerated());
			ps.setInt(4, investorConsumption.getCircleValidation());
			ps.setBigDecimal(5, investorConsumption.getAdjustment());
			ps.setBigDecimal(6, investorConsumption.getAdjustmentUnit());
			ps.setInt(7, investorConsumption.getId());
			ps.executeUpdate();
			updatedConsumption = getById(investorConsumption.getId());
		} catch (SQLException e) {
			System.out.println("Exception in class : InvestorConsumptionDAO : method : [update(InvestorConsumption)] "+e.getMessage());
		}
		return updatedConsumption;
	}
	
	public ArrayList<InvestorConsumption> update(ArrayList<InvestorConsumption> investorConsumptions){
		ArrayList<InvestorConsumption> updatedInvestorConsumptions = new ArrayList<InvestorConsumption>();
		for(InvestorConsumption ic : investorConsumptions){
			InvestorConsumption updatedInvestorConsumption = update(ic);
			updatedInvestorConsumptions.add(updatedInvestorConsumption);
		}
		return updatedInvestorConsumptions;
	}
	
	public ArrayList<InvestorConsumption> getAllInvestorConsumption(){
		ArrayList<InvestorConsumption> investorConsumtionsList = new ArrayList<InvestorConsumption>();
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection.prepareStatement("select * from investor_consumption");
				) {
			ResultSet rs = ps.executeQuery();
			investorConsumtionsList = investorConsumptionMapper(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : InvestorConsumptionDAO : method : [getAllInvestorConsumption()] "+e.getMessage());
		}
		return investorConsumtionsList;
	}

	public InvestorConsumption getById(int id){
		ArrayList<InvestorConsumption> investorConsumtionsList = new ArrayList<InvestorConsumption>();
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection.prepareStatement("select * from investor_consumption where id =?");
				) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			investorConsumtionsList = investorConsumptionMapper(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : InvestorConsumptionDAO : method : [getById(int)] "+e.getMessage());
		}
		return investorConsumtionsList.get(0);
	}
	
	public ArrayList<InvestorConsumption> getByConsumptionId(int consumptionId){
		ArrayList<InvestorConsumption> investorConsumtionsList = new ArrayList<InvestorConsumption>();
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection.prepareStatement("select * from investor_consumption where consumption_id =?");
				) {
			ps.setInt(1, consumptionId);
			ResultSet rs = ps.executeQuery();
			investorConsumtionsList = investorConsumptionMapper(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : InvestorConsumptionDAO : method : [getByConsumptionId(int)] "+e.getMessage());
		}
		return investorConsumtionsList;
	}
	
	public ArrayList<InvestorConsumption> getByInvestorId(int investorId){
		ArrayList<InvestorConsumption> investorConsumtionsList = new ArrayList<InvestorConsumption>();
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection.prepareStatement("select * from investor_consumption where investor_id =?");
				) {
			ps.setInt(1, investorId);
			ResultSet rs = ps.executeQuery();
			investorConsumtionsList = investorConsumptionMapper(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : InvestorConsumptionDAO : method : [getByInvestorId(int)] "+e.getMessage());
		}
		return investorConsumtionsList;
	}
	
	public ArrayList<InvestorConsumption> getByInvestorIdAndConsumptionId(int investorId, int consumtionId){
		ArrayList<InvestorConsumption> investorConsumtionsList = new ArrayList<InvestorConsumption>();
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection.prepareStatement("select * from investor_consumption where investor_id =? and consumption_id =?");
				) {
			ps.setInt(1, investorId);
			ps.setInt(1, consumtionId);
			ResultSet rs = ps.executeQuery();
			investorConsumtionsList = investorConsumptionMapper(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : InvestorConsumptionDAO : method : [getByInvestorIdAndConsumptionId(int,int)] "+e.getMessage());
		}
		return investorConsumtionsList;
	}
	
	private ArrayList<InvestorConsumption> investorConsumptionMapper(ResultSet rs) {
		ArrayList<InvestorConsumption> investorConsumtionsList = new ArrayList<InvestorConsumption>();
		try {
			while(rs.next()){
                InvestorConsumption investorConsumtion = new InvestorConsumption();
				investorConsumtion.setId(rs.getInt(1));
				investorConsumtion.setConsumptionId(rs.getInt(3));
				investorConsumtion.setInvestorId(rs.getInt(2));
				investorConsumtion.setActiveConsumption(rs.getBigDecimal(4));
				investorConsumtion.setReactiveConsumption(rs.getBigDecimal(5));
				investorConsumtion.setCircleValidation(rs.getInt(6));
				investorConsumtion.setBillGenerated(rs.getInt(7));
				investorConsumtion.setAdjustment(rs.getBigDecimal(8));
				investorConsumtion.setAdjustmentUnit(rs.getBigDecimal(9));
				investorConsumtionsList.add(investorConsumtion);
			}
		} catch (SQLException e) {
			System.out.println("Exception in class : InvestorConsumptionDAO : method : [investorConsumptionMapper(ResultSet)] "+e.getMessage());
		}
		return investorConsumtionsList;
	}
	
	public ArrayList<InvestorConsumptionView> getViewFromList(ArrayList<InvestorConsumption> investorConsumptionList,Consumption consumption){
		ArrayList<InvestorConsumptionView> investorConsumptionViews= new ArrayList<InvestorConsumptionView>();
		InvestorsDAO investorsDAO = new InvestorsDAO();
		MachinesDAO machinesDAO = new MachinesDAO();
		BillDetailsDAO billDetailsDAO = new BillDetailsDAO();
		
		for(InvestorConsumption investorConsumption:investorConsumptionList){
			InvestorConsumptionView investorConsumptionView = new InvestorConsumptionView();
			investorConsumptionView.setId(investorConsumption.getId());
			investorConsumptionView.setConsumption(consumption);
			investorConsumptionView.setActiveConsumption(investorConsumption.getActiveConsumption());
			investorConsumptionView.setReactiveConsumption(investorConsumption.getReactiveConsumption());
			investorConsumptionView.setAdjustment(investorConsumption.getAdjustment());
			investorConsumptionView.setAdjustmentUnit(investorConsumption.getAdjustmentUnit());
			Investor investor = investorsDAO.getById(investorConsumption.getInvestorId());
			investorConsumptionView.setInvestor(investor);
			investorConsumptionView.setMachines(machinesDAO.getByInvestorId(investor.getId()));
			investorConsumptionView.setCircleValidation(investorConsumption.getCircleValidation());
			investorConsumptionView.setBillGenerated(investorConsumption.getBillGenerated());
			if(investorConsumption.getBillGenerated()==1){
				investorConsumptionView.setBillDetailsId(billDetailsDAO.getByConsumptionBifurcationId(investorConsumption.getId()).getId());
			}
			investorConsumptionViews.add(investorConsumptionView);
		}
		return investorConsumptionViews;
	}
	
	public InvestorConsumptionView getViewFromBean(InvestorConsumption investorConsumption){
		InvestorConsumptionView investorConsumptionView = new InvestorConsumptionView();
		
		InvestorsDAO investorsDAO = new InvestorsDAO();
		MachinesDAO machinesDAO = new MachinesDAO();
		BillDetailsDAO billDetailsDAO = new BillDetailsDAO();
		ConsumptionsDAO consumptionDAO = new ConsumptionsDAO();
		
		investorConsumptionView.setId(investorConsumption.getId());
		investorConsumptionView.setConsumption(consumptionDAO.getById(investorConsumption.getConsumptionId()));
		investorConsumptionView.setActiveConsumption(investorConsumption.getActiveConsumption());
		investorConsumptionView.setReactiveConsumption(investorConsumption.getReactiveConsumption());
		investorConsumptionView.setAdjustment(investorConsumption.getAdjustment());
		Investor investor = investorsDAO.getById(investorConsumption.getInvestorId());
		investorConsumptionView.setInvestor(investor);
		
		investorConsumptionView.setMachines(machinesDAO.getByInvestorId(investor.getId()));
		investorConsumptionView.setCircleValidation(investorConsumption.getCircleValidation());
		investorConsumptionView.setBillGenerated(investorConsumption.getBillGenerated());
		if(investorConsumption.getBillGenerated()==1){
			investorConsumptionView.setBillDetailsId(billDetailsDAO.getByConsumptionBifurcationId(investorConsumption.getId()).getId());
		}
		
		return investorConsumptionView;
	}
	
	public InvestorConsumption delete(int id){
		InvestorConsumption investorConsumption = null;
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection.prepareStatement("delete from investor_consumption where id =?");	
				) {
			investorConsumption = getById(id);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Exception in class : InvestorConsumptionDAO : method : [delete(int)] "+e.getMessage());
		}
		return investorConsumption;
	}
}
