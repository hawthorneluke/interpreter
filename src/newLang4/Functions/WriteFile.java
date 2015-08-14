package newLang4.Functions;

import newLang4.Function;
import newLang4.Value;
import newLang4.Nodes.ExprList;
import newLang4.Values.BoolValue;

/** 開いている書き込みファイルから文字列1行を書き込む */
public class WriteFile extends Function {
    
    public WriteFile() {
    	name = "writeFile";
    }
    
    public Value invoke(ExprList arg) {
    	Value v = arg.getValue();
    	String str = v.getSValue();
    	if (OpenFileWrite.writeLine(str))
    		return new BoolValue(true);
    	else return new BoolValue(false);
    }
    
}
