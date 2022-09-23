package TC01_Mobile;

public class StartUp {
	
	public static String vOpenApp, vTypeSearch, vKeywordSearch;
	public static String vResult, vError, vflag; 
	public static String xTDdata[][];
	
	public static void main(String[] args) throws Exception {
		
		String xTSdata[][]; 
		String xTCdata[][]; 
		String vKeyword, vIP1, vIP2, vIP3;
		
		String xlPath_tc = "C:/Users/Asus/Desktop/Project_Test/TC01_Search-Activity-Donation/TC01_Search-Activity-Donation_Result.xlsx";
		String xlPath_ts = "C:/Users/Asus/Desktop/Project_Test/TC01_Search-Activity-Donation/TS01_Search-Activity-Donation_Result.xlsx";
		String xlPath_td = "C:/Users/Asus/Desktop/Project_Test/TC01_Search-Activity-Donation/TD01_Search-Activity-Donation_Result.xlsx";
	    String xlPath ="C:/Users/Asus/Desktop/Project_Test/TC01_Search-Activity-Donation/TC01_Search-Activity-Donation_Data.xlsx";

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
	                			xTSdata[k][9] = vError;
	                		}else if(vResult.equalsIgnoreCase("Fail")) {
	                			xTSdata[j][8] = "Fail";
	                		}else {
	                			//get text
								if(vKeyword.equals("get_text")) {
									if(vResult.equalsIgnoreCase(xTDdata[k][6])) {
										xTSdata[j][9] = vError;

										xTDdata[k][7] = vResult;
										xTDdata[k][8] = "Pass";
										xTDdata[k][9] = vError;
										xTDdata[k][10] = "-";
										xTDdata[k][11] = "-";
									}else {
										if(xTDdata[k][4].equals("�ѹ���")) {
											
											if(!vResult.equalsIgnoreCase(xTDdata[k][6])) {
												xTSdata[j][9] = vError;
												
												xTDdata[k][7] = vResult;
												xTDdata[k][8] = "Fail";
												xTDdata[k][9] = vError;
												
												String nameimg = myDriver.getUtility().saveScreen(xTDdata[k][0]);
												xTDdata[k][10] = nameimg;
												xTDdata[k][11] = "���ͧ�ҡ �óշ��ͺ����Ѻ��÷��ͺ���ç�Ѻ���Ѿ����Ҵ���";
												}
											
											String txt_day = "";
											int day = 0;
											int num = 0;
												
											txt_day = vResult;
											String[] arrA = txt_day.split(" ");
												
											day = Integer.parseInt(arrA[0]);
											System.out.println("Result Day : " + txt_day);
											System.out.println("Result Day Split : " + day);
												
											num =  Integer.parseInt(xTDdata[k][5]);
											
											if(num <= day) {
												xTSdata[j][9] = vError;
												
												xTDdata[k][7] = vResult;
												xTDdata[k][8] = "Pass";
												xTDdata[k][9] = vError;
												xTDdata[k][10] = "-";
												xTDdata[k][11] = "�к��ӡ���ʴ� �ѹ���Ԩ����㹡�ú�ԨҤ���Ե��١��ͧ";	
											}else {
												xTSdata[j][9] = vError;
												
												xTDdata[k][7] = vResult;
												xTDdata[k][8] = "Fail";
												xTDdata[k][9] = vError;
												
												String nameimg = myDriver.getUtility().saveScreen(xTDdata[k][0]);
												xTDdata[k][10] = nameimg;
												xTDdata[k][11] = "���ͧ�ҡ �ѹ������������㹪�ǧ�����ҧ�ѹ����ԨҤ���Ե";
											}
										}else if(xTDdata[k][4].equals("ʶҹ���")) {
											if(vResult.equalsIgnoreCase(xTDdata[k][6])) {
												xTSdata[j][9] = vError;

												xTDdata[k][7] = vResult;
												xTDdata[k][8] = "Pass";
												xTDdata[k][9] = vError;
												xTDdata[k][10] = "-";
												xTDdata[k][11] = "�к��ӡ���ʴ� ʶҹ���Ԩ����㹡�ú�ԨҤ���Ե��١��ͧ";
											}else if(vResult.equalsIgnoreCase(xTDdata[k][5])){
												xTSdata[j][9] = vError;

												xTDdata[k][7] = vResult;
												xTDdata[k][8] = "Pass";
												xTDdata[k][9] = vError;
												xTDdata[k][10] = "-";
												xTDdata[k][11] = "�к��ӡ���ʴ� ʶҹ���Ԩ����㹡�ú�ԨҤ���Ե��١��ͧ";
//											}else if(!vResult.equalsIgnoreCase(xTDdata[k][5])){
//													xTSdata[j][9] = vError;
//													
//													xTDdata[k][7] = vResult;
//													xTDdata[k][8] = "Fail";
//													xTDdata[k][9] = vError;
//													
//													String nameimg = myDriver.getUtility().saveScreen(xTDdata[k][0]);
//													xTDdata[k][10] = nameimg;
//													xTDdata[k][11] = "���ͧ�ҡ ʶҹ�������� ���ç������͹�";
												}else {
													xTSdata[j][9] = vError;

													xTDdata[k][7] = vResult;
													xTDdata[k][8] = "Fail";
													xTDdata[k][9] = vError;
													
													String nameimg = myDriver.getUtility().saveScreen(xTDdata[k][0]);
													xTDdata[k][10] = nameimg;
													xTDdata[k][11] = "���ͧ�ҡ �óշ��ͺ����Ѻ��÷��ͺ���ç�Ѻ���Ѿ����Ҵ���";
												}
										}else {
											xTSdata[j][9] = vError;

											xTDdata[k][7] = vResult;
											xTDdata[k][8] = "Fail";
											xTDdata[k][9] = vError;
											
											String nameimg = myDriver.getUtility().saveScreen(xTDdata[k][0]);
											xTDdata[k][10] = nameimg;
											xTDdata[k][11] = "���ͧ�ҡ �óշ��ͺ����Ѻ��÷��ͺ���ç�Ѻ���Ѿ����Ҵ���";
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
								xTDdata[k][9] = vError;
	                			
	                			String nameimg = myDriver.getUtility().saveScreen(xTDdata[k][0]);
								xTDdata[k][10] = nameimg;
		        				xTDdata[k][11] = "���ͧ�ҡ �óշ��ͺ����Ѻ��÷��ͺ�Դ��ͼԴ��Ҵ";
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


	