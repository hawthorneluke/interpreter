package newLang4.Functions;

import newLang4.Function;
import newLang4.Value;
import newLang4.Nodes.ExprList;
import newLang4.Values.BoolValue;

/** �J���Ă��鏑�����݃t�@�C�����當����1�s���������� */
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
