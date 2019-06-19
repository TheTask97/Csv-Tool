package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CsvSep {

	String dir, filePath, fileName, fileUnit;
	double targetSize;
	long mult;

	public CsvSep(String filePath, String dir, String fileName, double targetSize, String fileUnit) {

		this.filePath = filePath;
		this.dir = dir;
		this.fileName = fileName;
		this.targetSize = targetSize;
		this.fileUnit = fileUnit;
	}

	public long doIt() throws IOException {

		switch (fileUnit) {

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

		long lTargetSize = mult * (long) Math.round(targetSize);

		File f = new File(filePath), fN = null;

		BufferedReader bfr = new BufferedReader(new FileReader(f));
		FileWriter fw = null;

		String s = null;
		int counter = 1;
		boolean alWrot = false;
		boolean didWrote = false;

		String header = bfr.readLine();

		long timeCur = System.currentTimeMillis();

		while ((s = bfr.readLine()) != null) {

			if (!didWrote) {

				fN = new File(dir + "\\" + fileName + counter + ".csv");
				fw = new FileWriter(fN);
				fw.write(header + "\n");
				fw.flush();
				fw.write(s + "\n");
				alWrot = true;
				didWrote = true;
				fw.flush();
			}
			if (fN.length() < lTargetSize) {

				if (!alWrot) {
					fw.write(s + "\n");
					fw.flush();
				} else {
					alWrot = false;
				}

			} else {
				counter++;
				fN = new File(dir + "\\" + fileName + counter + ".csv");
				fw = new FileWriter(fN);
				fw.write(header + "\n");
				fw.flush();
				fw.write(s + "\n");
				fw.flush();
			}

		}

		return (System.currentTimeMillis() - timeCur);

	}

}