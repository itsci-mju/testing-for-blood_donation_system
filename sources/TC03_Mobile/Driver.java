package TC03_Mobile;

import java.util.NoSuchElementException;

public class Driver {
  
	
	private Utility util = new Utility();
	
	public Utility getUtility() {
		return util;
	}

	public String keyword_executor(String vKeyword, String vIP1, String vIP2) throws Exception {
		
		String flag = "false";
	
		try {
		if (vKeyword.equals("application_open")){		   
		    util.application_open(getIP(vIP1));
		    flag = "True";
		    return "pass";
		}
		if (vKeyword.equals("input_idcard")){
			util.input_idcard(getIP(vIP1), getIP(vIP2));
			flag = "True";
		    return "pass";
		}
		if (vKeyword.equals("input_password")){
			util.input_password(getIP(vIP1), getIP(vIP2));
			flag = "True";
		    return "pass";
		}
//		try {
		if (vKeyword.equals("get_text")){
			String txt = util.get_text(getIP(vIP1),getIP(vIP2));
			flag = "True";
		    return txt;
		}
//		}catch (Exception e) {
//			if (vKeyword.equals("get_text")){
//				String txt = util.get_text(getIP(vIP2));
//				flag = "True";
//			    return txt;
//			}
//		}
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
    	}
		catch (NoSuchElementException e){
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
		if (vIP.equals("vIdCard")){
			vIP = StartUp.vIdCard;
		}
		if (vIP.equals("vPassword")){
			vIP = StartUp.vPassword;
		}
	  return vIP;
	}

	public void getData(int k) {
		StartUp.vOpenApp = StartUp.xTDdata[k][2];
		System.out.println(StartUp.xTDdata[k][2]);
		StartUp.vIdCard = StartUp.xTDdata[k][4];
		StartUp.vPassword = StartUp.xTDdata[k][5];
	}
}