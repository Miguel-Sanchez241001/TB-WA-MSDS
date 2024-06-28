package pe.com.bn.msds.common;

import org.springframework.ui.ModelMap;

public class View {

	public static String returnJsp(ModelMap model,String strUrl){
		
		String nav="layout/main-layout";
		
		if(strUrl!=null){
			if(!(strUrl.indexOf("/Ajax")==-1)){  
				nav="layout/white-layout";        
				strUrl=strUrl.replace("/Ajax", "");
				model.addAttribute("page",strUrl); 
				return nav; 
			}
			if(!(strUrl.indexOf("/Popup")==-1)){ 
				nav="layout/popup-layout";        
				strUrl=strUrl.replace("/Popup", "");
				model.addAttribute("page",strUrl); 
				return nav; 
			}		
		}
		model.addAttribute("page",strUrl);
		return nav;
	}
	
}
