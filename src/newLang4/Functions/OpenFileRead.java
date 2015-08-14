package newLang4.Functions;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import newLang4.Function;
import newLang4.Value;
import newLang4.Nodes.ExprList;
import newLang4.Values.BoolValue;

/** 読み込み用にファイルを開く */
public class OpenFileRead extends Function {

	private static FileInputStream fis;
	private static BufferedReader br;
	private static String fname;

	public OpenFileRead() {
		name = "openFileRead";
	}

	public Value invoke(ExprList arg) {
		Value v = arg.getValue();
		fname = v.getSValue();

		try {
			if (br != null)
				br.close();
			if (fis != null)
				fis.close();
			fis = new FileInputStream(fname);
			br = new BufferedReader(new InputStreamReader(fis));
			return new BoolValue(true);
		} catch (IOException e) {
			System.out.println("Cannot open file: " + fname);
		}

		return new BoolValue(false);
	}

	/** 開けたファイルを閉じるため */
	public static void closeFile() {
		try {
			if (br != null)
				br.close();
			if (fis != null)
				fis.close();
			br = null;
			fis = null;
		} catch (IOException e) {
			System.out.println("Cannot close file: " + fname);
		}
	}
	
	/** 開けたファイルから読み込むため */
	public static String readLine() {
		try {
			return br.readLine();
		} catch (IOException e) {
			System.out.println("Cannot read from file: " + fname);
		}
		return null;
	}

}
