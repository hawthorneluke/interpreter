package newLang4.Functions;

import newLang4.Function;
import newLang4.Value;
import newLang4.Nodes.ExprList;
import newLang4.Values.StringValue;

/** “n‚³‚ê‚½’l‚ðStringValue‚É•ÏŠ·‚·‚é */
public class ToStr extends Function {

    public ToStr() {
    	name = "toStr";
    }
    
    public Value invoke(ExprList arg) {
    	return new StringValue(arg.getValue().getSValue());    
    }
    
}
