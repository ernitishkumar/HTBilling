package com.ht.beans;

import java.math.BigDecimal;

public class MeterReading {
		
	private int id;

	private String meterno;
	
	private BigDecimal mf;
	
	private String readingDate;
	
	private BigDecimal activeEnergy;
	
    private BigDecimal activeTodOne;
    
    private BigDecimal activeTodTwo;
    
    private BigDecimal activeTodThree;
    
    private BigDecimal reactiveQuadrantOne;
    
    private BigDecimal reactiveQuadrantTwo;
    
    private BigDecimal reactiveQuadrantThree;
    
	private BigDecimal reactiveQuadrantFour;
    
	private int htCellValidation;
	
	private int circleCellValidation;
	
	private int developerValidation;
	
	private int discardedFlag;
	
	private String discardedBy;
	
	private String discardedOn;
	
	private int srfrFlag;
	
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
	 * @return the meterno
	 */
	public String getMeterno() {
		return meterno;
	}

	/**
	 * @param meterno the meterno to set
	 */
	public void setMeterno(String meterno) {
		this.meterno = meterno;
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
	 * @return the readingDate
	 */
	public String getReadingDate() {
		return readingDate;
	}

	/**
	 * @param readingDate the readingDate to set
	 */
	public void setReadingDate(String readingDate) {
		this.readingDate = readingDate;
	}

	/**
	 * @return the activeEnergy
	 */
	public BigDecimal getActiveEnergy() {
		return activeEnergy;
	}

	/**
	 * @param activeEnergy the activeEnergy to set
	 */
	public void setActiveEnergy(BigDecimal activeEnergy) {
		this.activeEnergy = activeEnergy;
	}

	/**
	 * @return the activeTodOne
	 */
	public BigDecimal getActiveTodOne() {
		return activeTodOne;
	}

	/**
	 * @param activeTodOne the activeTodOne to set
	 */
	public void setActiveTodOne(BigDecimal activeTodOne) {
		this.activeTodOne = activeTodOne;
	}

	/**
	 * @return the activeTodTwo
	 */
	public BigDecimal getActiveTodTwo() {
		return activeTodTwo;
	}

	/**
	 * @param activeTodTwo the activeTodTwo to set
	 */
	public void setActiveTodTwo(BigDecimal activeTodTwo) {
		this.activeTodTwo = activeTodTwo;
	}

	/**
	 * @return the activeTodThree
	 */
	public BigDecimal getActiveTodThree() {
		return activeTodThree;
	}

	/**
	 * @param activeTodThree the activeTodThree to set
	 */
	public void setActiveTodThree(BigDecimal activeTodThree) {
		this.activeTodThree = activeTodThree;
	}

	/**
	 * @return the reactiveQuadrantOne
	 */
	public BigDecimal getReactiveQuadrantOne() {
		return reactiveQuadrantOne;
	}

	/**
	 * @param reactiveQuadrantOne the reactiveQuadrantOne to set
	 */
	public void setReactiveQuadrantOne(BigDecimal reactiveQuadrantOne) {
		this.reactiveQuadrantOne = reactiveQuadrantOne;
	}

	/**
	 * @return the reactiveQuadrantTwo
	 */
	public BigDecimal getReactiveQuadrantTwo() {
		return reactiveQuadrantTwo;
	}

	/**
	 * @param reactiveQuadrantTwo the reactiveQuadrantTwo to set
	 */
	public void setReactiveQuadrantTwo(BigDecimal reactiveQuadrantTwo) {
		this.reactiveQuadrantTwo = reactiveQuadrantTwo;
	}

	/**
	 * @return the reactiveQuadrantThree
	 */
	public BigDecimal getReactiveQuadrantThree() {
		return reactiveQuadrantThree;
	}

	/**
	 * @param reactiveQuadrantThree the reactiveQuadrantThree to set
	 */
	public void setReactiveQuadrantThree(BigDecimal reactiveQuadrantThree) {
		this.reactiveQuadrantThree = reactiveQuadrantThree;
	}

	/**
	 * @return the reactiveQuadrantFour
	 */
	public BigDecimal getReactiveQuadrantFour() {
		return reactiveQuadrantFour;
	}

	/**
	 * @param reactiveQuadrantFour the reactiveQuadrantFour to set
	 */
	public void setReactiveQuadrantFour(BigDecimal reactiveQuadrantFour) {
		this.reactiveQuadrantFour = reactiveQuadrantFour;
	}

	/**
	 * @return the htCellValidation
	 */
	public int getHtCellValidation() {
		return htCellValidation;
	}

	/**
	 * @param htCellValidation the htCellValidation to set
	 */
	public void setHtCellValidation(int htCellValidation) {
		this.htCellValidation = htCellValidation;
	}

	/**
	 * @return the circleCellValidation
	 */
	public int getCircleCellValidation() {
		return circleCellValidation;
	}

	/**
	 * @param circleCellValidation the circleCellValidation to set
	 */
	public void setCircleCellValidation(int circleCellValidation) {
		this.circleCellValidation = circleCellValidation;
	}

	/**
	 * @return the developerValidation
	 */
	public int getDeveloperValidation() {
		return developerValidation;
	}

	/**
	 * @param developerValidation the developerValidation to set
	 */
	public void setDeveloperValidation(int developerValidation) {
		this.developerValidation = developerValidation;
	}

	/**
	 * @return the discardedFlag
	 */
	public int getDiscardedFlag() {
		return discardedFlag;
	}

	/**
	 * @param discardedFlag the discardedFlag to set
	 */
	public void setDiscardedFlag(int discardedFlag) {
		this.discardedFlag = discardedFlag;
	}

	/**
	 * @return the discardedBy
	 */
	public String getDiscardedBy() {
		return discardedBy;
	}

	/**
	 * @param discardedBy the discardedBy to set
	 */
	public void setDiscardedBy(String discardedBy) {
		this.discardedBy = discardedBy;
	}

	/**
	 * @return the discardedOn
	 */
	public String getDiscardedOn() {
		return discardedOn;
	}

	/**
	 * @param discardedOn the discardedOn to set
	 */
	public void setDiscardedOn(String discardedOn) {
		this.discardedOn = discardedOn;
	}

	/**
	 * @return the srfrFlag
	 */
	public int getSrfrFlag() {
		return srfrFlag;
	}

	/**
	 * @param srfrFlag the srfrFlag to set
	 */
	public void setSrfrFlag(int srfrFlag) {
		this.srfrFlag = srfrFlag;
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
	 * @param meterno
	 * @param mf
	 * @param readingDate
	 * @param activeEnergy
	 * @param activeTodOne
	 * @param activeTodTwo
	 * @param activeTodThree
	 * @param reactiveQuadrantOne
	 * @param reactiveQuadrantTwo
	 * @param reactiveQuadrantThree
	 * @param reactiveQuadrantFour
	 * @param htCellValidation
	 * @param circleCellValidation
	 * @param developerValidation
	 * @param discardedFlag
	 * @param discardedBy
	 * @param discardedOn
	 * @param srfrFlag
	 * @param adjustment
	 */
	public MeterReading(int id, String meterno, BigDecimal mf,
			String readingDate, BigDecimal activeEnergy,
			BigDecimal activeTodOne, BigDecimal activeTodTwo,
			BigDecimal activeTodThree, BigDecimal reactiveQuadrantOne,
			BigDecimal reactiveQuadrantTwo, BigDecimal reactiveQuadrantThree,
			BigDecimal reactiveQuadrantFour, int htCellValidation,
			int circleCellValidation, int developerValidation,
			int discardedFlag, String discardedBy, String discardedOn,
			int srfrFlag, BigDecimal adjustment) {
		this.id = id;
		this.meterno = meterno;
		this.mf = mf;
		this.readingDate = readingDate;
		this.activeEnergy = activeEnergy;
		this.activeTodOne = activeTodOne;
		this.activeTodTwo = activeTodTwo;
		this.activeTodThree = activeTodThree;
		this.reactiveQuadrantOne = reactiveQuadrantOne;
		this.reactiveQuadrantTwo = reactiveQuadrantTwo;
		this.reactiveQuadrantThree = reactiveQuadrantThree;
		this.reactiveQuadrantFour = reactiveQuadrantFour;
		this.htCellValidation = htCellValidation;
		this.circleCellValidation = circleCellValidation;
		this.developerValidation = developerValidation;
		this.discardedFlag = discardedFlag;
		this.discardedBy = discardedBy;
		this.discardedOn = discardedOn;
		this.srfrFlag = srfrFlag;
		this.adjustment = adjustment;
	}

	/**
	 * @param meterno
	 * @param mf
	 * @param readingDate
	 * @param activeEnergy
	 * @param activeTodOne
	 * @param activeTodTwo
	 * @param activeTodThree
	 * @param reactiveQuadrantOne
	 * @param reactiveQuadrantTwo
	 * @param reactiveQuadrantThree
	 * @param reactiveQuadrantFour
	 * @param htCellValidation
	 * @param circleCellValidation
	 * @param developerValidation
	 * @param discardedFlag
	 * @param discardedBy
	 * @param discardedOn
	 * @param srfrFlag
	 * @param adjustment
	 */
	public MeterReading(String meterno, BigDecimal mf, String readingDate,
			BigDecimal activeEnergy, BigDecimal activeTodOne,
			BigDecimal activeTodTwo, BigDecimal activeTodThree,
			BigDecimal reactiveQuadrantOne, BigDecimal reactiveQuadrantTwo,
			BigDecimal reactiveQuadrantThree, BigDecimal reactiveQuadrantFour,
			int htCellValidation, int circleCellValidation,
			int developerValidation, int discardedFlag, String discardedBy,
			String discardedOn, int srfrFlag, BigDecimal adjustment) {
		this.meterno = meterno;
		this.mf = mf;
		this.readingDate = readingDate;
		this.activeEnergy = activeEnergy;
		this.activeTodOne = activeTodOne;
		this.activeTodTwo = activeTodTwo;
		this.activeTodThree = activeTodThree;
		this.reactiveQuadrantOne = reactiveQuadrantOne;
		this.reactiveQuadrantTwo = reactiveQuadrantTwo;
		this.reactiveQuadrantThree = reactiveQuadrantThree;
		this.reactiveQuadrantFour = reactiveQuadrantFour;
		this.htCellValidation = htCellValidation;
		this.circleCellValidation = circleCellValidation;
		this.developerValidation = developerValidation;
		this.discardedFlag = discardedFlag;
		this.discardedBy = discardedBy;
		this.discardedOn = discardedOn;
		this.srfrFlag = srfrFlag;
		this.adjustment = adjustment;
	}
	
	public MeterReading(AMRReading amrReading,MeterDetails meterDetails) {
		this.meterno = amrReading.getMeterNo();
		this.mf = meterDetails.getMf();
		this.readingDate = amrReading.getReadingDate();
		this.activeEnergy = amrReading.getActiveEnergy();
		this.activeTodOne = amrReading.getActiveTodOne();
		this.activeTodTwo = amrReading.getActiveTodTwo();
		this.activeTodThree = amrReading.getActiveTodThree();
		this.reactiveQuadrantOne = new BigDecimal(0);
		this.reactiveQuadrantTwo = new BigDecimal(0);
		this.reactiveQuadrantThree = new BigDecimal(0);
		this.reactiveQuadrantFour = new BigDecimal(0);
		this.htCellValidation = 1;
		this.circleCellValidation = 0;
		this.developerValidation = 0;
		this.discardedFlag = 0;
		this.discardedBy = null;
		this.discardedOn = null;
		this.srfrFlag = 0;
		this.adjustment = new BigDecimal(0);
	}
	
	/**
	 * Default Constructor
	 */
	public MeterReading() {
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MeterReading [id=" + id + ", meterno=" + meterno + ", mf=" + mf
				+ ", readingDate=" + readingDate + ", activeEnergy="
				+ activeEnergy + ", activeTodOne=" + activeTodOne
				+ ", activeTodTwo=" + activeTodTwo + ", activeTodThree="
				+ activeTodThree + ", reactiveQuadrantOne="
				+ reactiveQuadrantOne + ", reactiveQuadrantTwo="
				+ reactiveQuadrantTwo + ", reactiveQuadrantThree="
				+ reactiveQuadrantThree + ", reactiveQuadrantFour="
				+ reactiveQuadrantFour + ", htCellValidation="
				+ htCellValidation + ", circleCellValidation="
				+ circleCellValidation + ", developerValidation="
				+ developerValidation + ", discardedFlag=" + discardedFlag
				+ ", discardedBy=" + discardedBy + ", discardedOn="
				+ discardedOn + ", srfrFlag=" + srfrFlag + ", adjustment="
				+ adjustment + "]";
	}

	
	
}
