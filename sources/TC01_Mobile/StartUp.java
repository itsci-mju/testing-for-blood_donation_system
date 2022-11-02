package TC01_Mobile;

public class StartUp {
	
	public static String vOpenApp, vTypeSearch, vKeywordSearch;
	public static String vResult, vError, vflag; 
	public static String xTDdata[][];
	
	public static void main(String[] args) throws Exception {
		
		String xTSdata[][]; 
		String xTCdata[][]; 
		String vKeyword, vIP1, vIP2, vIP3;
		
		String xlPath_tc = "C:/Users/Asus/Desktop/Project_Test/TC01_Search-Activity-Donation/Documents/TC01_Search-Activity-Donation_Result.xlsx";
		String xlPath_ts = "C:/Users/Asus/Desktop/Project_Test/TC01_Search-Activity-Donation/Documents/TS01_Search-Activity-Donation_Result.xlsx";
		String xlPath_td = "C:/Users/Asus/Desktop/Project_Test/TC01_Search-Activity-Donation/Documents/TD01_Search-Activity-Donation_Result.xlsx";
	    String xlPath ="C:/Users/Asus/Desktop/Project_Test/TC01_Search-Activity-Donation/Documents/TC01_Search-Activity-Donation_Data.xlsx";

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
							System.out.println("---" + vKeyword + "````" + vIP1 + "````" + vIP2 + "````" + vIP3);
							vResult = "Pass";
	                		vError = "No Error";
	                		
	                		vResult = myDriver.keyword_executor(vKeyword, vIP1, vIP2, vIP3);
	                		
	                		if(vResult.equalsIgnoreCase("Pass")) {
	                			xTSdata[j][8] = "Pass";
	                		}else if(vResult.equalsIgnoreCase("Fail")) {
	                			xTSdata[j][8] = "Fail";
	                		}else {
	                			//get text
								if(vKeyword.equals("get_text")) {
									if(vResult.equalsIgnoreCase(xTDdata[k][6])) {
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
										if(xTDdata[k][4].equals("วันที่")) {
											
//											if(!vResult.equalsIgnoreCase(xTDdata[k][6])) {
//												xTSdata[j][9] = vError;
//												
//												xTDdata[k][7] = vResult;
//												xTDdata[k][8] = "Fail";
//												
//												System.out.println("Expected Result : " + xTDdata[k][6]);
//												System.out.println("Actual Result : " + vResult);
//												System.out.println("Fail!!!");
//												
//												xTDdata[k][9] = vError;
//												
//												String nameimg = myDriver.getUtility().saveScreen(xTDdata[k][0]);
//												xTDdata[k][10] = nameimg;
//												xTDdata[k][11] = "เนื่องจาก กรณีทดสอบสำหรับการทดสอบไม่ตรงกับผลลัพธ์ที่คาดไว้";
//												}
											
											String txt_day = "";
											String day = "";
											txt_day = vResult;
											String[] arrA = txt_day.split(" ");
												
											day = arrA[0];
											System.out.println("Result Day : " + txt_day);
											System.out.println("Result Day Split : " + day);
											
											if(day.equals(xTDdata[k][5])) {
												xTSdata[j][9] = vError;
												
												xTDdata[k][7] = vResult;
												xTDdata[k][8] = "Pass";

												System.out.println("-------------------------------------");
												System.out.println("Expected Result : " + xTDdata[k][5]);
												System.out.println("Actual Result : " + vResult);
												System.out.println("Pass!!!");
												System.out.println("-------------------------------------");
												
												xTDdata[k][9] = vError;
												xTDdata[k][10] = "-";
												xTDdata[k][11] = "ระบบทำการแสดง วันที่กิจกรรมในการบริจาคโลหิตได้ถูกต้อง";	
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
										}else if(xTDdata[k][4].equals("สถานที่")) {
											if(vResult.equalsIgnoreCase(xTDdata[k][6])) {
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
												xTDdata[k][11] = "ระบบทำการแสดง สถานที่กิจกรรมในการบริจาคโลหิตได้ถูกต้อง";
											}else if(vResult.equalsIgnoreCase(xTDdata[k][5])){
												xTSdata[j][9] = vError;

												xTDdata[k][7] = vResult;
												xTDdata[k][8] = "Pass";

												System.out.println("-------------------------------------");
												System.out.println("Expected Result : " + xTDdata[k][5]);
												System.out.println("Actual Result : " + vResult);
												System.out.println("Pass!!!");
												System.out.println("-------------------------------------");
												
												xTDdata[k][9] = vError;
												xTDdata[k][10] = "-";
												xTDdata[k][11] = "ระบบทำการแสดง สถานที่กิจกรรมในการบริจาคโลหิตได้ถูกต้อง";
//											}else if(!vResult.equalsIgnoreCase(xTDdata[k][5])){
//													xTSdata[j][9] = vError;
//													
//													xTDdata[k][7] = vResult;
//													xTDdata[k][8] = "Fail";
//													xTDdata[k][9] = vError;
//													
//													String nameimg = myDriver.getUtility().saveScreen(xTDdata[k][0]);
//													xTDdata[k][10] = nameimg;
//													xTDdata[k][11] = "เนื่องจาก สถานที่ที่ค้นหา ไม่ตรงตามเงื่อนไข";
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


	
