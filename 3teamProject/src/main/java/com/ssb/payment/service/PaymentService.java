package com.ssb.payment.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.text.html.parser.Entity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ssb.order.db.OrdersDTO;
import com.ssb.payment.db.PaymentDTO;
import com.ssb.payment.exception.PayFailedValidationException;
import com.ssb.payment.exception.PriceValidationException;
import com.ssb.payment.vo.PortOneRefundResult;
import com.ssb.payment.vo.StoreInfo;


public class PaymentService {
	
	public static void validationCheck(PaymentDTO payment, OrdersDTO ordersDTO) throws PriceValidationException, PayFailedValidationException {
		
		if(payment.getMerchantUid()!=ordersDTO.getId()) {
		}else if(payment.isSuccess()==false){
			throw new PayFailedValidationException("결제가 정상적으로 처리되지 않았습니다.");
			
		}else if(payment.getStatus().equals("paid") &&payment.getPaidAmount()!= ordersDTO.getTotal_price()) {
			throw new PriceValidationException("결제된 금액에 문제가 발생하였습니다");
			
		}else if(payment.getStatus().equals("paid")&& ordersDTO.getTotal_price() == payment.getPaidAmount()) {
		}
	}
	
	
	//환불 및 결제 정보 조회를 위한 토큰 발행코드
	public String getTokenV3(StoreInfo storeInfo) {
			
		final String tokenURL = "https://api.iamport.kr/users/getToken";
		String token = "";

			
		try {
			HttpClient client = HttpClientBuilder.create().build();
			JsonObject myInfoJson = new JsonObject();
			myInfoJson.addProperty("imp_key", storeInfo.getImpkey());
			myInfoJson.addProperty("imp_secret", storeInfo.getSecretkey());
				
				
			StringEntity entity = new StringEntity(myInfoJson.toString(), ContentType.APPLICATION_JSON);

			
			HttpPost post = new HttpPost(tokenURL);
			post.setEntity(entity);
				
				 // HTTP 요청 보내기
			HttpResponse httpRes = client.execute(post);
			HttpEntity httpEntity = httpRes.getEntity();
			
			String tokenJson = EntityUtils.toString(httpEntity);
			
			Gson gson = new Gson();
			String response = gson.fromJson(tokenJson,Map.class).get("response").toString();
			token = gson.fromJson(response, Map.class).get("access_token").toString();
			

			} catch (IOException e) {
				   
				    e.printStackTrace();
			}

			
//		System.out.println("발급받은 토큰의 값은 : " +token);
			
			
			
		return token;
		
		}
	
	
	
	public PortOneRefundResult refundPayment(String token, long merchantUid) {
		
		final String refundURL = "https://api.iamport.kr/payments/cancel";
		String result ="";
		PortOneRefundResult refundResult = PortOneRefundResult.NOPASS;
		
		try {
			HttpClient client = HttpClientBuilder.create().build();
			JsonObject myInfoJson = new JsonObject();
			myInfoJson.addProperty("Authorization",token);
			myInfoJson.addProperty("merchant_uid", merchantUid);
				
				
			StringEntity entity = new StringEntity(myInfoJson.toString(), ContentType.APPLICATION_JSON);

			
			
			HttpPost post = new HttpPost(refundURL);
			post.setHeader("Authorization",token);
			
			post.setEntity(entity);
				
				 // HTTP 요청 보내기
			HttpResponse httpRes = client.execute(post);
			HttpEntity httpEntity = httpRes.getEntity();
			
			String response = EntityUtils.toString(httpEntity);
			
			
			Gson gson = new Gson();
			result = gson.fromJson(response,Map.class).get("response").toString();

			} catch (IOException e) {
				   
				    e.printStackTrace();
			}
		

			if(result.equals("null")) {
				refundResult = PortOneRefundResult.NOPASS;
			}else {
				refundResult = PortOneRefundResult.PASS;
			}
		

		return refundResult;
	}
	
	
	public PortOneRefundResult refundPaymentV2(String token, long merchantUid) {
		
		try {
			URL refundURL = new URL("https://api.iamport.kr/payments/cancel");
		
        	HttpsURLConnection conn = (HttpsURLConnection) refundURL.openConnection();
        
        	// 요청 방식을 POST로 설정
			conn.setRequestMethod("POST");
 
			// 요청의 Content-Type, Accept, Authorization 헤더 설정
			conn.setRequestProperty("Content-type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Authorization", token);
 
			// 해당 연결을 출력 스트림(요청)으로 사용
			conn.setDoOutput(true);
 
			// JSON 객체에 해당 API가 필요로하는 데이터 추가.
			JsonObject json = new JsonObject();
			json.addProperty("merchant_uid", merchantUid);
//			json.addProperty("reason", reason);
 
			// 출력 스트림으로 해당 conn에 요청
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
				bw.write(json.toString());
				bw.flush();
				bw.close();
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				br.close();
				conn.disconnect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		return null;
	}
 

}
