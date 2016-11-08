package com.ht.beans;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MeterDetails {

	private String meterNo;
	private String make;
	private String category;
	private String type;
	private String meterClass;
	private String ctr;
	private String ptr;
	private BigDecimal mf;
	private String equipmentClass;
	private String phase;
	private String meterGroup;
	/**
	 * @return the meterNo
	 */
	public String getMeterNo() {
		return meterNo;
	}
	/**
	 * @param meterNo the meterNo to set
	 */
	public void setMeterNo(String meterNo) {
		this.meterNo = meterNo;
	}
	/**
	 * @return the make
	 */
	public String getMake() {
		return make;
	}
	/**
	 * @param make the make to set
	 */
	public void setMake(String make) {
		this.make = make;
	}
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the meterClass
	 */
	public String getMeterClass() {
		return meterClass;
	}
	/**
	 * @param meterClass the meterClass to set
	 */
	public void setMeterClass(String meterClass) {
		this.meterClass = meterClass;
	}
	/**
	 * @return the ctr
	 */
	public String getCtr() {
		return ctr;
	}
	/**
	 * @param ctr the ctr to set
	 */
	public void setCtr(String ctr) {
		this.ctr = ctr;
	}
	/**
	 * @return the ptr
	 */
	public String getPtr() {
		return ptr;
	}
	/**
	 * @param ptr the ptr to set
	 */
	public void setPtr(String ptr) {
		this.ptr = ptr;
	}
	/**
	 * @return the mf
	 */
	public BigDecimal getMf() {
		return mf;
	}
	/**
	 * @param mf the mf to set
	 */
	public void setMf(BigDecimal mf) {
		this.mf = mf;
	}
	/**
	 * @return the equipmentClass
	 */
	public String getEquipmentClass() {
		return equipmentClass;
	}
	/**
	 * @param equipmentClass the equipmentClass to set
	 */
	public void setEquipmentClass(String equipmentClass) {
		this.equipmentClass = equipmentClass;
	}
	/**
	 * @return the phase
	 */
	public String getPhase() {
		return phase;
	}
	/**
	 * @param phase the phase to set
	 */
	public void setPhase(String phase) {
		this.phase = phase;
	}
	/**
	 * @return the meterGroup
	 */
	public String getMeterGroup() {
		return meterGroup;
	}
	/**
	 * @param meterGroup the meterGroup to set
	 */
	public void setMeterGroup(String meterGroup) {
		this.meterGroup = meterGroup;
	}
	/**
	 * @param meterNo
	 * @param make
	 * @param category
	 * @param type
	 * @param meterClass
	 * @param ctr
	 * @param ptr
	 * @param mf
	 * @param equipmentClass
	 * @param phase
	 * @param meterGroup
	 */
	public MeterDetails(String meterNo, String make, String category,
			String type, String meterClass, String ctr, String ptr,
			BigDecimal mf, String equipmentClass, String phase,
			String meterGroup) {
		this.meterNo = meterNo;
		this.make = make;
		this.category = category;
		this.type = type;
		this.meterClass = meterClass;
		this.ctr = ctr;
		this.ptr = ptr;
		this.mf = mf;
		this.equipmentClass = equipmentClass;
		this.phase = phase;
		this.meterGroup = meterGroup;
	}
	/**
	 * Default Constructor
	 */
	public MeterDetails() {
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MeterDetails [meterNo=" + meterNo + ", make=" + make
				+ ", category=" + category + ", type=" + type + ", meterClass="
				+ meterClass + ", ctr=" + ctr + ", ptr=" + ptr + ", mf=" + mf
				+ ", equipmentClass=" + equipmentClass + ", phase=" + phase
				+ ", meterGroup=" + meterGroup + "]";
	}
	
	
}
