package com.ssb.payment.db;

/**
 * @author Nell
 *
 */
public class PaymentDTO {
	
	private String impUid;
	private long merchantUid;
	private int paidAmount;
	private String status;
	private String pgProvider;
	private String paidMethod;
	private boolean success;
	
	
	
	public PaymentDTO() {
		super();
	}


	public static PaymentDTO createPayment(String impUid, long merchantUid, int paidAmount, String status, String paidMethod,
			boolean success, String pgProvider) {
		PaymentDTO paymentDTO = new PaymentDTO();
		paymentDTO.impUid = impUid;
		paymentDTO.merchantUid = merchantUid;
		paymentDTO.paidAmount = paidAmount;
		paymentDTO.status = status;
		paymentDTO.paidMethod = paidMethod;
		paymentDTO.success = success;
		paymentDTO.pgProvider = pgProvider;
		return paymentDTO;
	}


	public String getImpUid() {
		return impUid;
	}


	


	public long getMerchantUid() {
		return merchantUid;
	}


	public int getPaidAmount() {
		return paidAmount;
	}


	public String getStatus() {
		return status;
	}
	
	


	public String getPaidMethod() {
		return paidMethod;
	}


	public boolean isSuccess() {
		return success;
	}

	

	public void setImpUid(String impUid) {
		this.impUid = impUid;
	}


	public void setMerchantUid(long merchantUid) {
		this.merchantUid = merchantUid;
	}


	public void setPaidAmount(int paidAmount) {
		this.paidAmount = paidAmount;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public void setPaidMethod(String paidMethod) {
		this.paidMethod = paidMethod;
	}


	public void setSuccess(boolean success) {
		this.success = success;
	}

	
	

	public String getPgProvider() {
		return pgProvider;
	}


	public void setPgProvider(String pgProvider) {
		this.pgProvider = pgProvider;
	}


	@Override
	public String toString() {
		return "PaymentDTO [impUid=" + impUid + ", merchantUid=" + merchantUid + ", paidAmount=" + paidAmount
				+ ", status=" + status + ", paidMethod=" + paidMethod + ", success=" + success + "]";
	}
	
	
	
	
	

}
