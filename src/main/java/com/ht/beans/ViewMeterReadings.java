package com.ht.beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ViewMeterReadings {
	
	private String meterNo;
    
    private Plant plant;
    
	private MeterReading currentMeterReading;
	
	private MeterReading previousMeterReading;
	
	private Consumption consumption;
	
	private Developer developer;

	public String getMeterNo() {
		return meterNo;
	}

	public void setMeterNo(String meterNo) {
		this.meterNo = meterNo;
	}

	public MeterReading getCurrentMeterReading() {
		return currentMeterReading;
	}

	public void setCurrentMeterReading(MeterReading currentMeterReading) {
		this.currentMeterReading = currentMeterReading;
	}

	public MeterReading getPreviousMeterReading() {
		return previousMeterReading;
	}

	public void setPreviousMeterReading(MeterReading previousMeterReading) {
		this.previousMeterReading = previousMeterReading;
	}
	
     public void setPlant(Plant plant){
        this.plant=plant;
    }
    
    public Plant getPlant(){
        return this.plant;
    }
	
	public Consumption getConsumption() {
		return consumption;
	}

	public void setConsumption(Consumption consumption) {
		this.consumption = consumption;
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
	
	
	}
