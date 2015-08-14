package newLang4.Functions;

import newLang4.Function;
import newLang4.Value;
import newLang4.Nodes.ExprList;
import newLang4.Values.VoidValue;

/** �J���Ă��鏑�����݃t�@�C������� */
public class CloseFileWrite extends Function {
    
    public CloseFileWrite() {
    	name = "closeFileWrite";
    }
    
    public Value invoke(ExprList arg) {
    	OpenFileWrite.closeFile();
    	return new VoidValue();
    }
    
}
