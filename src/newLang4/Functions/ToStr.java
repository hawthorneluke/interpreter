package newLang4.Functions;

import newLang4.Function;
import newLang4.Value;
import newLang4.Nodes.ExprList;
import newLang4.Values.StringValue;

/** 渡された値をStringValueに変換する */
public class ToStr extends Function {

    public ToStr() {
    	name = "toStr";
    }
    
    public Value invoke(ExprList arg) {
    	return new StringValue(arg.getValue().getSValue());    
    }
    
}
