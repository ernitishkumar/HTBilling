package com.ht.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ht.beans.Machine;
import com.ht.utility.GlobalResources;
import java.sql.Statement;

public class MachinesDAO {

	
	public Machine insert(Machine machine){
		Machine insertedMachine = null;
		Connection connection = GlobalResources.getConnection();
		int lastInsertedId = -1;
		try {
			PreparedStatement ps = connection
					.prepareStatement("insert into machines(code,capacity,commissioned_date,active_rate,reactive_rate,ppa_letter_no,ppa_date,developer_id,plant_id,investor_id,particulars) VALUES(?,?,?,?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, machine.getCode());
			ps.setString(2, machine.getCapacity());
			ps.setString(3, machine.getCommissionedDate());
			ps.setFloat(4, machine.getActiveRate());
			ps.setFloat(5, machine.getReactiveRate());
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
			keys.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in class : MachineDAO : method : [insert(Machine)] "+e);
		}
		return insertedMachine;
	}
	
	public Machine update(Machine machine){
		Connection connection = GlobalResources.getConnection();
		Machine updatedMachine = null;
		try {
			PreparedStatement ps = connection
					.prepareStatement("update machines set code=?,capacity=?,commissioned_date=?,active_rate=?,reactive_rate=?,ppa_letter_no=?,ppa_date=?,developer_id=?,plant_id=?,investor_id=?,particulars=? where id=?");
			ps.setString(1, machine.getCode());
			ps.setString(2, machine.getCapacity());
			ps.setString(3, machine.getCommissionedDate());
			ps.setFloat(4, machine.getActiveRate());
			ps.setFloat(5, machine.getReactiveRate());
			ps.setString(6, machine.getPpaLetterNo());
			ps.setString(7, machine.getPpaDate());
			ps.setInt(8, machine.getDeveloperId());
			ps.setInt(9, machine.getPlantId());
			ps.setInt(10, machine.getInvestorId());
			ps.setString(11, machine.getParticulars());
			ps.setInt(12, machine.getId());
			ps.executeUpdate();
			ps.close();
			updatedMachine = getById(machine.getId());
		} catch (SQLException e) {
			System.out.println("Exception in class : MachineDAO : method : [update(Machine)] "+e);
		}
		return updatedMachine;
	}
	
	public Machine delete(int id){
		Connection connection = GlobalResources.getConnection();
		Machine deletedMachine = null;
		try {
			deletedMachine = getById(id);
			PreparedStatement ps = connection
					.prepareStatement("delete from machines where id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e) {
			System.out.println("Exception in class : MachineDAO : method : [update(Machine)] "+e);
		}
		return deletedMachine;
	}
	
	public ArrayList<Machine> getAll(){
		ArrayList<Machine> machineList = new ArrayList<Machine>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from machines");
			ResultSet rs = ps .executeQuery();
			machineList = machineMapper(rs);
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in class : MachineDAO : method : [getAll()] "+e);
		}
		return machineList;
	}
	
	public Machine getById(int id){
		ArrayList<Machine> machineList = new ArrayList<Machine>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from machines where id = ?");
			ps.setInt(1,id);
			ResultSet rs = ps .executeQuery();
			machineList = machineMapper(rs);
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in class : MachineDAO : method : [getById(int)] "+e);
		}
		return machineList.get(0);
	}
	
	public Machine getByCode(String code){
		ArrayList<Machine> machineList = new ArrayList<Machine>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from machines where code = ?");
			ps.setString(1,code);
			ResultSet rs = ps .executeQuery();
			machineList = machineMapper(rs);
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in class : MachineDAO : method : [getByCode(String)] "+e);
		}
		return machineList.get(0);
	}

	public ArrayList<Machine> getByDeveloperId(int developerId){
		ArrayList<Machine> machineList = new ArrayList<Machine>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from machines where developer_id = ?");
			ps.setInt(1,developerId);
			ResultSet rs = ps .executeQuery();
			machineList = machineMapper(rs);
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in class : MachineDAO : method : [getByDeveloperId(int)] "+e);
		}
		return machineList;
	}
	
	public ArrayList<Machine> getByPlantId(int plantId){
		ArrayList<Machine> machineList = new ArrayList<Machine>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from machines where plant_id = ?");
			ps.setInt(1,plantId);
			ResultSet rs = ps .executeQuery();
			machineList = machineMapper(rs);
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in class : MachineDAO : method : [getByPlantId(int)] "+e);
		}
		return machineList;
	}
	
	public ArrayList<Machine> getByInvestorId(int investorId){
		ArrayList<Machine> machineList = new ArrayList<Machine>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from machines where investor_id = ?");
			ps.setInt(1,investorId);
			ResultSet rs = ps .executeQuery();
			machineList = machineMapper(rs);
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in class : MachineDAO : method : [getByInvestorId(int)] "+e);
		}
		return machineList;
	}
	
	public ArrayList<Machine> getByDevelperPlantAndInvestorId(int developerId, int plantId, int investorId){
		ArrayList<Machine> machineList = new ArrayList<Machine>();
		Connection connection = GlobalResources.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from machines where developer_id=?,plant_id=?,investor_id = ?");
			ps.setInt(1,developerId);
			ps.setInt(2,plantId);
			ps.setInt(3,investorId);
			ResultSet rs = ps .executeQuery();
			machineList = machineMapper(rs);
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in class : MachineDAO : method : [getByDevelperPlantAndInvestorId(int,int,int)] "+e);
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
				machine.setActiveRate(rs.getFloat(5));
				machine.setReactiveRate(rs.getFloat(6));
				machine.setPpaLetterNo(rs.getString(7));
				machine.setPpaDate(rs.getString(8));
				machine.setDeveloperId(rs.getInt(9));
				machine.setPlantId(rs.getInt(10));
				machine.setInvestorId(rs.getInt(11));
				machine.setParticulars(rs.getString(12));
				machineList.add(machine);
			}
		} catch (SQLException e) {
			
		}
		return machineList;
	}
}
