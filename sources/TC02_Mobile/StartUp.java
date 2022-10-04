package TC02_Mobile;

public class StartUp {
	
	public static String vOpenApp, vGender, vIdCard, vFirstname, vLastname, vBirthday, vAddress, vTel, vCareer, vEmail, vPassword, vConfirmPassword;
	public static String vResult, vError, vflag; 
	public static String xTDdata[][];
	
	public static void main(String[] args) throws Exception {
		
		String xTSdata[][]; 
		String xTCdata[][]; 
		String vKeyword, vIP1, vIP2, vIP3, vIP4, vIP5, vIP6;
		
		String xlPath_tc = "C:/Users/Asus/Desktop/Project_Test/TC02_Register-By-Donor/TC02_Register-By-Donor_Result.xlsx";
		String xlPath_ts = "C:/Users/Asus/Desktop/Project_Test/TC02_Register-By-Donor/TS02_Register-By-Donor_Result.xlsx";
		String xlPath_td = "C:/Users/Asus/Desktop/Project_Test/TC02_Register-By-Donor/TD02_Register-By-Donor_Result.xlsx";
	    String xlPath = "C:/Users/Asus/Desktop/Project_Test/TC02_Register-By-Donor/TC02_Register-By-Donor_Data.xlsx";

		Driver myDriver = new Driver();
		
		ManageExcel kdf = new ManageExcel();
		xTCdata = kdf.xlRead(xlPath, 0);
		xTSdata = kdf.xlRead(xlPath, 1);
		xTDdata = kdf.xlRead(xlPath, 2);
		
		for (int i = 1; i < xTCdata.length; i++) {
			if (xTCdata[i][4].equals("Y")) {
		 		vflag = "Pass";
		 		
		 		for (int k = 1; k < xTDdata.length; k++) {
		 			System.out.println("Start Run Java Application : " + (xTDdata.length-1) + " Round" + " Round : " + k);
					if (xTDdata[k][1].equals("Y")) {
						myDriver.getData(k);
						
					for (int j = 1; j <xTSdata.length; j++){
						try {
							if (xTCdata[i][1].equals(xTSdata[j][0])){	
						
							vKeyword = xTSdata[j][4];
							vIP1 = xTSdata[j][5];
							vIP2 = xTSdata[j][6];
							vIP3 = xTSdata[j][7];
							vIP4 = xTSdata[j][8];
							vIP5 = xTSdata[j][9];
							vIP6 = xTSdata[j][10];
							System.out.println("---" + vKeyword + "````" + vIP1 + "````" + vIP2 + "````" + vIP3);
							System.out.println("````" + vIP4 + "````" + vIP5 + "````" + vIP6);
							vResult = "Pass";
	                		vError = "No Error";
	                		
	                		vResult = myDriver.keyword_executor(vKeyword, vIP1, vIP2, vIP3, vIP4, vIP5, vIP6);
	                		
	                		if(vResult.equalsIgnoreCase("Pass")) {
	                			xTSdata[j][11] = "Pass";
	                			xTSdata[k][12] = vError;
	                		}else if(vResult.equalsIgnoreCase("Fail")) {
	                			xTSdata[j][11] = "Fail";
	                		}else {
	                			//get text
	                			if(vKeyword.equals("get_text")) {
	                				
	                				try {
	                					if(vResult.equalsIgnoreCase(xTDdata[k][15])) {
	                						xTSdata[j][12] = vError;
	                						
	                						xTDdata[k][16] = vResult;
	                						xTDdata[k][17] = "Pass";
	                						xTDdata[k][18] = vError;
	                						xTDdata[k][19] = "-";
	                						xTDdata[k][20] = "-";
	                						
	                					}else {
	                						String Result = vResult;
	                						String[] arrA = Result.split(" ");
	                						String sp_Result = arrA[0] + " " + arrA[1] + " " + arrA[2] + " " + arrA[3] + " " + arrA[4] + "เท่านั้น";
	                						String sp_Result2 = arrA[0] + " " + arrA[1] + " " + "มีความยาวตั้งแต่" + " " + arrA[2] + " " + arrA[3] + "เท่านั้น";
	                						
	                						if(sp_Result.equalsIgnoreCase(xTDdata[k][15])||sp_Result2.equalsIgnoreCase(xTDdata[k][15])) {
	                							xTSdata[j][12] = vError;
	                							
	                							xTDdata[k][16] = vResult;
	                							xTDdata[k][17] = "Pass";
	                							xTDdata[k][18] = vError;
	                							xTDdata[k][19] = "-";
	                							xTDdata[k][20] = "เนื่องจาก กรณีทดสอบสำหรับการทดสอบผลลัพธ์ที่คาดไว้ มีความหมายใกล้เคียงกัน";
	                							
	                						}else {
	                							xTSdata[j][12] = vError;
	                							
	                							xTDdata[k][16] = vResult;
	                							xTDdata[k][17] = "Fail";
	                							xTDdata[k][18] = vError;
	                							String nameimg = myDriver.getUtility().saveScreen(xTDdata[k][0]);
	                							xTDdata[k][19] = nameimg;
	                							xTDdata[k][20] = "เนื่องจาก กรณีทดสอบสำหรับการทดสอบไม่ตรงกับผลลัพธ์ที่คาดไว้";
	                							
	                						}
	                						
	                					}
	                					
	                				}catch(Exception e) {
	                					xTSdata[j][12] = vError;
	                					
	                					xTDdata[k][16] = vResult;
	                					xTDdata[k][17] = "Fail";
	                					xTDdata[k][18] = vError;
	                					String nameimg = myDriver.getUtility().saveScreen(xTDdata[k][0]);
	                					xTDdata[k][19] = nameimg;
	                					xTDdata[k][20] = "เนื่องจาก กรณีทดสอบสำหรับการทดสอบไม่ตรงกับผลลัพธ์ที่คาดไว้";
	                					
	                				}
	                			}
	                		}
	               
	                		if (!vError.equals("No Error")){
	                			vflag = "Fail";
	                			
	                			xTSdata[k][11] = vflag;
	                			xTSdata[k][12] = vError;
	                			
	                			xTDdata[k][16] = vResult;
								xTDdata[k][17] = "Fail";
								xTDdata[k][18] = vError;
	                			
	                			String nameimg = myDriver.getUtility().saveScreen(xTDdata[k][0]);
								xTDdata[k][19] = nameimg;
		        				xTDdata[k][20] = "เนื่องจาก กรณีทดสอบสำหรับการทดสอบเกิดข้อผิดพลาด";
						    }
				        }
					}catch(Exception e) {}
				   }
				xTCdata[i][5] = vflag;							
			}
					System.out.println("End Run Java Application : " + (xTDdata.length-1) + " Round" + " Round : " + k);
		}	
		kdf.xlWrite(xlPath_tc, xTCdata, xTCdata.length, xTCdata[1].length);
		kdf.xlWrite(xlPath_ts, xTSdata, xTSdata.length, xTSdata[1].length);	
		kdf.xlWrite(xlPath_td, xTDdata, xTDdata.length, xTDdata[1].length);		
	  }		
    }
		System.out.println("Run Java Application Seccess!!!");
  }		
}


	
