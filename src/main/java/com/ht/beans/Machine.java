package com.ht.beans;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Machine {

	private int id;
	
	private String code;
	
	private String capacity;
	
	private String commissionedDate;
	
	private BigDecimal activeRate;
	
	private BigDecimal reactiveRate;
	
	private String ppaDate;
	
	private String ppaLetterNo;
	
	private int developerId;
	
	private Developer developer;
	
	private String particulars;

	private int plantId;
	
	private Plant plant;
	
	private int investorId;

	private Investor investor;

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
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the capacity
	 */
	public String getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity the capacity to set
	 */
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	/**
	 * @return the commissionedDate
	 */
	public String getCommissionedDate() {
		return commissionedDate;
	}

	/**
	 * @param commissionedDate the commissionedDate to set
	 */
	public void setCommissionedDate(String commissionedDate) {
		this.commissionedDate = commissionedDate;
	}

	/**
	 * @return the activeRate
	 */
	public BigDecimal getActiveRate() {
		return activeRate;
	}

	/**
	 * @param activeRate the activeRate to set
	 */
	public void setActiveRate(BigDecimal activeRate) {
		this.activeRate = activeRate;
	}

	/**
	 * @return the reactiveRate
	 */
	public BigDecimal getReactiveRate() {
		return reactiveRate;
	}

	/**
	 * @param reactiveRate the reactiveRate to set
	 */
	public void setReactiveRate(BigDecimal reactiveRate) {
		this.reactiveRate = reactiveRate;
	}

	/**
	 * @return the ppaDate
	 */
	public String getPpaDate() {
		return ppaDate;
	}

	/**
	 * @param ppaDate the ppaDate to set
	 */
	public void setPpaDate(String ppaDate) {
		this.ppaDate = ppaDate;
	}

	/**
	 * @return the ppaLetterNo
	 */
	public String getPpaLetterNo() {
		return ppaLetterNo;
	}

	/**
	 * @param ppaLetterNo the ppaLetterNo to set
	 */
	public void setPpaLetterNo(String ppaLetterNo) {
		this.ppaLetterNo = ppaLetterNo;
	}

	/**
	 * @return the developerId
	 */
	public int getDeveloperId() {
		return developerId;
	}

	/**
	 * @param developerId the developerId to set
	 */
	public void setDeveloperId(int developerId) {
		this.developerId = developerId;
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
	 * @param id
	 * @param code
	 * @param capacity
	 * @param commissionedDate
	 * @param activeRate
	 * @param reactiveRate
	 * @param ppaDate
	 * @param ppaLetterNo
	 * @param developerId
	 * @param developer
	 * @param particulars
	 * @param plantId
	 * @param plant
	 * @param investorId
	 * @param investor
	 */
	public Machine(int id, String code, String capacity,
			String commissionedDate, BigDecimal activeRate,
			BigDecimal reactiveRate, String ppaDate, String ppaLetterNo,
			int developerId, Developer developer, String particulars,
			int plantId, Plant plant, int investorId, Investor investor) {
		this.id = id;
		this.code = code;
		this.capacity = capacity;
		this.commissionedDate = commissionedDate;
		this.activeRate = activeRate;
		this.reactiveRate = reactiveRate;
		this.ppaDate = ppaDate;
		this.ppaLetterNo = ppaLetterNo;
		this.developerId = developerId;
		this.developer = developer;
		this.particulars = particulars;
		this.plantId = plantId;
		this.plant = plant;
		this.investorId = investorId;
		this.investor = investor;
	}

	/**
	 * @param code
	 * @param capacity
	 * @param commissionedDate
	 * @param activeRate
	 * @param reactiveRate
	 * @param ppaDate
	 * @param ppaLetterNo
	 * @param developerId
	 * @param developer
	 * @param particulars
	 * @param plantId
	 * @param plant
	 * @param investorId
	 * @param investor
	 */
	public Machine(String code, String capacity, String commissionedDate,
			BigDecimal activeRate, BigDecimal reactiveRate, String ppaDate,
			String ppaLetterNo, int developerId, Developer developer,
			String particulars, int plantId, Plant plant, int investorId,
			Investor investor) {
		this.code = code;
		this.capacity = capacity;
		this.commissionedDate = commissionedDate;
		this.activeRate = activeRate;
		this.reactiveRate = reactiveRate;
		this.ppaDate = ppaDate;
		this.ppaLetterNo = ppaLetterNo;
		this.developerId = developerId;
		this.developer = developer;
		this.particulars = particulars;
		this.plantId = plantId;
		this.plant = plant;
		this.investorId = investorId;
		this.investor = investor;
	}

	/**
	 * Default Constructor
	 */
	public Machine() {
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Machine [id=" + id + ", code=" + code + ", capacity="
				+ capacity + ", commissionedDate=" + commissionedDate
				+ ", activeRate=" + activeRate + ", reactiveRate="
				+ reactiveRate + ", ppaDate=" + ppaDate + ", ppaLetterNo="
				+ ppaLetterNo + ", developerId=" + developerId + ", developer="
				+ developer + ", particulars=" + particulars + ", plantId="
				+ plantId + ", plant=" + plant + ", investorId=" + investorId
				+ ", investor=" + investor + "]";
	}
	
}
