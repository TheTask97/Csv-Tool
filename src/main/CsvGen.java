package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CsvGen {

	String caseFileSize = "mb"; // var for choosing file size
	String seperator = ",";
	double dTargetSize = 10; // this is the size your targeting
	long lTargetSize = 1;
	File f = null;
	long mult = 1;
	String header = "";
	String[] names = { "Jack Dalton", "Joe Dalton", "William Dalton", "Averell Dalton", "Lucky Luke" }; // row name
	String[] mails = { "jack@dalton.com", "joe@dalton.com", "william@dalton.com", "averell@dalton.com", // row mails
			"lucky@luke.com" };
	String[] birthdate = { "22.11.1965", "01.03.1965", "12.12.1964", "01.01.1966", "08.07.1961" }; // row birthdate
	String[] act = { "mad at Averell", "calming Joe", "\"keeping Joe from killing Averell\"", "playing with Rantanplan",
			"capturing the Daltons" }; // row activity
	String writePath;

	public CsvGen(double dTargetSize, String caseFileSize, String path) {

		this.caseFileSize = caseFileSize;
		this.dTargetSize = dTargetSize;
		this.writePath = path;

		System.out.println(this.caseFileSize);
		System.out.println(this.dTargetSize);
		System.out.println(this.writePath);
	}

	public long doIt() throws IOException {

		/*
		 * This programm allows you to create csv files with a specific size.
		 * 
		 */

		int counter = 0;
		FileWriter fw = new FileWriter(
				new File(writePath + "\\" + "generatedFile_" + dTargetSize + caseFileSize + ".csv"));

		// write Header
		header = "NAME" + seperator + "ACTIVITY" + seperator + "BIRTHDATE" + seperator + "MAIL"; // this defines the
																									// header
		fw.write(header + "\n\r");
		fw.flush();

		switch (caseFileSize) {

		case "byte":
			mult = 1;
			break;
		case "kb":
			mult = 1000;
			break;

		case "mb":
			mult = 1000 * 1000;
			break;
		case "gb":
			mult = 1000 * 1000 * 1000;
			break;
		}

		lTargetSize = mult * (long) Math.round(dTargetSize);

		long curTime = System.currentTimeMillis();

		while (f == null || f.length() < lTargetSize) {
			counter = counter % names.length;
			fw.write(names[counter] + seperator + act[counter] + seperator + birthdate[counter] + seperator
					+ mails[counter] + "\r\n");
			fw.flush();
			f = new File(writePath + "\\" + "generatedFile_" + dTargetSize + caseFileSize + ".csv");
			counter++;

		}
		fw.close();
		return (System.currentTimeMillis() - curTime);

	}

}
