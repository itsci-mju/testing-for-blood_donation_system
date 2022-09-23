package TC01_Mobile;

import java.util.NoSuchElementException;

public class Driver {
  
	
	private Utility util = new Utility();
	
	public Utility getUtility() {
		return util;
	}

	public String keyword_executor(String vKeyword, String vIP1, String vIP2, String vIP3) throws Exception {
		
		String flag = "false";
	
		try {
		if (vKeyword.equals("application_open")){		   
		    util.application_open(getIP(vIP1));
		    flag = "True";
		    return "pass";
		}
		if (vKeyword.equals("type_search")){
			util.type_search(getIP(vIP1), getIP(vIP2));
			flag = "True";
		    return "pass";
		}
		if (vKeyword.equals("keyword_search")){
			util.keyword_search(getIP(vIP1), getIP(vIP2));
			flag = "True";
		    return "pass";
		}
		if (vKeyword.equals("get_text")){
			String type_search = StartUp.vTypeSearch;
			System.out.println("vTypeSearch : " + type_search);
			
			if(type_search.equals("วันที่")||type_search.equals("")) {
				try {
					String txt = util.get_text(getIP(vIP1));
					flag = "True";
					return txt;
				
				}catch(Exception e) {
					String txt = util.get_text(getIP(vIP3));
					flag = "True";
					return txt;
					}
			}
			if(type_search.equals("สถานที่")) {
				try {
					
					String txt = util.get_text(getIP(vIP2));
					flag = "True";
					return txt;

				}catch(Exception e) {
					String txt = util.get_text(getIP(vIP3));
					flag = "True";
					return txt;
				}
			}
		}
		if (vKeyword.equals("button_click")){
			util.button_click(getIP(vIP1));
			flag = "True";
		    return "pass";
		   
		}
		if (vKeyword.equals("application_close")){		   
		    util.application_close();
		    flag = "True";
		    return "pass";
		}
		if (flag.equals("false")){
			System.out.println("Keyword is missing " + vKeyword);
			StartUp.vError = "Error";		
			return "False-Keyword Missing";
		  }
    	}catch (NoSuchElementException e){
			System.out.println("Error is " + e);
			StartUp.vError = String.valueOf(e);
			
			return "Fail";
		}
		return "Unknown Keyword";
	}
	
	public String getIP(String vIP){
		
		if (vIP.equals("vOpenApp")){
			vIP = StartUp.vOpenApp;
		}
		if (vIP.equals("vTypeSearch")){
			vIP = StartUp.vTypeSearch;
		}
		if (vIP.equals("vKeywordSearch")){
			vIP = StartUp.vKeywordSearch;
		}
	  return vIP;
	}

	public void getData(int k) {
		StartUp.vOpenApp = StartUp.xTDdata[k][2];
		System.out.println(StartUp.xTDdata[k][2]);
		StartUp.vTypeSearch = StartUp.xTDdata[k][4];
		StartUp.vKeywordSearch = StartUp.xTDdata[k][5];
	}
}