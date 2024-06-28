package pe.com.bn.msds.test;

import java.math.BigDecimal;

public class TestBigDecimal {

	public static void main(String[] args) {

		BigDecimal d = new BigDecimal(0);
		
		d = d.add(new BigDecimal("2999"));
		
		System.out.println(d.setScale(2));
		
		System.out.println("123456789F".substring(0, 9));
		
		System.out.println("2562-SECCIÓN SISTEMAS ADMINIST".toString().length());
		
	}

}
