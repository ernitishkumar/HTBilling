package com.ht.beans;

public class MeterReading {
		
	private int id;

	private String meterno;
	
	private int mf;
	
	private String readingDate;
	
	private float activeEnergy;
	
    private float activeTodOne;
    
    private float activeTodTwo;
    
    private float activeTodThree;
    
    private float reactiveQuadrantOne;
    
    private float reactiveQuadrantTwo;
    
    private float reactiveQuadrantThree;
    
	private float reactiveQuadrantFour;
    
	private int htCellValidation;
	
	private int circleCellValidation;
	
	private int developerValidation;
	
	private int discardedFlag;
	
	private String discardedBy;
	
	private String discardedOn;
	
	private int srfrFlag;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMeterno() {
		return meterno;
	}

	public void setMeterno(String meterno) {
		this.meterno = meterno;
	}

	public int getMf() {
		return mf;
	}

	public void setMf(int mf) {
		this.mf = mf;
	}

	public String getReadingDate() {
		return readingDate;
	}

	public void setReadingDate(String readingDate) {
		this.readingDate = readingDate;
	}

	public float getActiveEnergy() {
		return activeEnergy;
	}

	public void setActiveEnergy(float activeEnergy) {
		this.activeEnergy = activeEnergy;
	}

	public float getActiveTodOne() {
		return activeTodOne;
	}

	public void setActiveTodOne(float activeTodOne) {
		this.activeTodOne = activeTodOne;
	}

	public float getActiveTodTwo() {
		return activeTodTwo;
	}

	public void setActiveTodTwo(float activeTodTwo) {
		this.activeTodTwo = activeTodTwo;
	}

	public float getActiveTodThree() {
		return activeTodThree;
	}

	public void setActiveTodThree(float activeTodThree) {
		this.activeTodThree = activeTodThree;
	}

	public float getReactiveQuadrantOne() {
		return reactiveQuadrantOne;
	}

	public void setReactiveQuadrantOne(float reactiveQuadrantOne) {
		this.reactiveQuadrantOne = reactiveQuadrantOne;
	}

	public float getReactiveQuadrantTwo() {
		return reactiveQuadrantTwo;
	}

	public void setReactiveQuadrantTwo(float reactiveQuadrantTwo) {
		this.reactiveQuadrantTwo = reactiveQuadrantTwo;
	}

	public float getReactiveQuadrantThree() {
		return reactiveQuadrantThree;
	}

	public void setReactiveQuadrantThree(float reactiveQuadrantThree) {
		this.reactiveQuadrantThree = reactiveQuadrantThree;
	}

	public float getReactiveQuadrantFour() {
		return reactiveQuadrantFour;
	}

	public void setReactiveQuadrantFour(float reactiveQuadrantFour) {
		this.reactiveQuadrantFour = reactiveQuadrantFour;
	}

	public int getHtCellValidation() {
		return htCellValidation;
	}

	public void setHtCellValidation(int htCellValidation) {
		this.htCellValidation = htCellValidation;
	}

	public int getCircleCellValidation() {
		return circleCellValidation;
	}

	public void setCircleCellValidation(int circleCellValidation) {
		this.circleCellValidation = circleCellValidation;
	}

	public int getDeveloperValidation() {
		return developerValidation;
	}

	public void setDeveloperValidation(int developerValidation) {
		this.developerValidation = developerValidation;
	}
		public int getDiscardedFlag() {
		return discardedFlag;
	}

	public void setDiscardedFlag(int discardedFlag) {
		this.discardedFlag = discardedFlag;
	}

	public String getDiscardedBy() {
		return discardedBy;
	}

	public void setDiscardedBy(String discardedBy) {
		this.discardedBy = discardedBy;
	}

	public String getDiscardedOn() {
		return discardedOn;
	}

	public void setDiscardedOn(String discardedOn) {
		this.discardedOn = discardedOn;
	}
	
	

	public int getSrfrFlag() {
		return srfrFlag;
	}

	public void setSrfrFlag(int srfrFlag) {
		this.srfrFlag = srfrFlag;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	
	@Override
	public String toString() {
		return "MeterReading [id=" + id + ", meterno=" + meterno + ", mf=" + mf + ", readingDate=" + readingDate
				+ ", activeEnergy=" + activeEnergy + ", activeTodOne=" + activeTodOne + ", activeTodTwo=" + activeTodTwo
				+ ", activeTodThree=" + activeTodThree + ", reactiveQuadrantOne=" + reactiveQuadrantOne
				+ ", reactiveQuadrantTwo=" + reactiveQuadrantTwo + ", reactiveQuadrantThree=" + reactiveQuadrantThree
				+ ", reactiveQuadrantFour=" + reactiveQuadrantFour + ", htCellValidation=" + htCellValidation
				+ ", circleCellValidation=" + circleCellValidation + ", developerValidation=" + developerValidation
				+ ", discardedFlag=" + discardedFlag + ", discardedBy=" + discardedBy + ", discardedOn=" + discardedOn
				+ ", srfrFlag=" + srfrFlag + "]";
	}

	
	
}
