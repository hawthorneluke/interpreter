package newLang4.Functions;

import newLang4.Function;
import newLang4.Value;
import newLang4.Nodes.ExprList;
import newLang4.Values.IntValue;

/** —”‚ğ•Ô‚· */
public class Rand extends Function {
	
    public Rand() {
    	name = "rand";
    }
    
    public Value invoke(ExprList arg) {
    	int v = arg.getValue().getIValue();
    	int r = (int)(Math.random() * ((v) + 1));
    	return new IntValue(r);     
    }
    
}
