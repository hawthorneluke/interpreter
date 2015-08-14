package newLang4.Functions;

import newLang4.Function;
import newLang4.Value;
import newLang4.Nodes.ExprList;
import newLang4.Values.VoidValue;

/** •¶š—ñ‚ğo—Í */
public class Print extends Function {

    public Print() {
    	name = "print";
    }
    
    public Value invoke(ExprList arg) {
    	Value v = arg.getValue();
    	String str = v.getSValue();
    	System.out.println(str);
    	return new VoidValue();       
    }
    
}
