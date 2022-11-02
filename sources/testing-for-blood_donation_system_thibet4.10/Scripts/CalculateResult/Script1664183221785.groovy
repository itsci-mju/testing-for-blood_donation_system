import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint

import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import java.io.File;
import java.text.DecimalFormat;
import webTest.ReadWriteExcel as ReadWriteExcel

ReadWriteExcel kdf = new ReadWriteExcel();


	String xlPath = 'D:/TestCalculateResult/'
	String Newfile  = 'D:/TestCalculateResult/CalculateResult.xlsx'
	
	String [][] Sum;
	DecimalFormat df = new DecimalFormat();
	df.applyPattern("0.00");
	File directory = new File(xlPath);
	File[] fList = directory.listFiles();
	String [][] Testcase = new String[fList.length+2][5];
	int numTestcase = 1;
	int numpass = 0;
	int numfail = 0;
	
	Testcase[0][0] = "TestCase";
	Testcase[0][1] = "TestData";
	Testcase[0][2] = "Pass";
	Testcase[0][3] = "Fail";
	Testcase[0][4] = "%Pass";
	
	for (File f : fList){
		
		Sum = kdf.xlRead(xlPath+f.getName(),0)
		int Pass = 0;
		int Fail = 0 ;
		for(int i=1;i<Sum.length;i++) {
			
			String test = Sum[i].toString();
			String tss = null;
			int ts = test.indexOf("Pass");
			if(ts != -1){
				tss = test.substring(ts);	
			}else{
				ts = 0;
				ts = test.indexOf("Fail");
				tss = test.substring(ts);
			}

				
			if(tss.startsWith("Pass")) {
				Pass++;
				println ("Pass")
			}else if(tss.startsWith("Fail")) {
				Fail++;
				println ("Fail")
			}
		}
		Testcase[numTestcase][0] = ""+ numTestcase;
		Testcase[numTestcase][1] = ""+ (Pass + Fail);
		Testcase[numTestcase][2] = ""+ (Pass);
		Testcase[numTestcase][3] = ""+ (Fail);
		Testcase[numTestcase][4] = ""+ df.format((Pass*100.00/(Pass + Fail)));
		numpass = numpass+Pass;
		numfail = numfail+Fail;
		numTestcase++;
		}
	
		Testcase[numTestcase][0] = "";
		Testcase[numTestcase][1] = ""+ (numpass + numfail);
		Testcase[numTestcase][2] = ""+ (numpass);
		Testcase[numTestcase][3] = ""+ (numfail);
		Testcase[numTestcase][4] = ""+ df.format((numpass*100.00/(numpass + numfail)));
	    kdf.xlWrite(Newfile, Testcase, Testcase.length, Testcase[0].length)
	
	
	
	