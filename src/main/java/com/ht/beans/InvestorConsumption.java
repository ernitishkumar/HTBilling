package com.ht.beans;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class InvestorConsumption {

	private int id;
	private int consumptionId;
	private int investorId;
	private BigDecimal activeConsumption;
	private BigDecimal reactiveConsumption;
    private int circleValidation;
    private int billGenerated;
    private BigDecimal adjustment;
    private BigDecimal adjustmentUnit;
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
	 * @return the consumptionId
	 */
	public int getConsumptionId() {
		return consumptionId;
	}
	/**
	 * @param consumptionId the consumptionId to set
	 */
	public void setConsumptionId(int consumptionId) {
		this.consumptionId = consumptionId;
	}
	/**
	 * @return the investorId
	 */
	public int getInvestorId() {
		return investorId;
	}
	/**
	 * @param investorId the investorId to set
	 */
	public void setInvestorId(int investorId) {
		this.investorId = investorId;
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
	 * @return the circleValidation
	 */
	public int getCircleValidation() {
		return circleValidation;
	}
	/**
	 * @param circleValidation the circleValidation to set
	 */
	public void setCircleValidation(int circleValidation) {
		this.circleValidation = circleValidation;
	}
	/**
	 * @return the billGenerated
	 */
	public int getBillGenerated() {
		return billGenerated;
	}
	/**
	 * @param billGenerated the billGenerated to set
	 */
	public void setBillGenerated(int billGenerated) {
		this.billGenerated = billGenerated;
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
	 * @return the adjustmentUnit
	 */
	public BigDecimal getAdjustmentUnit() {
		return adjustmentUnit;
	}
	/**
	 * @param adjustmentUnit the adjustmentUnit to set
	 */
	public void setAdjustmentUnit(BigDecimal adjustmentUnit) {
		this.adjustmentUnit = adjustmentUnit;
	}
	/**
	 * Default Constructor
	 */
	public InvestorConsumption() {
	}
	/**
	 * @param id
	 * @param consumptionId
	 * @param investorId
	 * @param activeConsumption
	 * @param reactiveConsumption
	 * @param circleValidation
	 * @param billGenerated
	 * @param adjustment
	 * @param adjustmentUnit
	 */
	public InvestorConsumption(int id, int consumptionId, int investorId,
			BigDecimal activeConsumption, BigDecimal reactiveConsumption,
			int circleValidation, int billGenerated, BigDecimal adjustment,
			BigDecimal adjustmentUnit) {
		this.id = id;
		this.consumptionId = consumptionId;
		this.investorId = investorId;
		this.activeConsumption = activeConsumption;
		this.reactiveConsumption = reactiveConsumption;
		this.circleValidation = circleValidation;
		this.billGenerated = billGenerated;
		this.adjustment = adjustment;
		this.adjustmentUnit = adjustmentUnit;
	}
	/**
	 * @param consumptionId
	 * @param investorId
	 * @param activeConsumption
	 * @param reactiveConsumption
	 * @param circleValidation
	 * @param billGenerated
	 * @param adjustment
	 * @param adjustmentUnit
	 */
	public InvestorConsumption(int consumptionId, int investorId,
			BigDecimal activeConsumption, BigDecimal reactiveConsumption,
			int circleValidation, int billGenerated, BigDecimal adjustment,
			BigDecimal adjustmentUnit) {
		this.consumptionId = consumptionId;
		this.investorId = investorId;
		this.activeConsumption = activeConsumption;
		this.reactiveConsumption = reactiveConsumption;
		this.circleValidation = circleValidation;
		this.billGenerated = billGenerated;
		this.adjustment = adjustment;
		this.adjustmentUnit = adjustmentUnit;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "InvestorConsumption [id=" + id + ", consumptionId="
				+ consumptionId + ", investorId=" + investorId
				+ ", activeConsumption=" + activeConsumption
				+ ", reactiveConsumption=" + reactiveConsumption
				+ ", circleValidation=" + circleValidation + ", billGenerated="
				+ billGenerated + ", adjustment=" + adjustment
				+ ", adjustmentUnit=" + adjustmentUnit + "]";
	}
	
}
