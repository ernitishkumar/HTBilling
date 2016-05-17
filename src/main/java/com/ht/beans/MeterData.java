/**
 * 
 */
package com.ht.beans;

/**
 * @author NITISH
 *
 */
public class MeterData {

	private MeterDetails meter;
	
	private Plant plant;

	private MeterReading currentReading;
	
	/**
	 * @return the meter
	 */
	public MeterDetails getMeter() {
		return meter;
	}

	/**
	 * @param meter the meter to set
	 */
	public void setMeter(MeterDetails meter) {
		this.meter = meter;
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
	 * @return the currentReading
	 */
	public MeterReading getCurrentReading() {
		return currentReading;
	}

	/**
	 * @param currentReading the currentReading to set
	 */
	public void setCurrentReading(MeterReading currentReading) {
		this.currentReading = currentReading;
	}
	
	
}
