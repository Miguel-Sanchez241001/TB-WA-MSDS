package pe.com.bn.msds.model;
import java.util.Calendar;
import java.util.GregorianCalendar;







public class BodyFactibilidad extends WSData{
	
public void init(){
		
		this.campos = new Campo[90];
		try {
			
			int num = -1;
	
	this.campos[++num]	    =   new Campo("DFH-CUSUARIO",   Campo.CHARACTER, 4);
	this.campos[++num]	    =   new Campo("DFH-CLAVE",   Campo.CHARACTER, 8);
	this.campos[++num]	    =   new Campo("DFH-PRINTER",   Campo.CHARACTER, 4);
	this.campos[++num]	    =   new Campo("DFH-FDIA-AA",   Campo.CHARACTER, 4);
	this.campos[++num]	    =   new Campo("DFH-FDIA-MM",   Campo.CHARACTER, 2);
	this.campos[++num]	    =   new Campo("DFH-FDIA-DD",   Campo.CHARACTER, 2);	
	this.campos[++num]	    =   new Campo("DFH-CAGENCIA",   Campo.CHARACTER, 4);
	this.campos[++num]	    =   new Campo("DFH-AAGENCIA",   Campo.CHARACTER, 15);
	this.campos[++num]	    =   new Campo("DFH-TDOCUM",   Campo.CHARACTER, 2);
	this.campos[++num]	    =   new Campo("DFH-NDOCUM",   Campo.CHARACTER, 15);
	this.campos[++num]	    =   new Campo("DFH-NCUOTAS",   Campo.CHARACTER, 3);	
	this.campos[++num]	    =   new Campo("DFH-PGRACIA",   Campo.CHARACTER, 1);
	this.campos[++num]	    =   new Campo("DFH-SCP",   Campo.CHARACTER, 1);
	this.campos[++num]	    =   new Campo("DFH-EECC",   Campo.CHARACTER, 1);
	this.campos[++num]	    =   new Campo("DFH-TSEGU",   Campo.CHARACTER, 1);  //0 , 1 , 9  
	this.campos[++num]	    =   new Campo("DFH-TDEBITO",   Campo.CHARACTER, 18);  //18
	this.campos[++num]	    =   new Campo("DFH-MDOCUM",   Campo.CHARACTER, 15);
	this.campos[++num]	    =   new Campo("DFH-MACLI",   Campo.CHARACTER, 70);
	this.campos[++num]	    =   new Campo("DFH-MTIPO",   Campo.CHARACTER, 2);
	this.campos[++num]	    =   new Campo("DFH-ATIPO",   Campo.CHARACTER, 30);
	this.campos[++num]	    =   new Campo("DFH-MAPT",   Campo.CHARACTER, 25);
	this.campos[++num]	    =   new Campo("DFH-MAMT",   Campo.CHARACTER, 25);
	this.campos[++num]	    =   new Campo("DFH-MN1T",   Campo.CHARACTER, 25);
	this.campos[++num]	    =   new Campo("DFH-MN2T",   Campo.CHARACTER, 25);
	this.campos[++num]	    =   new Campo("DFH-MCAT",   Campo.CHARACTER, 25);
	this.campos[++num]	    =   new Campo("DFH-MCADU",   Campo.CHARACTER, 6);
	this.campos[++num]	    =   new Campo("DFH-MFNAC",   Campo.CHARACTER, 10);
	this.campos[++num]	    =   new Campo("DFH-MSCLI",   Campo.CHARACTER, 1);
	this.campos[++num]	    =   new Campo("DFH-MCTAAE",   Campo.CHARACTER, 13);
	this.campos[++num]	    =   new Campo("DFH-MECLI",   Campo.CHARACTER, 10);
	this.campos[++num]	    =   new Campo("DFH-MDOMI",   Campo.CHARACTER, 66);
	this.campos[++num]	    =   new Campo("DFH-MMAIL",   Campo.CHARACTER, 50);
	this.campos[++num]	    =   new Campo("DFH-MTDOMI",   Campo.CHARACTER, 10);
	this.campos[++num]	    =   new Campo("DFH-MCELUL",   Campo.CHARACTER, 10);
	this.campos[++num]	    =   new Campo("DFH-MTLABO",   Campo.CHARACTER, 10);
	this.campos[++num]	    =   new Campo("DFH-MENTID",   Campo.CHARACTER, 27);
	this.campos[++num]	    =   new Campo("DFH-MFAPER",   Campo.CHARACTER, 10);
	this.campos[++num]	    =   new Campo("DFH-MCLABO",   Campo.CHARACTER, 13);
	this.campos[++num]	    =   new Campo("DFH-MFINGT",   Campo.CHARACTER, 10);
	this.campos[++num]	    =   new Campo("DFH-MSPROM",   Campo.CHARACTER, 15);
	
	this.campos[++num]	    =   new Campo("DFH-MQSBS",   Campo.CHARACTER, 10);
	this.campos[++num]	    =   new Campo("DFH-MCCH",   Campo.CHARACTER, 15);
	this.campos[++num]	    =   new Campo("DFH-MNSBS",   Campo.CHARACTER, 3);
	this.campos[++num]	    =   new Campo("DFH-MCPM",   Campo.CHARACTER, 15);
	this.campos[++num]	    =   new Campo("DFH-MCSBS",   Campo.CHARACTER, 10);
	this.campos[++num]	    =   new Campo("DFH-MLTC",   Campo.CHARACTER, 15);	
	this.campos[++num]	    =   new Campo("DFH-MRATIOS",   Campo.CHARACTER, 1);
	this.campos[++num]	    =   new Campo("DFH-MRATIO",   Campo.CHARACTER, 10);
	this.campos[++num]	    =   new Campo("DFH-MSPMANT",   Campo.CHARACTER, 15);
	this.campos[++num]	    =   new Campo("DFH-MSDESG",   Campo.CHARACTER, 9);
	this.campos[++num]	    =   new Campo("DFH-MSMAX",   Campo.CHARACTER, 15);
	this.campos[++num]	    =   new Campo("DFH-MSCP",   Campo.CHARACTER, 15);
	this.campos[++num]	    =   new Campo("DFH-MSDES",   Campo.CHARACTER, 15);
	this.campos[++num]	    =   new Campo("DFH-MSCUOTA",   Campo.CHARACTER, 15);
	this.campos[++num]	    =   new Campo("DFH-MABONO",   Campo.CHARACTER, 15);
	this.campos[++num]	    =   new Campo("DFH-MTEA",   Campo.CHARACTER, 10);
	this.campos[++num]	    =   new Campo("DFH-MDPAGO",   Campo.CHARACTER, 2);
	this.campos[++num]	    =   new Campo("DFH-MTCEA",   Campo.CHARACTER, 10);
	this.campos[++num]	    =   new Campo("DFH-MFVCTO",   Campo.CHARACTER, 13);
	this.campos[++num]	    =   new Campo("DFH-MCUBI",   Campo.CHARACTER, 6);
	this.campos[++num]	    =   new Campo("DFH-MACUBI",   Campo.CHARACTER, 60);
	this.campos[++num]	    =   new Campo("DFH-MSUEL6",   Campo.CHARACTER, 15);
	this.campos[++num]	    =   new Campo("DFH-MSUEL5",   Campo.CHARACTER, 15);
	this.campos[++num]	    =   new Campo("DFH-MSUEL4",   Campo.CHARACTER, 15);
	this.campos[++num]	    =   new Campo("DFH-MSUEL3",   Campo.CHARACTER, 15);
	this.campos[++num]	    =   new Campo("DFH-MSUEL2",   Campo.CHARACTER, 15);
	this.campos[++num]	    =   new Campo("DFH-MSUEL1",   Campo.CHARACTER, 15);
	this.campos[++num]	    =   new Campo("DFH-FDSBOLSO-CANCEL",   Campo.CHARACTER, 8);
	this.campos[++num]	    =   new Campo("DFH-SECTOR",   Campo.CHARACTER, 40);
	
	
	
	
	
	this.campos[++num]	    =   new Campo("DFH-FULT-OP",   Campo.CHARACTER, 10);
	
	this.campos[++num]	    =   new Campo("DFH-ABANCO1",   Campo.CHARACTER, 35);
	this.campos[++num]	    =   new Campo("DFH-TCREDI1",   Campo.CHARACTER, 35);
	this.campos[++num]	    =   new Campo("DFH-SDEUDA1",   Campo.CHARACTER, 15);
	
	this.campos[++num]	    =   new Campo("DFH-ABANCO2",   Campo.CHARACTER, 35);
	this.campos[++num]	    =   new Campo("DFH-TCREDI2",   Campo.CHARACTER, 35);
	this.campos[++num]	    =   new Campo("DFH-SDEUDA2",   Campo.CHARACTER, 15);
	
	this.campos[++num]	    =   new Campo("DFH-ABANCO3",   Campo.CHARACTER, 35);
	this.campos[++num]	    =   new Campo("DFH-TCREDI3",   Campo.CHARACTER, 35);
	this.campos[++num]	    =   new Campo("DFH-SDEUDA3",   Campo.CHARACTER, 15);
	 
	this.campos[++num]	    =   new Campo("DFH-ABANCO4",   Campo.CHARACTER, 35);
	this.campos[++num]	    =   new Campo("DFH-TCREDI4",   Campo.CHARACTER, 35);
	this.campos[++num]	    =   new Campo("DFH-SDEUDA4",   Campo.CHARACTER, 15);
	
	this.campos[++num]	    =   new Campo("DFH-ABANCO5",   Campo.CHARACTER, 35);
	this.campos[++num]	    =   new Campo("DFH-TCREDI5",   Campo.CHARACTER, 35);
	this.campos[++num]	    =   new Campo("DFH-SDEUDA5",   Campo.CHARACTER, 15);
   
	this.campos[++num]	    =   new Campo("DFH-ABANCO6",   Campo.CHARACTER,35);
	this.campos[++num]	    =   new Campo("DFH-TCREDI6",   Campo.CHARACTER, 35);
	this.campos[++num]	    =   new Campo("DFH-SDEUDA6",   Campo.CHARACTER, 15);
   
   
	
	
	this.campos[++num]	    =   new Campo("DFH-MSJ",   Campo.CHARACTER, 60);
	this.campos[++num]	    =   new Campo("DFH-CERROR",   Campo.CHARACTER, 4);
	


	
} catch (Exception e) {
	e.printStackTrace();
}
}

public void cargarData (
		String tipo, String dni,String plazo, String pg, String cp, String eecc, String tsegu, String macli
		) throws Exception {

			
	int num = -1;
	
	init();
	
	
	
	this.campos[++num].setString("31CC");
	this.campos[++num].setString("        ");
	this.campos[++num].setString("    ");
	
	 Calendar fecha = new GregorianCalendar();
     
     int ano = fecha.get(Calendar.YEAR);
     int mes = (fecha.get(Calendar.MONTH))+1;
     int dia = fecha.get(Calendar.DAY_OF_MONTH); 
     
     String anoo= String.valueOf(ano);
     String mess= String.valueOf(mes);
     String diaa= String.valueOf(dia);
     
     if(mess.length()==1){
     	mess = "0"+mess;
     }
     if(diaa.length()==1){
     	diaa="0"+diaa;
     }

 	
     
	this.campos[++num].setString(anoo);
	this.campos[++num].setString(mess);
	this.campos[++num].setString(diaa);
	this.campos[++num].setString("0027");
	this.campos[++num].setString("CALLCENTER     ");	
	this.campos[++num].setString(tipo);
	this.campos[++num].setString(dni);	
	this.campos[++num].setString(plazo);
	this.campos[++num].setString(pg);
	this.campos[++num].setString(cp);
	this.campos[++num].setString(eecc);
	this.campos[++num].setString(tsegu);
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString(macli);
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
}



public void cargarDataGrabar (
		String tdocum1,String ndocum1,String ncuotas, String pgracia,String scp1, String eecc, String tsegu, String macli, String solicitud
		) throws Exception {

			
	int num = -1;
	
	init();
	
	
	
	this.campos[++num].setString("31CC");
	this.campos[++num].setString("        ");
	this.campos[++num].setString("    ");
	
	 Calendar fecha = new GregorianCalendar();
    
    int ano = fecha.get(Calendar.YEAR);
    int mes = (fecha.get(Calendar.MONTH))+1;
    int dia = fecha.get(Calendar.DAY_OF_MONTH); 
    
    String anoo= String.valueOf(ano);
    String mess= String.valueOf(mes);
    String diaa= String.valueOf(dia);
    
    if(mess.length()==1){
    	mess = "0"+mess;
    }
    if(diaa.length()==1){
    	diaa="0"+diaa;
    }

	
    
	this.campos[++num].setString(anoo);
	this.campos[++num].setString(mess);
	this.campos[++num].setString(diaa);
	this.campos[++num].setString("0027");
	this.campos[++num].setString("CALLCENTER     ");
	
	this.campos[++num].setString(tdocum1);
	this.campos[++num].setString(ndocum1);	
	this.campos[++num].setString(ncuotas);
	this.campos[++num].setString(pgracia);
	this.campos[++num].setString(scp1);
	this.campos[++num].setString(eecc);
	this.campos[++num].setString(tsegu);
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString(macli);
	
	
	
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString(solicitud);
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");
	this.campos[++num].setString("");

}





public void FillBoby(String bodyInPut) throws Exception {
	init();
	super.FillBoby(bodyInPut);


}




}

