package com.ht.beans;

import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class InvestorConsumptionView {
	
	private int id;
	private Consumption consumption;
	private Investor investor;
	private BigDecimal activeConsumption;
	private BigDecimal reactiveConsumption;
	private int circleValidation;
	private int billGenerated;
	private List<Machine> machines;
	private int billDetailsId;
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
	 * @return the consumption
	 */
	public Consumption getConsumption() {
		return consumption;
	}
	/**
	 * @param consumption the consumption to set
	 */
	public void setConsumption(Consumption consumption) {
		this.consumption = consumption;
	}
	/**
	 * @return the investor
	 */
	public Investor getInvestor() {
		return investor;
	}
	/**
	 * @param investor the investor to set
	 */
	public void setInvestor(Investor investor) {
		this.investor = investor;
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
	 * @return the machines
	 */
	public List<Machine> getMachines() {
		return machines;
	}
	/**
	 * @param machines the machines to set
	 */
	public void setMachines(List<Machine> machines) {
		this.machines = machines;
	}
	/**
	 * @return the billDetailsId
	 */
	public int getBillDetailsId() {
		return billDetailsId;
	}
	/**
	 * @param billDetailsId the billDetailsId to set
	 */
	public void setBillDetailsId(int billDetailsId) {
		this.billDetailsId = billDetailsId;
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
	 * @param consumption
	 * @param investor
	 * @param activeConsumption
	 * @param reactiveConsumption
	 * @param circleValidation
	 * @param billGenerated
	 * @param machines
	 * @param billDetailsId
	 * @param adjustment
	 */
	public InvestorConsumptionView(int id, Consumption consumption,
			Investor investor, BigDecimal activeConsumption,
			BigDecimal reactiveConsumption, int circleValidation,
			int billGenerated, List<Machine> machines, int billDetailsId,
			BigDecimal adjustment) {
		this.id = id;
		this.consumption = consumption;
		this.investor = investor;
		this.activeConsumption = activeConsumption;
		this.reactiveConsumption = reactiveConsumption;
		this.circleValidation = circleValidation;
		this.billGenerated = billGenerated;
		this.machines = machines;
		this.billDetailsId = billDetailsId;
		this.adjustment = adjustment;
	}
	/**
	 * @param consumption
	 * @param investor
	 * @param activeConsumption
	 * @param reactiveConsumption
	 * @param circleValidation
	 * @param billGenerated
	 * @param machines
	 * @param billDetailsId
	 * @param adjustment
	 */
	public InvestorConsumptionView(Consumption consumption, Investor investor,
			BigDecimal activeConsumption, BigDecimal reactiveConsumption,
			int circleValidation, int billGenerated, List<Machine> machines,
			int billDetailsId, BigDecimal adjustment) {
		this.consumption = consumption;
		this.investor = investor;
		this.activeConsumption = activeConsumption;
		this.reactiveConsumption = reactiveConsumption;
		this.circleValidation = circleValidation;
		this.billGenerated = billGenerated;
		this.machines = machines;
		this.billDetailsId = billDetailsId;
		this.adjustment = adjustment;
	}
	/**
	 * Default Constructor
	 */
	public InvestorConsumptionView() {
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "InvestorConsumptionView [id=" + id + ", consumption="
				+ consumption + ", investor=" + investor
				+ ", activeConsumption=" + activeConsumption
				+ ", reactiveConsumption=" + reactiveConsumption
				+ ", circleValidation=" + circleValidation + ", billGenerated="
				+ billGenerated + ", machines=" + machines + ", billDetailsId="
				+ billDetailsId + ", adjustment=" + adjustment + "]";
	}
	
}
