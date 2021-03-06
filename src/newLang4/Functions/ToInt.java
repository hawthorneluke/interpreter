package newLang4.Functions;

import newLang4.Function;
import newLang4.Value;
import newLang4.Nodes.ExprList;
import newLang4.Values.IntValue;

/** 渡された値をIntValueに変換する */
public class ToInt extends Function {
    
    public ToInt() {
    	name = "toInt";
    }
    
    public Value invoke(ExprList arg) {
    	return new IntValue(arg.getValue().getIValue());    
    }
    
}
