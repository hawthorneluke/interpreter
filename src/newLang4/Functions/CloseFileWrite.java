package newLang4.Functions;

import newLang4.Function;
import newLang4.Value;
import newLang4.Nodes.ExprList;
import newLang4.Values.VoidValue;

/** 開いている書き込みファイルを閉じる */
public class CloseFileWrite extends Function {
    
    public CloseFileWrite() {
    	name = "closeFileWrite";
    }
    
    public Value invoke(ExprList arg) {
    	OpenFileWrite.closeFile();
    	return new VoidValue();
    }
    
}
