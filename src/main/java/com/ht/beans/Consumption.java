package com.ht.beans;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Consumption {

	private int id;
	private String meterNo;
	private String date;
	private BigDecimal activeConsumption;
	private BigDecimal reactiveConsumption;
	private int plantId;
	private String plantCode;
	private int meterReadingId;
	private int consumptionBifurcated;
	private BigDecimal adjustment;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
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
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the activeConsumption
	 */
	public BigDecimal getActiveConsumption() {
		return activeConsumption;
	}
	/**
	 * @param activeConsumption the activeConsumption to set
	 */
	public void setActiveConsumption(BigDecimal activeConsumption) {
		this.activeConsumption = activeConsumption;
	}
	/**
	 * @return the reactiveConsumption
	 */
	public BigDecimal getReactiveConsumption() {
		return reactiveConsumption;
	}
	/**
	 * @param reactiveConsumption the reactiveConsumption to set
	 */
	public void setReactiveConsumption(BigDecimal reactiveConsumption) {
		this.reactiveConsumption = reactiveConsumption;
	}
	/**
	 * @return the plantId
	 */
	public int getPlantId() {
		return plantId;
	}
	/**
	 * @param plantId the plantId to set
	 */
	public void setPlantId(int plantId) {
		this.plantId = plantId;
	}
	/**
	 * @return the plantCode
	 */
	public String getPlantCode() {
		return plantCode;
	}
	/**
	 * @param plantCode the plantCode to set
	 */
	public void setPlantCode(String plantCode) {
		this.plantCode = plantCode;
	}
	/**
	 * @return the meterReadingId
	 */
	public int getMeterReadingId() {
		return meterReadingId;
	}
	/**
	 * @param meterReadingId the meterReadingId to set
	 */
	public void setMeterReadingId(int meterReadingId) {
		this.meterReadingId = meterReadingId;
	}
	/**
	 * @return the consumptionBifurcated
	 */
	public int getConsumptionBifurcated() {
		return consumptionBifurcated;
	}
	/**
	 * @param consumptionBifurcated the consumptionBifurcated to set
	 */
	public void setConsumptionBifurcated(int consumptionBifurcated) {
		this.consumptionBifurcated = consumptionBifurcated;
	}
	/**
	 * @return the adjustment
	 */
	public BigDecimal getAdjustment() {
		return adjustment;
	}
	/**
	 * @param adjustment the adjustment to set
	 */
	public void setAdjustment(BigDecimal adjustment) {
		this.adjustment = adjustment;
	}
	/**
	 * @param id
	 * @param meterNo
	 * @param date
	 * @param activeConsumption
	 * @param reactiveConsumption
	 * @param plantId
	 * @param plantCode
	 * @param meterReadingId
	 * @param consumptionBifurcated
	 * @param adjustment
	 */
	public Consumption(int id, String meterNo, String date,
			BigDecimal activeConsumption, BigDecimal reactiveConsumption,
			int plantId, String plantCode, int meterReadingId,
			int consumptionBifurcated, BigDecimal adjustment) {
		this.id = id;
		this.meterNo = meterNo;
		this.date = date;
		this.activeConsumption = activeConsumption;
		this.reactiveConsumption = reactiveConsumption;
		this.plantId = plantId;
		this.plantCode = plantCode;
		this.meterReadingId = meterReadingId;
		this.consumptionBifurcated = consumptionBifurcated;
		this.adjustment = adjustment;
	}
	/**
	 * @param meterNo
	 * @param date
	 * @param activeConsumption
	 * @param reactiveConsumption
	 * @param plantId
	 * @param plantCode
	 * @param meterReadingId
	 * @param consumptionBifurcated
	 * @param adjustment
	 */
	public Consumption(String meterNo, String date,
			BigDecimal activeConsumption, BigDecimal reactiveConsumption,
			int plantId, String plantCode, int meterReadingId,
			int consumptionBifurcated, BigDecimal adjustment) {
		this.meterNo = meterNo;
		this.date = date;
		this.activeConsumption = activeConsumption;
		this.reactiveConsumption = reactiveConsumption;
		this.plantId = plantId;
		this.plantCode = plantCode;
		this.meterReadingId = meterReadingId;
		this.consumptionBifurcated = consumptionBifurcated;
		this.adjustment = adjustment;
	}
	/**
	 * Default Constructor
	 */
	public Consumption() {
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Consumption [id=" + id + ", meterNo=" + meterNo + ", date="
				+ date + ", activeConsumption=" + activeConsumption
				+ ", reactiveConsumption=" + reactiveConsumption + ", plantId="
				+ plantId + ", plantCode=" + plantCode + ", meterReadingId="
				+ meterReadingId + ", consumptionBifurcated="
				+ consumptionBifurcated + ", adjustment=" + adjustment + "]";
	}
    
	
	
}
