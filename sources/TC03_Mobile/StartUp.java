package TC03_Mobile;

public class StartUp {
	
	public static String vOpenApp, vIdCard, vPassword;
	public static String vResult, vError, vflag; 
	public static String xTDdata[][];
	
	public static void main(String[] args) throws Exception {
		
		String xTSdata[][]; 
		String xTCdata[][]; 
		String vKeyword, vIP1, vIP2;
		
		String xlPath_tc = "C:/Users/Asus/Desktop/Project_Test/TC03_Login-By-Donor/Documents/TC03_Login-By-Donor_Result.xlsx";
		String xlPath_ts = "C:/Users/Asus/Desktop/Project_Test/TC03_Login-By-Donor/Documents/TS03_Login-By-Donor_Result.xlsx";
		String xlPath_td = "C:/Users/Asus/Desktop/Project_Test/TC03_Login-By-Donor/Documents/TD03_Login-By-Donor_Result.xlsx";
	    String xlPath = "C:/Users/Asus/Desktop/Project_Test/TC03_Login-By-Donor/Documents/TC03_Login-By-Donor_Data.xlsx";

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
							System.out.println("---" + vKeyword + "````" + vIP1 + "````" + vIP2);
							vResult = "Pass";
	                		vError = "No Error";
	                		
	                		vResult = myDriver.keyword_executor(vKeyword, vIP1, vIP2);
	                		
	                		if(vResult.equalsIgnoreCase("Pass")) {
	                			xTSdata[j][8] = "Pass";
	                		}else if(vResult.equalsIgnoreCase("Fail")) {
	                			xTSdata[j][8] = "Fail";
	                		}else {
	                			//get text
	                			if(vKeyword.equals("get_text")) {
	                				
	                				System.out.println("Expected Result : " + vResult.toString());
	                				System.out.println("Actual Result : " + xTDdata[k][6].toString());
	                				System.out.println(" == " + vResult.toString().equals(xTDdata[k][6].toString()));
	                				
	                				if(vResult.toString().equals(xTDdata[k][6].toString())) {
	                					xTSdata[j][9] = vError;
	                					
	                					xTDdata[k][7] = vResult;
	                					xTDdata[k][8] = "Pass";
	                					
	                					System.out.println("-------------------------------------");
	                					System.out.println("Expected Result : " + xTDdata[k][6]);
	                					System.out.println("Actual Result : " + vResult);
	                					System.out.println("Pass!!!");
	                					System.out.println("-------------------------------------");
	                					
	                					xTDdata[k][9] = vError;
	                					xTDdata[k][10] = "-";
	                					xTDdata[k][11] = "-";
	                					
	                				}else {
	                					xTSdata[j][9] = vError;
	                					xTDdata[k][7] = vResult;
	                					xTDdata[k][8] = "Fail";
	                					
	                					System.out.println("-------------------------------------");
	                					System.out.println("Expected Result : " + xTDdata[k][6]);
	                					System.out.println("Actual Result : " + vResult);
	                					System.out.println("Fail!!!");
	                					System.out.println("-------------------------------------");
	                					
	                					xTDdata[k][9] = vError;
	                					String nameimg = myDriver.getUtility().saveScreen(xTDdata[k][0]);
	                					xTDdata[k][10] = nameimg;
	                					xTDdata[k][11] = "เนื่องจาก กรณีทดสอบสำหรับการทดสอบไม่ตรงกับผลลัพธ์ที่คาดไว้";
	                					
	                				}
	                				
	                			}
	                			
	                		}
	               
	                		if (!vError.equals("No Error")){
	                			vflag = "Fail";
	                			
	                			xTSdata[k][8] = vflag;
	                			xTSdata[k][9] = vError;
	                			
	                			xTDdata[k][7] = vResult;
								xTDdata[k][8] = "Fail";
								
								System.out.println("-------------------------------------");
								System.out.println("Actual Result : " + vResult);
								System.out.println("Fail!!!");
								System.out.println("Error!!!");
								System.out.println("-------------------------------------");
								
								xTDdata[k][9] = vError;
	                			
	                			String nameimg = myDriver.getUtility().saveScreen(xTDdata[k][0]);
								xTDdata[k][10] = nameimg;
		        				xTDdata[k][11] = "เนื่องจาก กรณีทดสอบสำหรับการทดสอบเกิดข้อผิดพลาด";
						    }
				        }
					}catch(Exception e) {}
				   }
				xTCdata[i][6] = vflag;							
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


	
