package com.ht.beans;

import java.math.BigDecimal;

public class BillDetails {

	private int id;
	private String billNo;
	private String invoiceNo;
	private int meterReadingId;
	private int investorId;
	private int consumptionId;
	private int consumptionBifurcationId;
	private String meterNo;
	private String readingDate;
	private String billGenerationDate;
	private BigDecimal totalKWH;
	private BigDecimal totalRKVH;
	private BigDecimal kwhRate;
	private BigDecimal rkvhRate;
	private BigDecimal activeAmount;
	private BigDecimal reactiveAmount;
	private BigDecimal totalAmount;
	private BigDecimal totalAmountRoundOff;
	private String totalAmountInWords;
	private int plantId;
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
	 * @return the billNo
	 */
	public String getBillNo() {
		return billNo;
	}
	/**
	 * @param billNo the billNo to set
	 */
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	/**
	 * @return the invoiceNo
	 */
	public String getInvoiceNo() {
		return invoiceNo;
	}
	/**
	 * @param invoiceNo the invoiceNo to set
	 */
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	/**
	 * @return the meterReadingId
	 */
	public int getMeterReadingId() {
		return meterReadingId;
	}
	/**
	 * @param meterReadingId the meterReadingId to set
	 */
	public void setMeterReadingId(int meterReadingId) {
		this.meterReadingId = meterReadingId;
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
	 * @return the consumptionId
	 */
	public int getConsumptionId() {
		return consumptionId;
	}
	/**
	 * @param consumptionId the consumptionId to set
	 */
	public void setConsumptionId(int consumptionId) {
		this.consumptionId = consumptionId;
	}
	/**
	 * @return the consumptionBifurcationId
	 */
	public int getConsumptionBifurcationId() {
		return consumptionBifurcationId;
	}
	/**
	 * @param consumptionBifurcationId the consumptionBifurcationId to set
	 */
	public void setConsumptionBifurcationId(int consumptionBifurcationId) {
		this.consumptionBifurcationId = consumptionBifurcationId;
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
	 * @return the billGenerationDate
	 */
	public String getBillGenerationDate() {
		return billGenerationDate;
	}
	/**
	 * @param billGenerationDate the billGenerationDate to set
	 */
	public void setBillGenerationDate(String billGenerationDate) {
		this.billGenerationDate = billGenerationDate;
	}
	/**
	 * @return the totalKWH
	 */
	public BigDecimal getTotalKWH() {
		return totalKWH;
	}
	/**
	 * @param totalKWH the totalKWH to set
	 */
	public void setTotalKWH(BigDecimal totalKWH) {
		this.totalKWH = totalKWH;
	}
	/**
	 * @return the totalRKVH
	 */
	public BigDecimal getTotalRKVH() {
		return totalRKVH;
	}
	/**
	 * @param totalRKVH the totalRKVH to set
	 */
	public void setTotalRKVH(BigDecimal totalRKVH) {
		this.totalRKVH = totalRKVH;
	}
	/**
	 * @return the kwhRate
	 */
	public BigDecimal getKwhRate() {
		return kwhRate;
	}
	/**
	 * @param kwhRate the kwhRate to set
	 */
	public void setKwhRate(BigDecimal kwhRate) {
		this.kwhRate = kwhRate;
	}
	/**
	 * @return the rkvhRate
	 */
	public BigDecimal getRkvhRate() {
		return rkvhRate;
	}
	/**
	 * @param rkvhRate the rkvhRate to set
	 */
	public void setRkvhRate(BigDecimal rkvhRate) {
		this.rkvhRate = rkvhRate;
	}
	/**
	 * @return the activeAmount
	 */
	public BigDecimal getActiveAmount() {
		return activeAmount;
	}
	/**
	 * @param activeAmount the activeAmount to set
	 */
	public void setActiveAmount(BigDecimal activeAmount) {
		this.activeAmount = activeAmount;
	}
	/**
	 * @return the reactiveAmount
	 */
	public BigDecimal getReactiveAmount() {
		return reactiveAmount;
	}
	/**
	 * @param reactiveAmount the reactiveAmount to set
	 */
	public void setReactiveAmount(BigDecimal reactiveAmount) {
		this.reactiveAmount = reactiveAmount;
	}
	/**
	 * @return the totalAmount
	 */
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	/**
	 * @return the totalAmountRoundOff
	 */
	public BigDecimal getTotalAmountRoundOff() {
		return totalAmountRoundOff;
	}
	/**
	 * @param totalAmountRoundOff the totalAmountRoundOff to set
	 */
	public void setTotalAmountRoundOff(BigDecimal totalAmountRoundOff) {
		this.totalAmountRoundOff = totalAmountRoundOff;
	}
	/**
	 * @return the totalAmountInWords
	 */
	public String getTotalAmountInWords() {
		return totalAmountInWords;
	}
	/**
	 * @param totalAmountInWords the totalAmountInWords to set
	 */
	public void setTotalAmountInWords(String totalAmountInWords) {
		this.totalAmountInWords = totalAmountInWords;
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
	 * @param billNo
	 * @param invoiceNo
	 * @param meterReadingId
	 * @param investorId
	 * @param consumptionId
	 * @param consumptionBifurcationId
	 * @param meterNo
	 * @param readingDate
	 * @param billGenerationDate
	 * @param totalKWH
	 * @param totalRKVH
	 * @param kwhRate
	 * @param rkvhRate
	 * @param activeAmount
	 * @param reactiveAmount
	 * @param totalAmount
	 * @param totalAmountRoundOff
	 * @param totalAmountInWords
	 * @param plantId
	 * @param adjustment
	 */
	public BillDetails(int id, String billNo, String invoiceNo,
			int meterReadingId, int investorId, int consumptionId,
			int consumptionBifurcationId, String meterNo, String readingDate,
			String billGenerationDate, BigDecimal totalKWH,
			BigDecimal totalRKVH, BigDecimal kwhRate, BigDecimal rkvhRate,
			BigDecimal activeAmount, BigDecimal reactiveAmount,
			BigDecimal totalAmount, BigDecimal totalAmountRoundOff,
			String totalAmountInWords, int plantId, BigDecimal adjustment) {
		this.id = id;
		this.billNo = billNo;
		this.invoiceNo = invoiceNo;
		this.meterReadingId = meterReadingId;
		this.investorId = investorId;
		this.consumptionId = consumptionId;
		this.consumptionBifurcationId = consumptionBifurcationId;
		this.meterNo = meterNo;
		this.readingDate = readingDate;
		this.billGenerationDate = billGenerationDate;
		this.totalKWH = totalKWH;
		this.totalRKVH = totalRKVH;
		this.kwhRate = kwhRate;
		this.rkvhRate = rkvhRate;
		this.activeAmount = activeAmount;
		this.reactiveAmount = reactiveAmount;
		this.totalAmount = totalAmount;
		this.totalAmountRoundOff = totalAmountRoundOff;
		this.totalAmountInWords = totalAmountInWords;
		this.plantId = plantId;
		this.adjustment = adjustment;
	}
	/**
	 * @param billNo
	 * @param invoiceNo
	 * @param meterReadingId
	 * @param investorId
	 * @param consumptionId
	 * @param consumptionBifurcationId
	 * @param meterNo
	 * @param readingDate
	 * @param billGenerationDate
	 * @param totalKWH
	 * @param totalRKVH
	 * @param kwhRate
	 * @param rkvhRate
	 * @param activeAmount
	 * @param reactiveAmount
	 * @param totalAmount
	 * @param totalAmountRoundOff
	 * @param totalAmountInWords
	 * @param plantId
	 * @param adjustment
	 */
	public BillDetails(String billNo, String invoiceNo, int meterReadingId,
			int investorId, int consumptionId, int consumptionBifurcationId,
			String meterNo, String readingDate, String billGenerationDate,
			BigDecimal totalKWH, BigDecimal totalRKVH, BigDecimal kwhRate,
			BigDecimal rkvhRate, BigDecimal activeAmount,
			BigDecimal reactiveAmount, BigDecimal totalAmount,
			BigDecimal totalAmountRoundOff, String totalAmountInWords,
			int plantId, BigDecimal adjustment) {
		this.billNo = billNo;
		this.invoiceNo = invoiceNo;
		this.meterReadingId = meterReadingId;
		this.investorId = investorId;
		this.consumptionId = consumptionId;
		this.consumptionBifurcationId = consumptionBifurcationId;
		this.meterNo = meterNo;
		this.readingDate = readingDate;
		this.billGenerationDate = billGenerationDate;
		this.totalKWH = totalKWH;
		this.totalRKVH = totalRKVH;
		this.kwhRate = kwhRate;
		this.rkvhRate = rkvhRate;
		this.activeAmount = activeAmount;
		this.reactiveAmount = reactiveAmount;
		this.totalAmount = totalAmount;
		this.totalAmountRoundOff = totalAmountRoundOff;
		this.totalAmountInWords = totalAmountInWords;
		this.plantId = plantId;
		this.adjustment = adjustment;
	}
	/**
	 * Default Constructor
	 */
	public BillDetails() {
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BillDetails [id=" + id + ", billNo=" + billNo + ", invoiceNo="
				+ invoiceNo + ", meterReadingId=" + meterReadingId
				+ ", investorId=" + investorId + ", consumptionId="
				+ consumptionId + ", consumptionBifurcationId="
				+ consumptionBifurcationId + ", meterNo=" + meterNo
				+ ", readingDate=" + readingDate + ", billGenerationDate="
				+ billGenerationDate + ", totalKWH=" + totalKWH
				+ ", totalRKVH=" + totalRKVH + ", kwhRate=" + kwhRate
				+ ", rkvhRate=" + rkvhRate + ", activeAmount=" + activeAmount
				+ ", reactiveAmount=" + reactiveAmount + ", totalAmount="
				+ totalAmount + ", totalAmountRoundOff=" + totalAmountRoundOff
				+ ", totalAmountInWords=" + totalAmountInWords + ", plantId="
				+ plantId + ", adjustment=" + adjustment + "]";
	}
	
	
}
