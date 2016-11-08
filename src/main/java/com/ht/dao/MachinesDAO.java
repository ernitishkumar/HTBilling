package com.ht.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ht.beans.Developer;
import com.ht.beans.Investor;
import com.ht.beans.Machine;
import com.ht.beans.Plant;
import com.ht.utility.GlobalResources;

public class MachinesDAO {

	private DevelopersDAO developerDAO = new DevelopersDAO();
	
	private PlantsDAO plantDAO = new PlantsDAO();
	
	private InvestorsDAO investorDAO = new InvestorsDAO();
	
	public Machine insert(Machine machine){
		Machine insertedMachine = null;
		int lastInsertedId = -1;
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection
						.prepareStatement("insert into machines(code,capacity,commissioned_date,active_rate,reactive_rate,ppa_letter_no,ppa_date,developer_id,plant_id,investor_id,particulars) VALUES(?,?,?,?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
				) {
			ps.setString(1, machine.getCode());
			ps.setString(2, machine.getCapacity());
			ps.setString(3, machine.getCommissionedDate());
			ps.setBigDecimal(4, machine.getActiveRate());
			ps.setBigDecimal(5, machine.getReactiveRate());
			ps.setString(6, machine.getPpaLetterNo());
			ps.setString(7, machine.getPpaDate());
			ps.setInt(8, machine.getDeveloperId());
			ps.setInt(9, machine.getPlantId());
			ps.setInt(10, machine.getInvestorId());
			ps.setString(11, machine.getParticulars());
			ps.executeUpdate();
			ResultSet keys = ps.getGeneratedKeys();    
			keys.next();  
			lastInsertedId = keys.getInt(1);
			insertedMachine = getById(lastInsertedId);
		} catch (SQLException e) {
			System.out.println("Exception in class : MachineDAO : method : [insert(Machine)] "+e);
		}
		return insertedMachine;
	}

