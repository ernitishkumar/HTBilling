package com.ht.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ht.beans.Investor;
import com.ht.utility.GlobalResources;

public class InvestorsDAO {

	public Investor insert(Investor investor){
		Investor createdInvestor = null;
		int lastInsertedId = -1;
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection
						.prepareStatement("insert into investors(code,name,cin,tin,vat,invoice_no,office_address, office_contact_no, office_contact_person, office_email, site_address, site_contact_no, site_contact_person, site_email) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
				) {
			ps.setString(1, investor.getCode());
			ps.setString(2, investor.getName());
			ps.setString(3, investor.getCin());
			ps.setString(4, investor.getTin());
			ps.setString(5, investor.getVat());
			ps.setString(6, investor.getInvoiceNo());
			ps.setString(7, investor.getOfficeAddress());
			ps.setString(8, investor.getOfficeContactNo());
			ps.setString(9, investor.getOfficeContactPerson());
			ps.setString(10, investor.getOfficeEmail());
			ps.setString(11, investor.getSiteAddress());
			ps.setString(12, investor.getSiteContactNo());
			ps.setString(13, investor.getSiteContactPerson());
			ps.setString(14, investor.getSiteEmail());
			ps.executeUpdate();
			ResultSet keys = ps.getGeneratedKeys();    
			keys.next();  
			lastInsertedId = keys.getInt(1);
			createdInvestor = getById(lastInsertedId);
		} catch (SQLException e) {
			System.out.println("Exception in class : InvestorDAO : method : [insert(Investor)] "+e);
		}
		return createdInvestor;
	}
	
	public Investor update(Investor investor){
		Investor updatedInvestor = null;
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection
						.prepareStatement("update investors set code=?,name=?,cin=?,tin=?,vat=?,invoice_no=?, office_address=?, office_contact_no=?, office_contact_person=?, office_email=?, site_address=?, site_contact_no=?, site_contact_person=?, site_email=? where id=?");
				) {
			ps.setString(1, investor.getCode());
			ps.setString(2, investor.getName());
			ps.setString(3, investor.getCin());
			ps.setString(4, investor.getTin());
			ps.setString(5, investor.getVat());
			ps.setString(6, investor.getInvoiceNo());
			ps.setString(7, investor.getOfficeAddress());
			ps.setString(8, investor.getOfficeContactNo());
			ps.setString(9, investor.getOfficeContactPerson());
			ps.setString(10, investor.getOfficeEmail());
			ps.setString(11, investor.getSiteAddress());
			ps.setString(12, investor.getSiteContactNo());
			ps.setString(13, investor.getSiteContactPerson());
			ps.setString(14, investor.getSiteEmail());
			ps.setInt(15, investor.getId());
			ps.executeUpdate();
			updatedInvestor = getById(investor.getId());
		} catch (SQLException e) {
			System.out.println("Exception in class : InvestorDAO : method : [update(Investor)] "+e);
		}
		return updatedInvestor;
	}
	
	public Investor delete(int id){
		Investor deletedInvestor = null;
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection
						.prepareStatement("delete from investors where id=?");
				) {
			deletedInvestor = getById(id);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Exception in class : InvestorDAO : method : [update(Investor)] "+e);
		}
		return deletedInvestor;
	}
	
	public ArrayList<Investor> getAll(){
		ArrayList<Investor> investorList = new ArrayList<Investor>();
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection.prepareStatement("select * from investors");
				) {
			ResultSet rs = ps .executeQuery();
			investorList = investorMapper(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : InvestorDAO : method : [getAll()] "+e);
		}
		return investorList;
	}
	
	public Investor getById(int id){
		ArrayList<Investor> investorList = new ArrayList<Investor>();
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection.prepareStatement("select * from investors where id = ?");
				) {
			ps.setInt(1,id);
			ResultSet rs = ps .executeQuery();
			investorList = investorMapper(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : InvestorDAO : method : [getById(int)] "+e);
		}
		return investorList.get(0);
	}

	public Investor getByCode(String code){
		ArrayList<Investor> investorList = new ArrayList<Investor>();
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection.prepareStatement("select * from investors where code = ?");
				) {
			ps.setString(1,code);
			ResultSet rs = ps .executeQuery();
			investorList = investorMapper(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : InvestorDAO : method : [getByCode(String)] "+e);
		}
		return investorList.get(0);
	}
	
	public Investor getByCIN(String cin){
		ArrayList<Investor> investorList = new ArrayList<Investor>();
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection.prepareStatement("select * from investors where cin = ?");
				) {
			ps.setString(1,cin);
			ResultSet rs = ps .executeQuery();
			investorList = investorMapper(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : InvestorDAO : method : [getByCIN(String)] "+e);
		}
		return investorList.get(0);
	}
	
	public Investor getByTIN(String tin){
		ArrayList<Investor> investorList = new ArrayList<Investor>();
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection.prepareStatement("select * from investors where tin = ?");
				) {
			ps.setString(1,tin);
			ResultSet rs = ps .executeQuery();
			investorList = investorMapper(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : InvestorDAO : method : [getByTIN(String)] "+e);
		}
		return investorList.get(0);
	}
	
	public Investor getByName(String name){
		ArrayList<Investor> investorList = new ArrayList<Investor>();
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection.prepareStatement("select * from investors where name = ?");
				) {
			ps.setString(1,name);
			ResultSet rs = ps .executeQuery();
			investorList = investorMapper(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : InvestorDAO : method : [getByName(String)] "+e);
		}
		return investorList.get(0);
	}
	
	private ArrayList<Investor> investorMapper(ResultSet rs) {
		ArrayList<Investor> investorList = new ArrayList<Investor>();
		try {
			while(rs.next()){
                Investor investor = new Investor();
				investor.setId(rs.getInt(1));
				investor.setCode(rs.getString(2));
				investor.setName(rs.getString(3));
				investor.setCin(rs.getString(4));
				investor.setTin(rs.getString(5));
				investor.setVat(rs.getString(6));
				investor.setInvoiceNo(rs.getString(7));
				investor.setOfficeAddress(rs.getString(8));
				investor.setOfficeContactNo(rs.getString(9));
				investor.setOfficeContactPerson(rs.getString(10));
				investor.setOfficeEmail(rs.getString(11));
				investor.setSiteAddress(rs.getString(12));
				investor.setSiteContactNo(rs.getString(13));
				investor.setSiteContactPerson(rs.getString(14));
				investor.setSiteEmail(rs.getString(15));
				investorList.add(investor);
			}
		} catch (SQLException e) {
			System.out.println("Exception in class : InvestorDAO : method : [investorMapper(ResultSet)] "+e);
		}
		return investorList;
	}
}
