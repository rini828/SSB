package com.ssb.payment.vo;

public enum PaymentValidationCheckState {
	OPEN, //검증 시작
	STANDBY, //결제 대기상태 (가상계좌 연결을 대비)
	PRICEDIFF, // 결제금액과 주문서와 금액 차이 발생
	PAYMENTYET, // 몰라
	FAILED, // 결제 상태 실패
	SUCCESS // 성공

}
