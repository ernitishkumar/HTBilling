/**
 * 
 */
package com.ht.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ht.beans.PlantMeterMapping;
import com.ht.utility.GlobalResources;

/**
 * @author ANKIT
 *
 */
public class PlantMeterMappingDAO {
	
	public PlantMeterMapping insert(PlantMeterMapping plantMeterMapping){
		PlantMeterMapping insertedMapping = null;
		int lastInsertedId = -1;
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection.prepareStatement("insert into plant_meter_mapping (plant_id,meter_no) values(?,?)",Statement.RETURN_GENERATED_KEYS);
				) {
			ps.setInt(1, plantMeterMapping.getPlantId());
			ps.setString(2, plantMeterMapping.getMeterNo());
			ps.executeUpdate();
			ResultSet keys = ps.getGeneratedKeys();    
			keys.next();  
			lastInsertedId = keys.getInt(1);
			insertedMapping = getById(lastInsertedId);
		} catch (SQLException e) {
			System.out.println("Exception in class : InvestorPlantMappingDAO : method : [insert(InvestorPlantMapping)] "+e.getMessage());
		}
		return insertedMapping;
	}

	private PlantMeterMapping getById(int id) {
		ArrayList<PlantMeterMapping> mappingList = new ArrayList<PlantMeterMapping>();
		try(
				Connection connection = GlobalResources.getDatasource().getConnection();
				PreparedStatement ps = connection.prepareStatement("select * from plant_meter_mapping where id=?");
				) {
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			mappingList = plantMeterMappingParser(rs);
		} catch (SQLException e) {
			System.out.println("Exception in class : PlantMeterMappingDAO : method : [getById(int)] "+e.getMessage());
		}
		return mappingList.get(0);
	}
	
	private ArrayList<PlantMeterMapping> plantMeterMappingParser(ResultSet rs) {
		ArrayList<PlantMeterMapping> mappingList = new ArrayList<PlantMeterMapping>();
		try {
			while(rs.next()){
				PlantMeterMapping plantMeterMapping = new PlantMeterMapping();
				plantMeterMapping.setId(rs.getInt(1));
				plantMeterMapping.setPlantId(rs.getInt(2));
				plantMeterMapping.setMeterNo(rs.getString(3));
				mappingList.add(plantMeterMapping);
			}
		} catch (SQLException e) {
			System.out.println("Exception in class : PlantMeterMappingDAO : method : [plantMeterMappingParser(ResultSet)] "+e.getMessage());
		}
		return mappingList;
	}

}
