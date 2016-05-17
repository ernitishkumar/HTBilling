package com.ht.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class InvestorConsumptionView {
	
	private int id;
	private Consumption consumption;
	private Investor investor;
	private float activeConsumption;
	private float reactiveConsumption;
	private int circleValidation;
	private int billGenerated;
	private List<Machine> machines;
	private int billDetailsId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Consumption getConsumption() {
		return consumption;
	}
	public void setConsumption(Consumption consumption) {
		this.consumption = consumption;
	}
	public Investor getInvestor() {
		return investor;
	}
	public void setInvestor(Investor investor) {
		this.investor = investor;
	}
	public float getActiveConsumption() {
		return activeConsumption;
	}
	public void setActiveConsumption(float activeConsumption) {
		this.activeConsumption = activeConsumption;
	}
	public float getReactiveConsumption() {
		return reactiveConsumption;
	}
	public void setReactiveConsumption(float reactiveConsumption) {
		this.reactiveConsumption = reactiveConsumption;
	}
	public List<Machine> getMachines() {
		return machines;
	}
	public void setMachines(List<Machine> machines) {
		this.machines = machines;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "InvestorConsumptionView [id=" + id + ", consumption=" + consumption + ", investor=" + investor
				+ ", activeConsumption=" + activeConsumption + ", reactiveConsumption=" + reactiveConsumption
				+ ", circleValidation=" + circleValidation + ", billGenerated=" + billGenerated + ", machines="
				+ machines + ", billDetailsId=" + billDetailsId + "]";
	}
	
}
