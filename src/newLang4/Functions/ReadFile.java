package newLang4.Functions;

import newLang4.Function;
import newLang4.Value;
import newLang4.Nodes.ExprList;
import newLang4.Values.StringValue;
import newLang4.Values.VoidValue;

/** �J���Ă���ǂݍ��݃t�@�C�����當����1�s��ǂݍ��� */
public class ReadFile extends Function {

    public ReadFile() {
    	name = "readFile";
    }
    
    public Value invoke(ExprList arg) {
    	String line = OpenFileRead.readLine();
    	if (line != null) return new StringValue(line);
    	else return new VoidValue();
    }
    
}
