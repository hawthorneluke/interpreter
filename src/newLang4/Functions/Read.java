package newLang4.Functions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import newLang4.Function;
import newLang4.Value;
import newLang4.Nodes.ExprList;
import newLang4.Values.StringValue;

/** キーボードから入力された文字列を返す */
public class Read extends Function {
    
    public Read() {
    	name = "read";
    }
    
    public Value invoke(ExprList arg) {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String str = "error";
		try {
			str = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return new StringValue(str);       
    }
    
}
