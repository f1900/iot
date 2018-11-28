package com.mit.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculate {
	public static double add(double a,double b){
		return BigDecimal.valueOf(a).add(BigDecimal.valueOf(b)).doubleValue();
	}
	public static double sub(double a,double b){
		return BigDecimal.valueOf(a).subtract(BigDecimal.valueOf(b)).doubleValue();
	}
	public static double mul(double a,double b){
		return BigDecimal.valueOf(a).multiply(BigDecimal.valueOf(b)).doubleValue();
	}
	public static double div(double a,double b){
		return BigDecimal.valueOf(a).divide(BigDecimal.valueOf(b),6,RoundingMode.HALF_UP).doubleValue();
	}

	public static void main(String[] args) {
//		long i=1;
		System.out.println(add(-2, -3));
		System.out.println(sub(2, -3));
		System.out.println(mul(2, 3));
		System.out.println(div(5, 3));
	}
}
