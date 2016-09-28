package com.ht.beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Machine {

	private int id;
	
	private String code;
	
	private String capacity;
	
	private String commissionedDate;
	
	private float activeRate;
	
	private float reactiveRate;
	
	private String ppaDate;
	
	private String ppaLetterNo;
	
	private int developerId;
	
	private Developer developer;
	
	private String particulars;
	
	public int getDeveloperId() {
		return developerId;
	}

	public void setDeveloperId(int developerId) {
		this.developerId = developerId;
	}

	private int plantId;
	
	private Plant plant;
	
	private int investorId;

	private Investor investor;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getCommissionedDate() {
		return commissionedDate;
	}

	public void setCommissionedDate(String commissionedDate) {
		this.commissionedDate = commissionedDate;
	}

	public float getActiveRate() {
		return activeRate;
	}

	public void setActiveRate(float activeRate) {
		this.activeRate = activeRate;
	}

	public float getReactiveRate() {
		return reactiveRate;
	}

	public void setReactiveRate(float reactiveRate) {
		this.reactiveRate = reactiveRate;
	}

	public String getPpaDate() {
		return ppaDate;
	}

	public void setPpaDate(String ppaDate) {
		this.ppaDate = ppaDate;
	}

	public String getPpaLetterNo() {
		return ppaLetterNo;
	}

	public void setPpaLetterNo(String ppaLetterNo) {
		this.ppaLetterNo = ppaLetterNo;
	}

	public int getPlantId() {
		return plantId;
	}

	public void setPlantId(int plantId) {
		this.plantId = plantId;
	}

	public int getInvestorId() {
		return investorId;
	}

	public void setInvestorId(int investorId) {
		this.investorId = investorId;
	}

	/**
	 * @return the particulars
	 */
	public String getParticulars() {
		return particulars;
	}

	/**
	 * @param particulars the particulars to set
	 */
	public void setParticulars(String particulars) {
		this.particulars = particulars;
	}
	
	/**
	 * @return the developer
	 */
	public Developer getDeveloper() {
		return developer;
	}

	/**
	 * @param developer the developer to set
	 */
	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}

	/**
	 * @return the plant
	 */
	public Plant getPlant() {
		return plant;
	}

	/**
	 * @param plant the plant to set
	 */
	public void setPlant(Plant plant) {
		this.plant = plant;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Machine [id=" + id + ", code=" + code + ", capacity=" + capacity + ", commissionedDate="
				+ commissionedDate + ", activeRate=" + activeRate + ", reactiveRate=" + reactiveRate + ", ppaDate="
				+ ppaDate + ", ppaLetterNo=" + ppaLetterNo + ", developerId=" + developerId + ", particulars="
				+ particulars + ", plantId=" + plantId + ", investorId=" + investorId + "]";
	}
	
}
