package newLang4.Functions;

import newLang4.Function;
import newLang4.Value;
import newLang4.Nodes.ExprList;
import newLang4.Values.VoidValue;

/** �J���Ă���ǂݍ��݃t�@�C������� */
public class CloseFileRead extends Function {
    
    public CloseFileRead() {
    	name = "closeFileRead";
    }
    
    public Value invoke(ExprList arg) {
    	OpenFileRead.closeFile();
    	return new VoidValue();
    }
    
}
