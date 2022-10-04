package TC02_Mobile;

import java.util.NoSuchElementException;

public class Driver {
  
	private Utility util = new Utility();
	
	public Utility getUtility() {
		return util;
	}

	public String keyword_executor(String vKeyword, String vIP1, String vIP2, String vIP3, String vIP4, String vIP5, String vIP6) throws Exception {
		
		String flag = "false";
	
		try {
		if (vKeyword.equals("application_open")){		   
		    util.application_open(getIP(vIP1));
		    flag = "True";
		    return "pass";
		}
		if (vKeyword.equals("type_gender")){
			util.type_gender(getIP(vIP1), getIP(vIP2));
			flag = "True";
		    return "pass";
		}
		if (vKeyword.equals("input_idcard")){
			util.input_idcard(getIP(vIP1), getIP(vIP2));
			flag = "True";
		    return "pass";
		}
		if (vKeyword.equals("input_firstname")){
			util.input_firstname(getIP(vIP1), getIP(vIP2));
			flag = "True";
		    return "pass";
		}if (vKeyword.equals("input_lastname")){
			util.input_lastname(getIP(vIP1), getIP(vIP2));
			flag = "True";
		    return "pass";
		}if (vKeyword.equals("input_birthday")){
			util.input_birthday(getIP(vIP1), getIP(vIP2), getIP(vIP3), getIP(vIP4), getIP(vIP5), getIP(vIP6));
			flag = "True";
		    return "pass";
		}if (vKeyword.equals("input_address")){
			util.input_address(getIP(vIP1), getIP(vIP2));
			flag = "True";
		    return "pass";
		}if (vKeyword.equals("input_tel")){
			util.input_tel(getIP(vIP1), getIP(vIP2));
			flag = "True";
		    return "pass";
		}if (vKeyword.equals("input_career")){
			util.input_career(getIP(vIP1), getIP(vIP2));
			flag = "True";
		    return "pass";
		}if (vKeyword.equals("input_email")){
			util.input_email(getIP(vIP1), getIP(vIP2));
			flag = "True";
		    return "pass";
		}if (vKeyword.equals("input_password")){
			util.input_password(getIP(vIP1), getIP(vIP2));
			flag = "True";
		    return "pass";
		}if (vKeyword.equals("input_conFpassword")){
			util.input_conFpassword(getIP(vIP1), getIP(vIP2));
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
//		if (vKeyword.equals("dialog_click")){
//			boolean works = util.dialog_click();
//			if (works) {
//		        flag = "True";
//	            return "pass";
//		    }
//		}
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
		if (vIP.equals("vGender")){
			vIP = StartUp.vGender;
		}
		if (vIP.equals("vIdCard")){
			vIP = StartUp.vIdCard;
		}
		if (vIP.equals("vFirstname")){
			vIP = StartUp.vFirstname;
		}
		if (vIP.equals("vLastname")){
			vIP = StartUp.vLastname;
		}
		if (vIP.equals("vBirthday")){
			vIP = StartUp.vBirthday;
		}
		if (vIP.equals("vAddress")){
			vIP = StartUp.vAddress;
		}
		if (vIP.equals("vTel")){
			vIP = StartUp.vTel;
		}
		if (vIP.equals("vCareer")){
			vIP = StartUp.vCareer;
		}
		if (vIP.equals("vEmail")){
			vIP = StartUp.vEmail;
		}
		if (vIP.equals("vPassword")){
			vIP = StartUp.vPassword;
		}
		if (vIP.equals("vConfirmPassword")){
			vIP = StartUp.vConfirmPassword;
		}
	  return vIP;
	}

	public void getData(int k) {
		StartUp.vOpenApp = StartUp.xTDdata[k][2];
		System.out.println(StartUp.xTDdata[k][2]);
		StartUp.vGender = StartUp.xTDdata[k][4];
		StartUp.vIdCard = StartUp.xTDdata[k][5];
		StartUp.vFirstname = StartUp.xTDdata[k][6];
		StartUp.vLastname = StartUp.xTDdata[k][7];
		StartUp.vBirthday = StartUp.xTDdata[k][8];
		StartUp.vAddress = StartUp.xTDdata[k][9];
		StartUp.vTel = StartUp.xTDdata[k][10];
		StartUp.vCareer = StartUp.xTDdata[k][11];
		StartUp.vEmail = StartUp.xTDdata[k][12];
		StartUp.vPassword = StartUp.xTDdata[k][13];
		StartUp.vConfirmPassword = StartUp.xTDdata[k][14];
	}
}