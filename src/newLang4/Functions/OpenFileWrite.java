package newLang4.Functions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import newLang4.Function;
import newLang4.Value;
import newLang4.Nodes.ExprList;
import newLang4.Values.BoolValue;

/** 書き込み用にファイルを開く */
public class OpenFileWrite extends Function {

	private static BufferedWriter bw = null;
	private static String fname;

	public OpenFileWrite() {
		name = "openFileWrite";
	}

	public Value invoke(ExprList arg) {
		Value v = arg.getValue();
		fname = v.getSValue();

		try {
			if (bw != null) {
				bw.close();
			}
 
			bw = new BufferedWriter(new FileWriter(new File(fname)));

			return new BoolValue(true);
		} catch (IOException e) {
			System.out.println("Cannot open file: " + fname);
		}

		return new BoolValue(false);
	}

	/**　開けたファイルを閉じるため */
	public static void closeFile() {
		try {
			if (bw != null) {
				bw.close();
			}
			bw = null;
		} catch (IOException e) {
			System.out.println("Cannot close file: " + fname);
		}
	}
	
	/** 開けたファイルに書き込むため */
	public static boolean writeLine(String str) {
		try {
			bw.write(str);
			bw.newLine();
			return true;
		} catch (IOException e) {
			System.out.println("Cannot write to file: " + fname);
		}
		return false;
	}

}
