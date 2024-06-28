package pe.com.bn.msds.test;



import org.apache.axis.encoding.Base64;

import pe.com.bn.comp.cryto.service.BNClaveSegura;

public class TestClavesegurades {

	public static void main(String[] args) {
		
		try {
			String keyPath = "D:/indra_csanchez/2022-M0552/COMP-clavesegurades/clavesegurades.key";
			String semilla = "semillaMGEC";
			
			byte[] clave = BNClaveSegura.encrypt(keyPath, semilla);

			
			System.out.println(Base64.encode(clave));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
