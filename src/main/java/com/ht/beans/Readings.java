package com.ht.beans;

import java.math.BigDecimal;

public class Readings {

	private int id;
	private String meterNo;
	private BigDecimal mf;
	private String redingDateTime;
	private String cmvhImport;
	private String cmvhExport;
	private String mvarhLaggingImport;
	private String mvarhLaggingExport;
	private String mvarhLeadingImport;
	private String mvarhLeadingExport;
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
	 * @return the meterNo
	 */
	public String getMeterNo() {
		return meterNo;
	}
	/**
	 * @param meterNo the meterNo to set
	 */
	public void setMeterNo(String meterNo) {
		this.meterNo = meterNo;
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
	 * @return the redingDateTime
	 */
	public String getRedingDateTime() {
		return redingDateTime;
	}
	/**
	 * @param redingDateTime the redingDateTime to set
	 */
	public void setRedingDateTime(String redingDateTime) {
		this.redingDateTime = redingDateTime;
	}
	/**
	 * @return the cmvhImport
	 */
	public String getCmvhImport() {
		return cmvhImport;
	}
	/**
	 * @param cmvhImport the cmvhImport to set
	 */
	public void setCmvhImport(String cmvhImport) {
		this.cmvhImport = cmvhImport;
	}
	/**
	 * @return the cmvhExport
	 */
	public String getCmvhExport() {
		return cmvhExport;
	}
	/**
	 * @param cmvhExport the cmvhExport to set
	 */
	public void setCmvhExport(String cmvhExport) {
		this.cmvhExport = cmvhExport;
	}
	/**
	 * @return the mvarhLaggingImport
	 */
	public String getMvarhLaggingImport() {
		return mvarhLaggingImport;
	}
	/**
	 * @param mvarhLaggingImport the mvarhLaggingImport to set
	 */
	public void setMvarhLaggingImport(String mvarhLaggingImport) {
		this.mvarhLaggingImport = mvarhLaggingImport;
	}
	/**
	 * @return the mvarhLaggingExport
	 */
	public String getMvarhLaggingExport() {
		return mvarhLaggingExport;
	}
	/**
	 * @param mvarhLaggingExport the mvarhLaggingExport to set
	 */
	public void setMvarhLaggingExport(String mvarhLaggingExport) {
		this.mvarhLaggingExport = mvarhLaggingExport;
	}
	/**
	 * @return the mvarhLeadingImport
	 */
	public String getMvarhLeadingImport() {
		return mvarhLeadingImport;
	}
	/**
	 * @param mvarhLeadingImport the mvarhLeadingImport to set
	 */
	public void setMvarhLeadingImport(String mvarhLeadingImport) {
		this.mvarhLeadingImport = mvarhLeadingImport;
	}
	/**
	 * @return the mvarhLeadingExport
	 */
	public String getMvarhLeadingExport() {
		return mvarhLeadingExport;
	}
	/**
	 * @param mvarhLeadingExport the mvarhLeadingExport to set
	 */
	public void setMvarhLeadingExport(String mvarhLeadingExport) {
		this.mvarhLeadingExport = mvarhLeadingExport;
	}
	/**
	 * @param id
	 * @param meterNo
	 * @param mf
	 * @param redingDateTime
	 * @param cmvhImport
	 * @param cmvhExport
	 * @param mvarhLaggingImport
	 * @param mvarhLaggingExport
	 * @param mvarhLeadingImport
	 * @param mvarhLeadingExport
	 */
	public Readings(int id, String meterNo, BigDecimal mf,
			String redingDateTime, String cmvhImport, String cmvhExport,
			String mvarhLaggingImport, String mvarhLaggingExport,
			String mvarhLeadingImport, String mvarhLeadingExport) {
		this.id = id;
		this.meterNo = meterNo;
		this.mf = mf;
		this.redingDateTime = redingDateTime;
		this.cmvhImport = cmvhImport;
		this.cmvhExport = cmvhExport;
		this.mvarhLaggingImport = mvarhLaggingImport;
		this.mvarhLaggingExport = mvarhLaggingExport;
		this.mvarhLeadingImport = mvarhLeadingImport;
		this.mvarhLeadingExport = mvarhLeadingExport;
	}
	/**
	 * @param meterNo
	 * @param mf
	 * @param redingDateTime
	 * @param cmvhImport
	 * @param cmvhExport
	 * @param mvarhLaggingImport
	 * @param mvarhLaggingExport
	 * @param mvarhLeadingImport
	 * @param mvarhLeadingExport
	 */
	public Readings(String meterNo, BigDecimal mf, String redingDateTime,
			String cmvhImport, String cmvhExport, String mvarhLaggingImport,
			String mvarhLaggingExport, String mvarhLeadingImport,
			String mvarhLeadingExport) {
		this.meterNo = meterNo;
		this.mf = mf;
		this.redingDateTime = redingDateTime;
		this.cmvhImport = cmvhImport;
		this.cmvhExport = cmvhExport;
		this.mvarhLaggingImport = mvarhLaggingImport;
		this.mvarhLaggingExport = mvarhLaggingExport;
		this.mvarhLeadingImport = mvarhLeadingImport;
		this.mvarhLeadingExport = mvarhLeadingExport;
	}
	/**
	 * Default Constructor
	 */
	public Readings() {
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Readings [id=" + id + ", meterNo=" + meterNo + ", mf=" + mf
				+ ", redingDateTime=" + redingDateTime + ", cmvhImport="
				+ cmvhImport + ", cmvhExport=" + cmvhExport
				+ ", mvarhLaggingImport=" + mvarhLaggingImport
				+ ", mvarhLaggingExport=" + mvarhLaggingExport
				+ ", mvarhLeadingImport=" + mvarhLeadingImport
				+ ", mvarhLeadingExport=" + mvarhLeadingExport + "]";
	}
	
}
