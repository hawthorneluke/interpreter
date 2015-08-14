package newLang4.Nodes;

import newLang4.*;
import newLang4.Values.BoolValue;

/** Expr EQ/GT/LT/GE/LE/NE Expr */
public class Cond extends Node {
	Node expr1, expr2;
    public Cond(Environment my_env, Node expr1, LexicalUnit sym, Node expr2) {
    	type = NodeType.COND;
        env = my_env;
        this.value = sym;
        this.expr1 = expr1;
        this.expr2 = expr2;
    }
    
    static public Node isMatch(Environment env, LexicalUnit u) {

        Node expr1 = Expr.isMatch(env, u);
        if (expr1 != null) {
        	LexicalUnit sym = env.getInput().get();
        	switch(sym.getType()) {
        	case EQ:
        	case GT:
        	case LT:
        	case GE:
        	case LE:
        	case NE:
        		LexicalUnit endEx = env.getInput().get();
        		Node expr2 = Expr.isMatch(env, endEx);
        		if (expr2 != null) {
        			return new Cond(env, expr1, sym, expr2);
        		}
        		env.getInput().unget(endEx);
        		break;
			default:
				break;
        	}
        	env.getInput().unget(sym);
        }

        return null;
    }
    
    public Value getValue() {
    	switch(value.getType()) {
    	case EQ:
    		if (expr1.getValue().eq(expr2.getValue()))
    			return new BoolValue(true);
    		else
    			return new BoolValue(false);
    	case GT:
    		if (expr1.getValue().gt(expr2.getValue()))
    			return new BoolValue(true);
    		else
    			return new BoolValue(false);
    	case LT:
    		if (expr1.getValue().lt(expr2.getValue()))
    			return new BoolValue(true);
    		else
    			return new BoolValue(false);
    	case GE:
    		if (expr1.getValue().ge(expr2.getValue()))
    			return new BoolValue(true);
    		else
    			return new BoolValue(false);
    	case LE:
    		if (expr1.getValue().le(expr2.getValue()))
    			return new BoolValue(true);
    		else
    			return new BoolValue(false);
    	case NE:
    		if (expr1.getValue().ne(expr2.getValue()))
    			return new BoolValue(true);
    		else
    			return new BoolValue(false);
		default:
			return new BoolValue(false);
    	}
    	
    }
    
    public boolean parse() {
    	if (value == null) {
    		env.parseError("No value");
    		return false;
    	}
    	if (!expr1.parse()) return false;
    	if (!expr2.parse()) return false;
    	return true;
    }
    
    public String toString() {
    	return value.getValue().getSValue() + "[" + expr1 + "," + expr2 + "]";
    }
}
