/**
 * 
 */
package com.ht.beans;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author ANKIT
 *
 */
@XmlRootElement
public class PlantMeterMapping {

	private int id;
	private int plantId;
	private String meterNo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPlantId() {
		return plantId;
	}
	public void setPlantId(int plantId) {
		this.plantId = plantId;
	}
	public String getMeterNo() {
		return meterNo;
	}
	public void setMeterNo(String meterNo) {
		this.meterNo = meterNo;
	}
	
	public PlantMeterMapping(int id, int plantId, String meterNo) {
		super();
		this.id = id;
		this.plantId = plantId;
		this.meterNo = meterNo;
	}
	
	public PlantMeterMapping(int plantId, String meterNo) {
		super();
		this.plantId = plantId;
		this.meterNo = meterNo;
	}
	
	public PlantMeterMapping() {
		super();
	}
	
	@Override
	public String toString() {
		return "PlantMeterMapping [id=" + id + ", plantId=" + plantId + ", meterNo=" + meterNo + "]";
	}
	
	
}
