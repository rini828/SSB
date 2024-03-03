package com.ssb.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// 인터페이스는 자생력이 없다. (객체 생성 불가능)
// => 추상메서드를 포함하기 때문
// => 추상메서드는 body가 없어서 실행구문이 없음 (실행 불가능)
 
// 상수
// 추상메서드

public interface Action {
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}