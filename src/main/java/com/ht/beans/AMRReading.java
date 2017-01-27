/**
 * 
 */
package com.ht.beans;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author lenovo
 *
 */
public class AMRReading {

	private int id;

	private String meterno;
	
	private String readingDate;
	
	private BigDecimal activeEnergy;
	
    private BigDecimal activeTodOne;
    
    private BigDecimal activeTodTwo;
    
    private BigDecimal activeTodThree;
    
    private BigDecimal reactiveQuadrantOne;
    
    private BigDecimal reactiveQuadrantTwo;
    
    private BigDecimal reactiveQuadrantThree;
    
	private BigDecimal reactiveQuadrantFour;
	
	private Date uploadedOn;
	
	private int status;
	
	private String misc1;
	
	private String misc2;
	
	private String misc3;

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
	 * @return the uploadedOn
	 */
	public Date getUploadedOn() {
		return uploadedOn;
	}

	/**
	 * @param uploadedOn the uploadedOn to set
	 */
	public void setUploadedOn(Date uploadedOn) {
		this.uploadedOn = uploadedOn;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the misc1
	 */
	public String getMisc1() {
		return misc1;
	}

	/**
	 * @param misc1 the misc1 to set
	 */
	public void setMisc1(String misc1) {
		this.misc1 = misc1;
	}

	/**
	 * @return the misc2
	 */
	public String getMisc2() {
		return misc2;
	}

	/**
	 * @param misc2 the misc2 to set
	 */
	public void setMisc2(String misc2) {
		this.misc2 = misc2;
	}

	/**
	 * @return the misc3
	 */
	public String getMisc3() {
		return misc3;
	}

	/**
	 * @param misc3 the misc3 to set
	 */
	public void setMisc3(String misc3) {
		this.misc3 = misc3;
	}

	/**
	 * @param id
	 * @param meterno
	 * @param readingDate
	 * @param activeEnergy
	 * @param activeTodOne
	 * @param activeTodTwo
	 * @param activeTodThree
	 * @param reactiveQuadrantOne
	 * @param reactiveQuadrantTwo
	 * @param reactiveQuadrantThree
	 * @param reactiveQuadrantFour
	 * @param uploadedOn
	 * @param status
	 * @param misc1
	 * @param misc2
	 * @param misc3
	 */
	public AMRReading(int id, String meterno, String readingDate,
			BigDecimal activeEnergy, BigDecimal activeTodOne,
			BigDecimal activeTodTwo, BigDecimal activeTodThree,
			BigDecimal reactiveQuadrantOne, BigDecimal reactiveQuadrantTwo,
			BigDecimal reactiveQuadrantThree, BigDecimal reactiveQuadrantFour,
			Date uploadedOn, int status, String misc1, String misc2,
			String misc3) {
		this.id = id;
		this.meterno = meterno;
		this.readingDate = readingDate;
		this.activeEnergy = activeEnergy;
		this.activeTodOne = activeTodOne;
		this.activeTodTwo = activeTodTwo;
		this.activeTodThree = activeTodThree;
		this.reactiveQuadrantOne = reactiveQuadrantOne;
		this.reactiveQuadrantTwo = reactiveQuadrantTwo;
		this.reactiveQuadrantThree = reactiveQuadrantThree;
		this.reactiveQuadrantFour = reactiveQuadrantFour;
		this.uploadedOn = uploadedOn;
		this.status = status;
		this.misc1 = misc1;
		this.misc2 = misc2;
		this.misc3 = misc3;
	}

	/**
	 * @param meterno
	 * @param readingDate
	 * @param activeEnergy
	 * @param activeTodOne
	 * @param activeTodTwo
	 * @param activeTodThree
	 * @param reactiveQuadrantOne
	 * @param reactiveQuadrantTwo
	 * @param reactiveQuadrantThree
	 * @param reactiveQuadrantFour
	 * @param uploadedOn
	 * @param status
	 * @param misc1
	 * @param misc2
	 * @param misc3
	 */
	public AMRReading(String meterno, String readingDate,
			BigDecimal activeEnergy, BigDecimal activeTodOne,
			BigDecimal activeTodTwo, BigDecimal activeTodThree,
			BigDecimal reactiveQuadrantOne, BigDecimal reactiveQuadrantTwo,
			BigDecimal reactiveQuadrantThree, BigDecimal reactiveQuadrantFour,
			Date uploadedOn, int status, String misc1, String misc2,
			String misc3) {
		this.meterno = meterno;
		this.readingDate = readingDate;
		this.activeEnergy = activeEnergy;
		this.activeTodOne = activeTodOne;
		this.activeTodTwo = activeTodTwo;
		this.activeTodThree = activeTodThree;
		this.reactiveQuadrantOne = reactiveQuadrantOne;
		this.reactiveQuadrantTwo = reactiveQuadrantTwo;
		this.reactiveQuadrantThree = reactiveQuadrantThree;
		this.reactiveQuadrantFour = reactiveQuadrantFour;
		this.uploadedOn = uploadedOn;
		this.status = status;
		this.misc1 = misc1;
		this.misc2 = misc2;
		this.misc3 = misc3;
	}

	/**
	 * Default Constructor
	 */
	public AMRReading() {
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AMRReading [id=" + id + ", meterno=" + meterno
				+ ", readingDate=" + readingDate + ", activeEnergy="
				+ activeEnergy + ", activeTodOne=" + activeTodOne
				+ ", activeTodTwo=" + activeTodTwo + ", activeTodThree="
				+ activeTodThree + ", reactiveQuadrantOne="
				+ reactiveQuadrantOne + ", reactiveQuadrantTwo="
				+ reactiveQuadrantTwo + ", reactiveQuadrantThree="
				+ reactiveQuadrantThree + ", reactiveQuadrantFour="
				+ reactiveQuadrantFour + ", uploadedOn=" + uploadedOn
				+ ", status=" + status + ", misc1=" + misc1 + ", misc2="
				+ misc2 + ", misc3=" + misc3 + "]";
	}
	
}
