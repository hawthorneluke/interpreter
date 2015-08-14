package newLang4.Functions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import newLang4.Function;
import newLang4.Value;
import newLang4.Nodes.ExprList;
import newLang4.Values.BoolValue;

/** �������ݗp�Ƀt�@�C�����J�� */
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

	/**�@�J�����t�@�C������邽�� */
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
	
	/** �J�����t�@�C���ɏ������ނ��� */
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