	public Machine update(Machine machine){
		Machine updatedMachine = null;
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection
						.prepareStatement("update machines set code=?,capacity=?,commissioned_date=?,active_rate=?,reactive_rate=?,ppa_letter_no=?,ppa_date=?,developer_id=?,plant_id=?,investor_id=?,particulars=? where id=?");
				) {
			ps.setString(1, machine.getCode());
			ps.setString(2, machine.getCapacity());
			ps.setString(3, machine.getCommissionedDate());
			ps.setBigDecimal(4, machine.getActiveRate());
			ps.setBigDecimal(5, machine.getReactiveRate());
			ps.setString(6, machine.getPpaLetterNo());
			ps.setString(7, machine.getPpaDate());
			ps.setInt(8, machine.getDeveloperId());
			ps.setInt(9, machine.getPlantId());
			ps.setInt(10, machine.getInvestorId());
			ps.setString(11, machine.getParticulars());
			ps.setInt(12, machine.getId());
			ps.executeUpdate();
			updatedMachine = getById(machine.getId());
		} catch (SQLException e) {
			System.out.println("Exception in class : MachineDAO : method : [update(Machine)] "+e);
		}
		return updatedMachine;
	}

	public Machine delete(int id){
		Machine deletedMachine = null;
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection
						.prepareStatement("delete from machines where id=?");
					) {
			deletedMachine = getById(id);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Exception in class : MachineDAO : method : [update(Machine)] "+e);
		}
		return deletedMachine;
	}

	public ArrayList<Machine> getAll(){
		ArrayList<Machine> machineList = new ArrayList<Machine>();
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection.prepareStatement("select * from machines");
				) {
			ResultSet rs = ps .executeQuery();
			machineList = machineMapper(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : MachineDAO : method : [getAll()] "+e);
		}
		return machineList;
	}

	public Machine getById(int id){
		ArrayList<Machine> machineList = new ArrayList<Machine>();
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection.prepareStatement("select * from machines where id = ?");
				) {
			ps.setInt(1,id);
			ResultSet rs = ps .executeQuery();
			machineList = machineMapper(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : MachineDAO : method : [getById(int)] "+e);
		}
		return machineList.get(0);
	}

	public Machine getByCode(String code){
		ArrayList<Machine> machineList = new ArrayList<Machine>();
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection.prepareStatement("select * from machines where code = ?");
				) {
			ps.setString(1,code);
			ResultSet rs = ps .executeQuery();
			machineList = machineMapper(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : MachineDAO : method : [getByCode(String)] "+e);
		}
		return machineList.get(0);
	}

	public ArrayList<Machine> getByDeveloperId(int developerId){
		ArrayList<Machine> machineList = new ArrayList<Machine>();
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection.prepareStatement("select * from machines where developer_id = ?");
				) {
			ps.setInt(1,developerId);
			ResultSet rs = ps .executeQuery();
			machineList = machineMapper(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : MachineDAO : method : [getByDeveloperId(int)] "+e);
		}
		return machineList;
	}

	public ArrayList<Machine> getByPlantId(int plantId){
		ArrayList<Machine> machineList = new ArrayList<Machine>();
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection.prepareStatement("select * from machines where plant_id = ?");
				) {
			ps.setInt(1,plantId);
			ResultSet rs = ps .executeQuery();
			machineList = machineMapper(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : MachineDAO : method : [getByPlantId(int)] "+e);
		}
		return machineList;
	}

	public ArrayList<Machine> getByInvestorId(int investorId){
		ArrayList<Machine> machineList = new ArrayList<Machine>();
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection.prepareStatement("select * from machines where investor_id = ?");
				) {
			ps.setInt(1,investorId);
			ResultSet rs = ps .executeQuery();
			machineList = machineMapper(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : MachineDAO : method : [getByInvestorId(int)] "+e);
		}
		return machineList;
	}

	public ArrayList<Machine> getByDevelperPlantAndInvestorId(int developerId, int plantId, int investorId){
		ArrayList<Machine> machineList = new ArrayList<Machine>();
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection.prepareStatement("select * from machines where developer_id=?,plant_id=?,investor_id = ?");
				) {
			ps.setInt(1,developerId);
			ps.setInt(2,plantId);
			ps.setInt(3,investorId);
			ResultSet rs = ps .executeQuery();
			machineList = machineMapper(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : MachineDAO : method : [getByDevelperPlantAndInvestorId(int,int,int)] "+e);
		}
		return machineList;
	}	

	public ArrayList<Machine> getByPlantIdAndInvestorId(int plantId, int investorId){
		ArrayList<Machine> machineList = new ArrayList<Machine>();
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection.prepareStatement("select * from machines where plant_id=? and investor_id = ?");
				) {
			ps.setInt(1,plantId);
			ps.setInt(2,investorId);
			ResultSet rs = ps .executeQuery();
			machineList = machineMapper(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in class : MachineDAO : method : [getByPlantIdAndInvestorId(int,int)] "+e);
		}
		return machineList;
	}	

	private ArrayList<Machine> machineMapper(ResultSet rs) {
		ArrayList<Machine> machineList = new ArrayList<Machine>();
		try {
			while(rs.next()){
				Machine machine = new Machine();
				machine.setId(rs.getInt(1));
				machine.setCode(rs.getString(2));
				machine.setCapacity(rs.getString(3));
				machine.setCommissionedDate(rs.getString(4));
				machine.setActiveRate(rs.getBigDecimal(5));
				machine.setReactiveRate(rs.getBigDecimal(6));
				machine.setPpaLetterNo(rs.getString(7));
				machine.setPpaDate(rs.getString(8));
				machine.setDeveloperId(rs.getInt(9));
				machine.setPlantId(rs.getInt(10));
				machine.setInvestorId(rs.getInt(11));
				machine.setParticulars(rs.getString(12));
				
				Developer developer = developerDAO.getById(machine.getDeveloperId());
				machine.setDeveloper(developer);
				
				Plant plant = plantDAO.getById(machine.getPlantId());
				machine.setPlant(plant);
				
				Investor investor = investorDAO.getById(machine.getInvestorId());
				machine.setInvestor(investor);
				
				machineList.add(machine);
			}
		} catch (SQLException e) {
             System.out.println("Exception in class: MachinesDAO method: machineMapper(ResultSet) : "+e);
		}
		return machineList;
	}
}
