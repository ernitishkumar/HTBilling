package com.ht.beans;

import java.math.BigDecimal;
import java.util.ArrayList;

public class BillDetailsView {

	private int id;
	
	private String billNo;
	
	private String invoiceNo;
	
	private MeterReading meterReadings;
	
	private Investor investor;
	
	private Consumption consumption;
	
	private InvestorConsumptionView investorConsumptionView;
	
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
	
	private BigDecimal totalAmountRoundoff;
	
	private String totalAmountInWords;

	private String particulars;
	
	private Plant plant;
	
	private ArrayList<Machine> machines;
	
	private BillDetails billDetails;

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
	 * @return the meterReadings
	 */
	public MeterReading getMeterReadings() {
		return meterReadings;
	}

	/**
	 * @param meterReadings the meterReadings to set
	 */
	public void setMeterReadings(MeterReading meterReadings) {
		this.meterReadings = meterReadings;
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
	 * @return the consumption
	 */
	public Consumption getConsumption() {
		return consumption;
	}

	/**
	 * @param consumption the consumption to set
	 */
	public void setConsumption(Consumption consumption) {
		this.consumption = consumption;
	}

	/**
	 * @return the investorConsumptionView
	 */
	public InvestorConsumptionView getInvestorConsumptionView() {
		return investorConsumptionView;
	}

	/**
	 * @param investorConsumptionView the investorConsumptionView to set
	 */
	public void setInvestorConsumptionView(
			InvestorConsumptionView investorConsumptionView) {
		this.investorConsumptionView = investorConsumptionView;
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
	 * @return the totalAmountRoundoff
	 */
	public BigDecimal getTotalAmountRoundoff() {
		return totalAmountRoundoff;
	}

	/**
	 * @param totalAmountRoundoff the totalAmountRoundoff to set
	 */
	public void setTotalAmountRoundoff(BigDecimal totalAmountRoundoff) {
		this.totalAmountRoundoff = totalAmountRoundoff;
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
	 * @return the machines
	 */
	public ArrayList<Machine> getMachines() {
		return machines;
	}

	/**
	 * @param machines the machines to set
	 */
	public void setMachines(ArrayList<Machine> machines) {
		this.machines = machines;
	}
	
	

	/**
	 * @return the billDetails
	 */
	public BillDetails getBillDetails() {
		return billDetails;
	}

	/**
	 * @param billDetails the billDetails to set
	 */
	public void setBillDetails(BillDetails billDetails) {
		this.billDetails = billDetails;
	}

	
	/**
	 * @param id
	 * @param billNo
	 * @param invoiceNo
	 * @param meterReadings
	 * @param investor
	 * @param consumption
	 * @param investorConsumptionView
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
	 * @param totalAmountRoundoff
	 * @param totalAmountInWords
	 * @param particulars
	 * @param plant
	 * @param machines
	 * @param billDetails
	 */
	public BillDetailsView(int id, String billNo, String invoiceNo,
			MeterReading meterReadings, Investor investor,
			Consumption consumption,
			InvestorConsumptionView investorConsumptionView, String meterNo,
			String readingDate, String billGenerationDate, BigDecimal totalKWH,
			BigDecimal totalRKVH, BigDecimal kwhRate, BigDecimal rkvhRate,
			BigDecimal activeAmount, BigDecimal reactiveAmount,
			BigDecimal totalAmount, BigDecimal totalAmountRoundoff,
			String totalAmountInWords, String particulars, Plant plant,
			ArrayList<Machine> machines, BillDetails billDetails) {
		this.id = id;
		this.billNo = billNo;
		this.invoiceNo = invoiceNo;
		this.meterReadings = meterReadings;
		this.investor = investor;
		this.consumption = consumption;
		this.investorConsumptionView = investorConsumptionView;
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
		this.totalAmountRoundoff = totalAmountRoundoff;
		this.totalAmountInWords = totalAmountInWords;
		this.particulars = particulars;
		this.plant = plant;
		this.machines = machines;
		this.billDetails = billDetails;
	}
	
	/**
	 * @param billNo
	 * @param invoiceNo
	 * @param meterReadings
	 * @param investor
	 * @param consumption
	 * @param investorConsumptionView
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
	 * @param totalAmountRoundoff
	 * @param totalAmountInWords
	 * @param particulars
	 * @param plant
	 * @param machines
	 * @param billDetails
	 */
	public BillDetailsView(String billNo, String invoiceNo,
			MeterReading meterReadings, Investor investor,
			Consumption consumption,
			InvestorConsumptionView investorConsumptionView, String meterNo,
			String readingDate, String billGenerationDate, BigDecimal totalKWH,
			BigDecimal totalRKVH, BigDecimal kwhRate, BigDecimal rkvhRate,
			BigDecimal activeAmount, BigDecimal reactiveAmount,
			BigDecimal totalAmount, BigDecimal totalAmountRoundoff,
			String totalAmountInWords, String particulars, Plant plant,
			ArrayList<Machine> machines, BillDetails billDetails) {
		this.billNo = billNo;
		this.invoiceNo = invoiceNo;
		this.meterReadings = meterReadings;
		this.investor = investor;
		this.consumption = consumption;
		this.investorConsumptionView = investorConsumptionView;
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
		this.totalAmountRoundoff = totalAmountRoundoff;
		this.totalAmountInWords = totalAmountInWords;
		this.particulars = particulars;
		this.plant = plant;
		this.machines = machines;
		this.billDetails = billDetails;
	}

	/**
	 * Default Constructor
	 */
	public BillDetailsView() {
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BillDetailsView [id=" + id + ", billNo=" + billNo
				+ ", invoiceNo=" + invoiceNo + ", meterReadings="
				+ meterReadings + ", investor=" + investor + ", consumption="
				+ consumption + ", investorConsumptionView="
				+ investorConsumptionView + ", meterNo=" + meterNo
				+ ", readingDate=" + readingDate + ", billGenerationDate="
				+ billGenerationDate + ", totalKWH=" + totalKWH
				+ ", totalRKVH=" + totalRKVH + ", kwhRate=" + kwhRate
				+ ", rkvhRate=" + rkvhRate + ", activeAmount=" + activeAmount
				+ ", reactiveAmount=" + reactiveAmount + ", totalAmount="
				+ totalAmount + ", totalAmountRoundoff=" + totalAmountRoundoff
				+ ", totalAmountInWords=" + totalAmountInWords
				+ ", particulars=" + particulars + ", plant=" + plant
				+ ", machines=" + machines + ", billDetails=" + billDetails
				+ "]";
	}
	
}
